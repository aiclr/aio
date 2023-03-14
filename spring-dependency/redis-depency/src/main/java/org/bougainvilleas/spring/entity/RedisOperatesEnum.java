package org.bougainvilleas.spring.entity;

/**
 * redis 执行器类型
 *
 * switch 当case为有序数值时，效率高
 * @author caddy
 */
public interface RedisOperatesEnum {
    enum RedisStringOperatesEnum implements RedisOperatesEnum{
        GET(0,"String get(String key)"),//获取指定 key 的值
        GET_BIT(1,"Boolean getBit(String key, long offset)"),//对 key 所储存的字符串值，获取指定偏移量上的位(bit)
        GET_RANGE(2,"String getRange(String key, long start, long end)"),//返回 key 中字符串值的子字符
        SIZE(3,"Long size(String key)"),//获取字符串的长度
        MULTI_GET(4,"List multiGet(Collection keys)"),//批量获取

        SET(5,"void set(String key, String value)"),//设置指定 key 的值
        GET_AND_SET(6,"String getAndSet(String key, String value)"),//将给定 key 的值设为 value ，并返回key的旧值(old value)
        SET_EXPIRE(7,"void setEx(String key, String value, long timeout, TimeUnit unit)"),//将值 value 关联到 key ，并将 key 的过期
        SET_IF_ABSENT(8,"boolean setIfAbsent(String key, String value)"),//只有在 key 不存在时设置 key 的值
        SET_RANGE(9,"void setRange(String key, String value, long offset)"),//用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
        MULTI_SET_IF_ABSENT(10,"boolean multiSetIfAbsent(Map<String,String> maps)"),//同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
        APPEND(11,"Integer append(String key, String value)"),//追加到末尾
        SET_BIT(12,"boolean setBit(String key, long offset, boolean value)"),//设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
        MULTI_SET(13,"void multiSet(Map<String,String> maps)"),//批量添加
        INCR_BY_LONG(14,"Long incrBy(String key, long increment)"),//增加(自增长), 负数则为自减
        INCR_BY_FLOAT(15,"Double incrByFloat(String key, double increment)");//增加(自增长), 负数则为自减
        String desc ;
        int key;
        RedisStringOperatesEnum(int key,String desc) {
            this.desc = desc;
            this.key = key;
        }

        public String getDesc() {
            return desc;
        }
        public int getKey() {
            return key;
        }
    }

}
