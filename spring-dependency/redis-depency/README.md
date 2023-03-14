#引入方式

1. 引入本模块依赖
    ```groovy
    dependencies {   
        implementation(project(":redis-depency"))
    }
    ```
1. 在引入的模块application.yml内加入如下配置
    ```yaml
    spring:
      profiles:
         active: redis
    ```
## note
- cluster 分布式集群
- 在 Spring Boot 1.x 版本默认使用的是 jedis ，而在 Spring Boot 2.x 版本默认使用的就是Lettuce
```txt
1. Jedis在实现上是直接连接的redis server，
   如果要保证在多线程环境下是非线程安全的，只有使用连接池，为每个Jedis实例增加物理连接
2. Lettuce的连接是基于Netty的，
   连接实例（StatefulRedisConnection）可以在多个线程间并发访问，
   因为StatefulRedisConnection是线程安全的，
   所以一个连接实例（StatefulRedisConnection）就可以满足多线程环境下的并发访问，
   当然这个也是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。
```
- RedisTemplate & StringRedisTemplate
   - [参考](https://blog.csdn.net/wo541075754/article/details/104798669/)
## problem
- docker-compose up 6台redis 出现如下WARN
- 不影响使用，rm docker container 重新up container 解决
- 应该是网络ip变动的问题
```shell
2021-04-06 21:47:39.635  WARN 6156 --- [ioEventLoop-6-8] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.10:6381]: Connection refused: /172.19.0.10:6381
2021-04-06 21:47:39.635  WARN 6156 --- [ioEventLoop-6-9] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.3:6384]: Connection refused: /172.19.0.3:6384
2021-04-06 21:47:39.635  WARN 6156 --- [oEventLoop-6-10] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.9:6383]: Connection refused: /172.19.0.9:6383
2021-04-06 21:47:39.637  WARN 6156 --- [ioEventLoop-6-1] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.2:6379]: Connection refused: /172.19.0.2:6379
2021-04-06 21:47:39.637  WARN 6156 --- [ioEventLoop-6-3] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.4:6382]: Connection refused: /172.19.0.4:6382
2021-04-06 21:47:39.639  WARN 6156 --- [ioEventLoop-6-7] i.l.c.c.topology.ClusterTopologyRefresh  : Unable to connect to [172.19.0.8:6380]: Connection refused: /172.19.0.8:6380
```
- 解决方案 --cluster-announce-ip 192.168.0.121
```yaml
version: '3'

x-config:
  &config
  image: redis:6.2.1-alpine3.13
  volumes:
    - /disk/1tdisk/docker/redis/conf:/etc/redis
    - /disk/1tdisk/docker/redis/data:/data
  environment:
    - TZ=Asia/Shanghai
services:
  redis6379:
    << : *config
    container_name: redis6379
    command: redis-server /etc/redis/redis.conf --cluster-announce-ip 192.168.0.121
    ports:
      - "16379:16379"
      - "6379:6379"
```
## RedisTemplate
### key 相关命令操作
  
|方法|描述|
|:---|:---|
|redisTemplate.delete(String key);|key 存在时删除 key 同步删除 value，会同步阻塞，value数据小时考虑使用delete|
|redisTemplate.unlink(String key);|key 存在时先删除 key，后序异步删除 value，不会阻塞,value数据过大时考虑使用unlink|
|redisTemplate.delete(Collection keys);|批量删除key，同步删除 value，会同步阻塞，value数据小时考虑使用delete|
|redisTemplate.unlink(Collection keys);|批量删除key，后序异步删除 value，不会阻塞，value数据过大时考虑使用unlink|
|redisTemplate.dump(String key);|给定 key ，并返回被序列化的值|
|redisTemplate.hasKey(String key);|检查给定 key 是否存在|
|redisTemplate.expire(String key, long timeout, TimeUnit unit);|设置过期时间|
|redisTemplate.expire(String key, Duration timeout);|设置过期时间|
|redisTemplate.expireAt(String key, Date date);|设置过期时间|
|redisTemplate.expireAt(String key, Instant expireAt);|设置过期时间|
|redisTemplate.getExpire(String key);|返回 key 的剩余的过期时间|
|redisTemplate.getExpire(String key, TimeUnit unit)|返回 key 的剩余的过期时间|
|redisTemplate.keys(String pattern);|查找所有符合给定模式( pattern)的 key|
|redisTemplate.move(String key, int dbIndex);|将当前数据库的 key 移动到给定的数据库 db 当中|
|redisTemplate.persist(String key);|移除 key 的过期时间，key 将持久保持|
|redisTemplate.randomKey();|从当前数据库中随机返回一个 key|
|redisTemplate.rename(String oldKey, String newKey);|修改 key 的名称|
|redisTemplate.renameIfAbsent(String oldKey, String newKey);|仅当 newkey 不存在时，将 oldKey 改名为 newkey|
|redisTemplate.type(String key);|返回 key 所储存的值的类型|