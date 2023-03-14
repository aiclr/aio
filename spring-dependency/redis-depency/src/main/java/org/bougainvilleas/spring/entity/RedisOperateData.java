package org.bougainvilleas.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作数据 base 模型
 * @author caddy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisOperateData {
    //default String key-value
//    private RedisOperatesEnum operates=RedisOperatesEnum.RedisStringOperatesEnum.GET;
    private Object obj;//操作目标方法标识
    //key
    private String key;
    //value
    private Object value;
    //过期时间
    private long timeout;
    private TimeUnit unit;
    private Date date;
    //redis 库
    private int dbIndex;
    //旧key
    private String oldKey;
    private Collection<String> keys;
}


class HashData{
    /**
     * Map hGetAll(String key)	获取所有给定字段的值
     * Boolean hPutIfAbsent(String key, String hashKey, String value)	仅当hashKey不存在时才设置
     * boolean hExists(String key, String field)	查看哈希表 key 中，指定的字段是否存在
     * Set hKeys(String key)	获取所有哈希表中的字段
     * Long hSize(String key)	获取哈希表中字段的数量
     * List hValues(String key)	获取哈希表中所有值
     * Cursor hScan(String key, ScanOptions options)	迭代哈希表中的键值对
     */
    //Object hGet(String key, String field)	获取存储在哈希表中指定字段的值
    private String field;
    //List hMultiGet(String key, Collection fields)	获取所有给定字段的值
    private Collection<String> fields;
    //void hPut(String key, String hashKey, String value)	添加字段
    private String hashKey;
    //void hPutAll(String key, Map maps)	添加多个字段
    private Map<String,String> maps;
    //Long hDelete(String key, Object... fields)	删除一个或多个哈希表字段
    private Object[] flelds;
    //Long hIncrBy(String key, Object field, long increment)	为哈希表 key 中的指定字段的整数值加上增量 increment
    private long incrementL;
    //Double hIncrByFloat(String key, Object field, double delta)	为哈希表 key 中的指定字段的整数值加上增量 increment
    private double incrementD;

}
class ListData{
    /**
     * Long lLeftPush(String key, String value)	存储在list头部
     * Long lRightPush(String key, String value)	存储在list尾部
     * Long lLeftPushIfPresent(String key, String value)	当list存在的时候才加入
     * Long lRightPushIfPresent(String key, String value)	当list存在的时候才加入
     * String lLeftPop(String key)	移出并获取列表的第一个元素
     * String lBLeftPop(String key, long timeout, TimeUnit unit)	移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * String lRightPop(String key)	移除并获取列表最后一个元素
     * String lBRightPop(String key, long timeout, TimeUnit unit)	移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * Long lRemove(String key, long index, String value)	删除集合中值等于value得元素
     * Long lLen(String key)	获取列表长度
     */
    //String lIndex(String key, long index)	通过索引获取列表中的元素
    //void lSet(String key, long index, String value)	通过索引设置列表元素的值
    private long index;
    //List lRange(String key, long start, long end)	获取列表指定范围内的元素
    //void lTrim(String key, long start, long end)	裁剪list
    private long start;
    private long end;
    //Long lLeftPushAll(String key, String... value)	存储在list头部
    //Long lRightPushAll(String key, String... value)	存储在list尾部
    private String[] values;
    //Long lLeftPushAll(String key, Collection value)	存储在list头部
    //Long lRightPushAll(String key, Collection value)	存储在list尾部
    private Collection<String> valueList;
    //lLeftPush(String key, String pivot, String value)	如果pivot存在,再pivot前面添加
    //lRightPush(String key, String pivot, String value)	在pivot元素的右边添加值
    private String pivot;
    //String lRightPopAndLeftPush(String sourceKey, String destinationKey)	移除列表的最后一个元素，并将该元素添加到另一个列表并返回
    //String lBRightPopAndLeftPush(String sourceKey, String destinationKey,long timeout, TimeUnit unit)	从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
    private String sourceKey,destinationKey;

}
//abstract class SetRedis{
//    //添加
//    abstract Long sAdd(String key, String... values);
//    //获取集合所有元素
//    abstract Set<String> sMembers(String key);
//
//    //获取集合大小
//    Long sSize(String key);
//
//    //判断集合是否包含value
//    Boolean sIsMember(String key, Object value);
//
//    //随机获取集合中的一个元素
//    String sRandomMember(String key);
//
//    //随机获取集合count个元素
//    List<String> sRandomMembers(String key, long count);
//
//    //随机获取集合中count个元素并且去除重复的
//    Set<String> sDistinctRandomMembers(String key, long count);
//
//    //使用迭代器获取元素
//    Cursor<String> sScan(String key, ScanOptions options);
//
////-------------------------------------------------------------------------------------
//
//    //获取两个集合的交集
//    Set<String> sIntersect(String key, String otherKey);
//
//    //获取key集合与多个集合的交集
//    Set<String> sIntersect(String key, Collection<String> otherKeys);
//
//    //key集合与destKey集合的交集存储到destKey集合中
//    Long sIntersectAndStore(String key, String otherKey, String destKey);
//
//    //key集合与多个集合的交集存储到destKey集合中
//    Long sIntersectAndStore(String key, Collection<String> otherKeys, String destKey)
//
////--------------------------------------------------------------------------------------
//
//    //获取两个集合的并集
//    Set<String> sUnion(String key, String otherKeys);
//
//    //获取key集合与多个集合的并集
//    Set<String> sUnion(String key, Collection<String> otherKeys);
//
//    //key集合与otherKey集合的并集存储到destKey中
//    Long sUnionAndStore(String key, String otherKey, String destKey);
//
//    //key集合与多个集合的并集存储到destKey中
//    Long sUnionAndStore(String key, Collection<String> otherKeys, String destKey);
//
////-------------------------------------------------------------------------------------
//
//    //获取两个集合的差集
//    Set<String> sDifference(String key, String otherKey);
//
//    //获取key集合与多个集合的差集
//    Set<String> sDifference(String key, Collection<String> otherKeys);
//
//    //key集合与otherKey集合的差集存储到destKey中
//    Long sDifference(String key, String otherKey, String destKey);
//
//    //key集合与多个集合的差集存储到destKey中
//    Long sDifference(String key, Collection<String> otherKeys, String destKey);
//
//    //移除
//    Long sRemove(String key, Object... values);
//
//    //随机移除一个元素
//    String sPop(String key);
//
//    //将key集合中value元素移到destKey集合中
//    Boolean sMove(String key, String value, String destKey);
//
//}
//class ZSetData{
//    //添加元素,有序集合是按照元素的score值由小到大排列
//    Boolean zAdd(String key, String value, double score);
//
//    //批量添加
//    Long zAdd(String key, Set<TypedTuple<String>> values);
//    //TypedTuple使用
//    TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>(value, score);
//    //获取集合的元素, 从小到大排序, start开始位置, end结束位置
//    Set<String> zRange(String key, long start, long end);
//
//    //获取集合元素, 并且把score值也获取
//    Set<TypedTuple<String>> zRangeWithScores(String key, long start, long end);
//
//    //根据Score值查询集合元素的值, 从小到大排序
//    Set<String> zRangeByScore(String key, double min, double max);
//
//    //根据Score值查询集合元素, 从小到大排序
//    Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max);
//
//    //根据Score值查询集合元素, 从小到大排序
//    Set<TypedTuple<String>> zRangeByScoreWithScores(String key, double min, double max, long start, long end);
//
////----------------------------------------------------------------------------------
//
//    //获取集合的元素, 从大到小排序
//    Set<String> zReverseRange(String key, long start, long end);
//
//    //获取集合的元素, 从大到小排序, 并返回score值
//    Set<TypedTuple<String>> zReverseRangeWithScores(String key, long start, long end);
//
//    //根据Score值查询集合元素, 从大到小排序
//    Set<String> zReverseRangeByScore(String key, double min, double max);
//
//    //根据Score值查询集合元素, 从大到小排序
//    Set<TypedTuple<String>> zReverseRangeByScoreWithScores(String key, double min, double max);
//
//    //
//    Set<String> zReverseRangeByScore(String key, double min, double max, long start, long end);
//
////-----------------------------------------------------------------------------------
//
//    //返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
//    Long zRank(String key, Object value);
//
//    //返回元素在集合的排名,按元素的score值由大到小排列
//    Long zReverseRank(String key, Object value);
//
//    //根据score值获取集合元素数量
//    Long zCount(String key, double min, double max);
//
//    //获取集合大小
//    Long zSize(String key);
//
//    //获取集合大小
//    Long zZCard(String key);
//
//    //获取集合中value元素的score值
//    Double zScore(String key, Object value);
//
////------------------------------------------------------------------------------------
//
//    //获取key和otherKey的并集并存储在destKey中
//    Long zUnionAndStore(String key, String otherKey, String destKey);
//
//    //获取key和多个集合的并集并存储在destKey中
//    Long zUnionAndStore(String key, Collection<String> otherKeys, String destKey)
//
////-----------------------------------------------------------------------------------
//
//    //获取key和otherKey的交集并存储在destKey中
//    Long zIntersectAndStore(String key, String otherKey, String destKey);
//
//    //获取key和多个集合的交集并存储在destKey中
//    Long zIntersectAndStore(String key, Collection<String> otherKeys, String destKey);
//
////-----------------------------------------------------------------------------------
//
//    //使用迭代器获取
//    Cursor<TypedTuple<String>> zScan(String key, ScanOptions options);
//    //移除
//    Long zRemove(String key, Object... values);
//
//    //增加元素的score值，并返回增加后的值
//    Double zIncrementScore(String key, String value, double delta);
//
//    //移除指定索引位置的成员
//    Long zRemoveRange(String key, long start, long end);
//
//    //根据指定的score值的范围来移除成员
//    Long zRemoveRangeByScore(String key, double min, double max);
//
//}