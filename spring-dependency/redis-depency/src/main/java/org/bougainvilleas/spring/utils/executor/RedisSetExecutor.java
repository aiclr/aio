package org.bougainvilleas.spring.utils.executor;

import org.bougainvilleas.spring.entity.RedisOperateData;
import org.bougainvilleas.spring.entity.RedisResultData;
import org.bougainvilleas.spring.entity.RedisSetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis set 数据操作
 * @author caddy
 */
@Component
public class RedisSetExecutor extends RedisExecutor {

    @Autowired
    public RedisSetExecutor(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate) {
        super(redisTemplate,stringRedisTemplate);
    }

    @Override
    public <V extends RedisOperateData> RedisResultData writeRequest(V data) {
        if(data instanceof RedisSetData){
            // do something
            return null;
        }
        return nextExecutor.writeRequest(data);
    }

    @Override
    public <V extends RedisOperateData> RedisResultData readRequest(V data) {
        if (data instanceof RedisSetData){
            //do something
            return null;
        }
        return nextExecutor.readRequest(data);
    }
}