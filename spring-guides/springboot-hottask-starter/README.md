# springboot 热加载 定时任务 starter

| version | org.springframework.boot | io.spring.dependency-management | jdk |
|:--------|:-------------------------|:--------------------------------|:----|
| 0.0.0   | 2.7.9                    | 1.1.0                           | 1.8 |

## 使用

### 发布到本地maven仓库

1. gradle publishMavenPublicationToMavenLocal 发布到本地仓库
2. gradle 引入 `implementation 'org.bougainvilleas:springboot-hottask-starter:x.x.x'`

### 直接导入本地jar包

1. gradle build 打包 ***springboot-hottask-starter-x.x.x.jar*** 放到项目libs目录下
2. `implementation fileTree(dir: 'libs', include: '*.jar')`

### gradle-8.0

1. 借助复合项目<sub>gradle8.0新特性</sub>gradle直接引入模块且不需要版本号 `implementation 'spring-guides:springboot-hottask-starter'`

### 配置

```yaml
hottask:
  enabled: true # 开启
  file: /data/hotTasks.xml # 定时任务使用xml文件持久化 文件位置

## 如果有 springdoc 或 swagger 暴露路径
springdoc:
  group-configs:
    - group: hotTask
      paths-to-match: /hotTask/*
```

### 定时任务管理api

```shell
## 查询全部
curl -X 'GET' \
  'http://127.0.0.1:8080/hotTask/getAll' \
  -H 'accept: */*'
## 修改
curl -X 'PUT' \
  'http://127.0.0.1:8080/hotTask/put' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "id": "1",
    "className": "org.bougainvilleas.spring.hottask.task.NewXAHeartTask",
    "beanName": "newXAHeartTask",
    "methodName": "parkingServiceTask",
    "initialDelay": 60000,
    "delay": 60000,
    "open": true,
    "del": false
  }'
## 逻辑删除
curl -X 'POST' \
  'http://127.0.0.1:8080/hotTask/del' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{"id": "2"}'
## 新增
 curl -X 'POST' \
  'http://127.0.0.1:8080/hotTask/add' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
    "className": "org.bougainvilleas.spring.hottask.tasks.Hot4SyncMonthCard",
    "beanName": "hot4SyncMonthCard",
    "methodName": "syncMonthCard",
    "expression": "0 */1 * * * ?",
    "open": false,
    "del": true
  }'
```

### 模板 [hotTasks.xml](data/hotTasks.xml)

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<HotTasks id="4">
    <HotTask>
        <id>3</id>
        <className>org.bougainvilleas.spring.hottask.tasks.Hot4SyncMonthCard</className>
        <beanName>hot4SyncMonthCard</beanName>
        <methodName>syncMonthCard</methodName>
        <HotFixedDelayTask>
            <initialDelay>3000</initialDelay>
            <delay>30000</delay>
        </HotFixedDelayTask>
        <createTime>20220419130000</createTime>
        <updateTime>20220419130000</updateTime>
        <open>false</open>
        <del>true</del>
    </HotTask>
    <HotTask>
        <id>1</id>
        <className>org.bougainvilleas.spring.hottask.task.NewXAHeartTask</className>
        <beanName>newXAHeartTask</beanName>
        <methodName>parkingServiceTask</methodName>
        <HotFixedDelayTask>
            <initialDelay>60000</initialDelay>
            <delay>30000</delay>
        </HotFixedDelayTask>
        <createTime>20220419130000</createTime>
        <updateTime>20220420185328</updateTime>
        <open>false</open>
        <del>false</del>
    </HotTask>
    <HotTask>
        <id>2</id>
        <className>org.bougainvilleas.spring.hottask.tasks.Hot4SyncMonthCard</className>
        <beanName>hot4SyncMonthCard</beanName>
        <methodName>syncMonthCard</methodName>
        <HotFixedRateTask>
            <initialDelay>3000</initialDelay>
            <interval>30000</interval>
        </HotFixedRateTask>
        <createTime>20220419130000</createTime>
        <updateTime>20220419130000</updateTime>
        <open>false</open>
        <del>true</del>
    </HotTask>
</HotTasks>

```