---
title: Redis
targets:
  - name: 官方网址
    link: redis#official
  - name: docker
    link: redis#docker
  - name: podman
    link: podman#redis
  - name: k8s
    link: redis#k8s
  - name: commands
    link: redis#commands
  - name: redis json
    link: redis#redisjson
  - name: rejson docker & podman
    link: redis#docker-or-podman
  - name: rejson k8s
    link: redis#rejson-k8s
  - name: rejson commands
    link: redis#redisjson-commands
  - name: java client
    link: redis#jrejson
---

## official

> [home](https://redis.io) \
> [documentation](https://redis.io/documentation) \
> [commands](https://redis.io/commands) \
> [github](https://github.com/redis/redis)
>
> ***注意:***
> > [Redis Cluster only supports database zero](https://redis.io/commands/select)
>
> **redis 6.0** 以后的新功能 `ACL` 访问控制列表:
> > A container for Access List Control commands 访问控制列表

## docker

```bash
docker run -v /root/redis/conf:/usr/local/etc/redis -v /root/redis/data:/data -p 6379:6379 --name redis redis:6.2.6

```

## k8s

> [redis.yaml](k8s/redis.yaml)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
  labels:
    app: redis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      imagePullSecrets:
        - name: fly-reg
      containers:
        - name: redis
          image: fly.reg.com/redis:6.2.6
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 6379
          volumeMounts:
            - name: redis
              mountPath: /etc/localtime
              readOnly: true
      volumes:
        - name: redis
          hostPath:
            path: /etc/localtime
---
apiVersion: v1
kind: Service
metadata:
  name: redis
spec:
  type: NodePort
  selector:
    app: redis
  ports:
    - name: http
      port: 6379
      targetPort: 6379
      nodePort: 30003
```

## commands

> [官网地址](https://redis.io/commands)

> 测试 connect
> > `redis-cli -h localhost -p 6379 ping`

> 密码 测试 connect
> > `redis-cli -h localhost -p 6379 -a password ping`

> connect
> > `redis-cli -h localhost -p 6379`

> 密码 connect
> > `redis-cli -h localhost -p 6379 -a password`

> 默认连接 localhost 6379
> > `redis-cli`

> 按数据原有格式打印数据，不展示额外的类型信息; \
> 显示中文
> > `redis-cli --raw`

> 连接后 认证
> > *localhost:6379>* `AUTH [username] password`

> `ACL`\
> redis 6.0 以后的新功能 A container for Access List Control commands 访问控制列表\
> 默认用户 default ,设置不同用户并授予命令或数据权限
> > *localhost:6379>* `ACL USERS`

> 切换数据库 index[0,15] 供16个库，默认连接0
> > *localhost:6379>* `select 1`

> 显示当前数据库索引 \
> Return the number of keys in the currently-selected database
> > *localhost:6379>* `dbsize`

## redisjson

> 支持 `JSON` 的 redis `rejson`\
> [home](https://oss.redis.com/redisjson/) \
> [github](https://github.com/RedisJSON/RedisJSON) \
> [java client github](https://github.com/RedisJSON/JRedisJSON)

### docker or podman

```bash
docker run -v /root/redis/conf:/usr/local/etc/redis -v /root/redis/data:/data -p 6379:6379 --name rejson redislabs/rejson:2.0.6
podman run -v /root/redis/conf:/usr/local/etc/redis -v /root/redis/data:/data -p 6379:6379 --name rejson redislabs/rejson:2.0.6
```

### rejson k8s

> [rejson.yaml](k8s/rejson.yaml)

```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redisjson
  labels:
    app: redisjson
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redisjson
  template:
    metadata:
      labels:
        app: redisjson
    spec:
      imagePullSecrets:
        - name: fly-reg
      containers:
        - name: redisjson
          image: fly.reg.com/redisjson:2.0.6
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 6379
          volumeMounts:
            - name: redisjson
              mountPath: /etc/localtime
              readOnly: true
      volumes:
        - name: redisjson
          hostPath:
            path: /etc/localtime
---
apiVersion: v1
kind: Service
metadata:
  name: redisjson
spec:
  type: NodePort
  selector:
    app: redisjson
  ports:
    - name: http
      port: 6379
      targetPort: 6379
      nodePort: 30004
```

### redisjson commands

> *localhost:6379>* `JSON.SET doc $ '{"a":2,"b":3,"nested":{"a":4,"b":null}}'`
> > `OK`
>
> *localhost:6379>* `get keys`
> > `(nil)`
>
> *localhost:6379>* `dbsize`
> > `(integer) 1`
>
> *localhost:6379>* `GET *`
> > `(nil)`
>
> *localhost:6379>* `JSON.GET doc`
> > `"{\"a\":2,\"b\":3,\"nested\":{\"a\":4,\"b\":null}}"`
>
> *localhost:6379>* `JSON.GET doc $..b`
> > `"[3,null]"`
>
> *localhost:6379>* `JSON.GET doc ..a $..b`
> > `"{\"..a\":[2,4],\"$..b\":[3,null]}"`
>
> *localhost:6379>* `JSON.GET doc ..a`
> > `"2"`
>
> *localhost:6379>* `JSON.GET doc $..a`
> > `"[2,4]"`
>
> *localhost:6379>* `JSON.GET doc $..a $..b`
> > `"{\"$..a\":[2,4],\"$..b\":[3,null]}"`



## jrejson

> RedisJson java client \
> [github](https://github.com/RedisJSON/JRedisJSON) \
> [maven search](https://search.maven.org/artifact/com.redislabs/jrejson/1.5.0/jar)

### maven

```xml
<dependency>
  <groupId>com.redislabs</groupId>
  <artifactId>jrejson</artifactId>
  <version>1.5.0</version>
</dependency>
```

### gradle

```gradle
implementation 'com.redislabs:jrejson:1.5.0'
```

### example

```java
import redis.clients.jedis.Jedis;
import com.redislabs.modules.rejson.JReJSON;

//...

// First get a connection
JReJSON client = new JReJSON("localhost", 6379);

// Setting a Redis key name _foo_ to the string _"bar"_, and reading it back
client.set("foo", "bar");
String s0 = (String) client.get("foo");

// Omitting the path (usually) defaults to the root path, so the call above to
// `get()` and the following ones // are basically interchangeable
String s1 = (String) client.get("foo", new Path("."));
String s2 = (String) client.get("foo", Path.ROOT_PATH);

// Any Gson-able object can be set and updated
client.set("obj", new Object());             // just an empty object
client.set("obj", null, new Path(".zilch"));
Path p = new Path(".whatevs");
client.set("obj", true, p);
client.set("obj", 42, p);
client.del("obj", p);
//...

```
