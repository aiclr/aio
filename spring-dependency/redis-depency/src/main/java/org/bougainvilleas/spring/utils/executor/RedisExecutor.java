package org.bougainvilleas.spring.utils.executor;

import lombok.Getter;
import lombok.Setter;
import org.bougainvilleas.spring.entity.RedisOperateData;
import org.bougainvilleas.spring.entity.RedisResultData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

/**
 * redis 抽象执行器
 *
 * 每种数据类型作为一个具体执行器
 *
 * 使用一个大数据对象封装操作数
 *
 * @author caddy
 */
@Getter
@Setter
public abstract class RedisExecutor {
    protected RedisTemplate<String, Object> redisTemplate;
    protected StringRedisTemplate stringRedisTemplate;
    protected RedisExecutor nextExecutor;

    protected RedisExecutor(RedisTemplate<String, Object> redisTemplate,StringRedisTemplate stringRedisTemplate) {
        Assert.notNull(redisTemplate,"redisTemplate must be not null");
        this.redisTemplate = redisTemplate;
        Assert.notNull(stringRedisTemplate,"stringRedisTemplate must be not null");
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public abstract <V extends RedisOperateData > RedisResultData writeRequest(V data);
    public abstract <V extends RedisOperateData > RedisResultData readRequest(V data);

}


