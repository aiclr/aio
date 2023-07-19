<div style="text-align: center;font-size: 40px;">Layering Docker Images</div>

## env

- springboot:2.5.5
- jdk1.8
- reference
    - [springboot](https://docs.spring.io/spring-boot/docs/2.5.x/reference/html/features.html#features.container-images)
    - [docker](https://docs.docker.com/develop/develop-images/multistage-build/)
    - [docker-compose](https://docs.docker.com/compose/reference/)

## Dockerfile

```dockerfile
FROM alpine4jre8:1.0.0 as builder
MAINTAINER caddy <caddyren@qq.com>
WORKDIR application
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract && \
if [ -z "$(ls -A snapshot-dependencies/)" ]; then touch snapshot-dependencies/v; else echo "snapshot-dependencies is not Empty"; fi

FROM alpine4jre8:1.0.0 as layer
MAINTAINER caddy <caddyren@qq.com>
WORKDIR application
ARG CONFIG=src/main/resources/config/
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY ${CONFIG} config/
COPY --from=builder application/application/ ./
VOLUME ["/application/config","/application/logs"]
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```

***分层jar 没有snapshot依赖时，snapshot-dependencies是空文件夹不会创建layer，后面COPY无法获取到该层报错，所以加入一行if进行处理***

## docker-compose.yml

```yaml
version: "3.9"
services:
  rabbitmq:
    image: rabbitmq
    ports:
      - "5672:5672"
      - "15672:15672"
    environment:
      - RABBITMQ_DEFAULT_USER=guest
      - RABBITMQ_DEFAULT_PASS=guest
  app:
    image: app:0.0.1
    depends_on:
      - rabbitmq
    ports:
      - "8080:8080"
    volumes:
      - "/home/caddy/app/config:/application/config"
      - "/home/caddy/app/logs:/application/logs"
```

- docker build `docker build -f Dockerfile -t app:0.0.1`
- docker-compose run `docker-compose up rabbitmq app`
- layertools `java -Djarmode=layertools -jar application.jar extract`

## [HOME](../../../index.md)