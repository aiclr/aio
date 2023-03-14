package org.bougainvilleas.spring.utils.executor;

import lombok.extern.slf4j.Slf4j;
import org.bougainvilleas.spring.entity.RedisOperateData;
import org.bougainvilleas.spring.entity.RedisOperatesEnum;
import org.bougainvilleas.spring.entity.RedisResultData;
import org.bougainvilleas.spring.entity.RedisStringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * redis String 数据操作
 *
 * @author caddy
 */
@Component
@Slf4j
public class RedisStringExecutor extends RedisExecutor {

    @Autowired
    public RedisStringExecutor(RedisTemplate<String, Object> redisTemplate,StringRedisTemplate stringRedisTemplate) {
        super(redisTemplate,stringRedisTemplate);
    }
    @Override
    public <V extends RedisOperateData> RedisResultData writeRequest(V data) {
        Assert.notNull(data, "redis 操作不能为null");
        if(data instanceof RedisStringData){
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            RedisStringData stringData = (RedisStringData) data;
            RedisOperatesEnum.RedisStringOperatesEnum redisStringOperatesEnum = (RedisOperatesEnum.RedisStringOperatesEnum) stringData.getObj();
            switch (redisStringOperatesEnum.getKey()){
                case 0:
                    String result = get(stringData.getKey());
                    if(redisTemplate.hasKey(stringData.getKey())){
                        Assert.notNull(result, "获取指定 key 的值失败");
                    }
                    return RedisResultData.builder().code(true).data(result).build();
                case 1:
                    set(stringData);
                    break;
                case 2:
                    set(stringData);
                    break;
                case 3:
                    set(stringData);
                    break;
                case 4:
                    set(stringData);
                    break;
                default:
                    throw new RuntimeException();
            }
            // do something
            return null;
        }
        return nextExecutor.writeRequest(data);
    }

    @Override
    public <V extends RedisOperateData> RedisResultData readRequest(V data) {
        Assert.notNull(data, "redis 操作不能为null");
        if (data instanceof RedisStringData){
            //do something
            data.setValue(get(data));
            return null;
        }
        return nextExecutor.readRequest(data);
    }

    private boolean set(RedisStringData data) {
        boolean result = false;
        try {
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            ops.set(data.getKey(), data.getValue());
            ops.set(data.getKey(), data.getValue(), 1L, TimeUnit.MINUTES); //设置过期时间
            ops.set(data.getKey(), data.getValue(), Duration.ofMinutes(1L));//设置过期时间
            ops.set(data.getKey(), data.getValue(), 1L);//用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
            ops.append(data.getKey(), data.getValue().toString());//追加到末尾
            ops.setIfAbsent(data.getKey(), data.getValue());//只有在 key 不存在时设置 key 的值
            ops.getAndSet(data.getKey(), data.getValue());//将给定 key 的值设为 value ，并返回key的旧值(old value)
            ops.multiSet(new HashMap<>());//批量添加
            ops.multiSetIfAbsent(new HashMap<>());//批量添加
            ops.increment(data.getKey());//自曾1
            ops.increment(data.getKey(), 1L);//自曾
            ops.increment(data.getKey(), 0.4);//自曾
            ops.setBit(data.getKey(), 1L, true);//字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
            result = true;
        } catch (Exception e) {
            log.error("REDIS WRITE ERROR------\n往redis写入数据失败:{}", e.getMessage(), e);
        }
        return result;
    }

    private Object get(RedisOperateData data) {
        try {
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            ops.get("", 1L, 3L);//返回 key 中字符串值的子字符
            ops.multiGet(new ArrayList<>());//批量获取
            ops.getBit(data.getKey(), 1L);//获取键对应值的ascii码的在offset处位值
            ops.size(data.getKey());//获取字符串的长度
            return ops.get(data.getKey());
        } catch (Exception e) {
            log.error("REDIS READ ERROR------\n从redis读取数据失败:{}", e.getMessage(), e);
        }
        return null;
    }

    public String get(String key){
        try {
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            return (String) ops.get(key);
        } catch (Exception e) {
            log.error("REDIS READ ERROR------\n从redis读取数据失败:{}", e.getMessage(), e);
        }
        return "";
    }
    public void set(String key, String value){
        try {
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            ops.set(key, value);
        } catch (Exception e) {
            log.error("REDIS WRITE ERROR------\n往redis写入数据失败:{}", e.getMessage(), e);
            throw new RuntimeException();
        }
    }
    public String getAndSet(String key, String value){
        return null;
    }
    public Boolean getBit(String key, long offset){
        return null;
    }
    public void setEx(String key, String value, long timeout, TimeUnit unit){

    }

    public boolean setIfAbsent(String key, String value){
        return false;
    }
    public void setRange(String key, String value, long offset){

    }
    public String getRange(String key, long start, long end){
        return null;
    }
    public boolean multiSetIfAbsent(Map<String,String> maps){
        return false;
    }
    public Integer append(String key, String value){
        return null;
    }
    public Long size(String key){
        return null;
    }
    public <T> List<T> multiGet(Collection<T> keys){
        return null;
    }
    public boolean setBit(String key, long offset, boolean value){
        return false;
    }
    public void multiSet(Map<String,String> maps){

    }
    public Long incrBy(String key, long increment){
        return null;
    }
    public Double incrByFloat(String key, double increment){
        return null;
    }


}