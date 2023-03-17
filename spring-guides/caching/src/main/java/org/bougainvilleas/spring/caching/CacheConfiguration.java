package org.bougainvilleas.spring.caching;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Configuring the Cache Storage
 * <p>
 * default {@link org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration}
 * {@link org.springframework.cache.concurrent.ConcurrentMapCacheManager}
 * SimpleCacheConfiguration matched:
 * - Cache org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration automatic cache type (CacheCondition)
 * - @ConditionalOnMissingBean (types: org.springframework.cache.CacheManager; SearchStrategy: all) did not find any beans (OnBeanCondition)
 */
@Configuration
@EnableCaching
public class CacheConfiguration {

    /**
     * For Java 11 or above, use 3.x otherwise use 2.x.
     * 需要引入 caffeine 依赖 implementation 'com.github.ben-manes.caffeine:caffeine:2.9.3'
     * <p>
     * {@link org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration}
     * {@link org.springframework.cache.caffeine.CaffeineCacheManager}
     * <p>
     * <p>
     * CacheMetricsAutoConfiguration matched:
     * - @ConditionalOnBean (types: org.springframework.cache.CacheManager; SearchStrategy: all) found bean 'caffeineCacheManager' (OnBeanCondition)
     * CaffeineCacheConfiguration matched:
     * - @ConditionalOnClass found required classes 'com.github.benmanes.caffeine.cache.Caffeine', 'org.springframework.cache.caffeine.CaffeineCacheManager' (OnClassCondition)
     * - Cache org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration automatic cache type (CacheCondition)
     */
    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine());
        return caffeineCacheManager;
    }

    public Caffeine<Object, Object> caffeine() {
        return Caffeine.newBuilder()
                .initialCapacity(200)
                .maximumSize(600)
                .expireAfterWrite(5, TimeUnit.MINUTES);
    }


}
