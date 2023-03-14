package org.bougainvilleas.spring.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.time.Duration;

/**
 * https://wenchao.ren/posts/lettuce%E4%B8%80%E5%AE%9A%E8%A6%81%E6%89%93%E5%BC%80redis%E9%9B%86%E7%BE%A4%E6%8B%93%E6%89%91%E5%88%B7%E6%96%B0%E5%8A%9F%E8%83%BD/
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    RedisProperties redisProperties;

    @Autowired
    public RedisConfig(RedisProperties redisProperties) {
        Assert.notNull(redisProperties, "redisProperties must be not null");
        this.redisProperties = redisProperties;
    }

    /**
     * 这里要注意的是，在构建LettuceConnectionFactory 时，
     * 如果不使用内置的destroyMethod，可能会导致Redis连接早于其它Bean被销毁
     */
    @Bean(destroyMethod = "destroy")
    public LettuceConnectionFactory lettuceConnectionFactory() {
        // 配置用于开启自适应刷新和定时刷新。如自适应刷新不开启，Redis集群变更时将会导致连接异常
        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .adaptiveRefreshTriggersTimeout(Duration.ofSeconds(30))//自适应刷新超时时间(默认30秒)
                .enablePeriodicRefresh(Duration.ofSeconds(60))// 开启周期刷新(默认60秒)
                .refreshTriggersReconnectAttempts(redisProperties.getCluster().getMaxRedirects())//重连几次，然后再刷新集群拓扑
                .enableAllAdaptiveRefreshTriggers()//开启针对{@link RefreshTrigger}中所有类型的事件的触发器 MOVED，ASK，PERSISTENT都会触发
                //.enableAdaptiveRefreshTrigger(ClusterTopologyRefreshOptions.RefreshTrigger.MOVED_REDIRECT, ClusterTopologyRefreshOptions.RefreshTrigger.PERSISTENT_RECONNECTS)
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(clusterTopologyRefreshOptions)//拓扑刷新
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.DEFAULT)//Accept commands when auto-reconnect is enabled
                .autoReconnect(true)
                .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(10)))//redis命令超时时间,超时后才会使用新的拓扑信息重新建立连接
                .socketOptions(SocketOptions.builder().keepAlive(true).tcpNoDelay(true).build()).validateClusterNodeMembership(false)// 取消校验集群节点的成员关系
                .build();

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientOptions(clusterClientOptions)
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

        RedisClusterConfiguration configuration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        configuration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, clientConfig);
        factory.setDatabase(0);
        factory.setShareNativeConnection(true);
        factory.resetConnection();
        factory.setValidateConnection(false);
        return factory;
    }


    /**
     * key string
     * value Object
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        template.setValueSerializer(getJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(getJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * key value 都是string
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        StringRedisTemplate strRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
        // key采用String的序列化方式
        strRedisTemplate.setKeySerializer(new StringRedisSerializer());
        // hash的key也采用String的序列化方式
        strRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value序列化方式采用jackson
        strRedisTemplate.setValueSerializer(getJackson2JsonRedisSerializer());
        // hash的value序列化方式采用jackson
        strRedisTemplate.setHashValueSerializer(getJackson2JsonRedisSerializer());
        return strRedisTemplate;
    }

    /**
     * json序列化
     */
    private Jackson2JsonRedisSerializer<Object> getJackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }


}

