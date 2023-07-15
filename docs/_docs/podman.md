---
title: podman
targets:
  - name: podman official
    link: https://podman.io
  - name: github
    link: https://github.com/containers/podman
  - name: Rootless mode
    link: https://man.archlinux.org/man/podman.1#Rootless_mode
  - name: Commands
    link: https://docs.podman.io/en/latest/Commands.html
  - name: pod
    link: podman#pod
  - name: mysql
    link: podman#mysql
  - name: nacos
    link: podman#nacos
  - name: redis
    link: podman#redis
  - name: rabbitmq
    link: podman#rabbitmq
  - name: elasticsearch
    link: podman#elasticsearch
---

# podman

> archlinux 安装 `pacman -S podman`
>
> opensuse 安装 `zypper in podman`

> rootless mode
> > `sudo usermod --add-subuids 10000-75535 USERNAME`\
> > `sudo usermod --add-subgids 10000-75535 USERNAME`
> >
> > `echo USERNAME:10000:65536 >> /etc/subuid`\
> > `echo USERNAME:10000:65536 >> /etc/subgid`

> `docker hub`国内加速镜像`registries.conf`
> > `sudo vim /etc/containers/registries.conf`
> > ```properties
> > [[registry]]
> >    prefix = "docker.io"
> >    location = "docker.io"
> > [[registry.mirror]]
> >    location = "rjm3pmfv.mirror.aliyuncs.com"
> > ```

> `podman --help` & `man podman`\
> `podman <subcommand> --help` & `man podman-<subcommand>`
> > `podman pod --help` & `man podman-pod`

> searching images
> > `podman search docker.io/httpd`
> > `podman search --limit 3 --format "{{.Name}}\t{{.Stars}}\t{{.Official}}" docker.io/httpd`
> > `podman search --limit 3 --list-tags --format "{{.Name}}\t{{.Tag}}" docker.io/httpd`
>
> searching official images
> > `podman search docker.io/httpd --filter=is-official`\
> > `podman search docker.io/httpd -f=is-official`
>
> pulling some images
> > `podman pull docker.io/library/httpd`
>
> Running a container
> > `podman run -dt -p 8080:80/tcp docker.io/library/httpd`

> `podman images` list all images

> `podman ps` Listing running containers

> `podman ps -a` Listing all containers

> `podman inspect -l | grep IPAddress` Inspecting a running container

> `podman logs -l` Viewing the container’s logs

> `podman top -l` Viewing the container’s pids

> `podman stop -l` Stopping the container

> `podman rm -l` Removing the container

## pod

> `podman pod create --publish 5672:5672,15672:15672,3306:3306,6379:6379,8001:8001 --name mysql-mq-redis-pod`

## mysql

> [MySQL 8.0 Release Notes](https://dev.mysql.com/doc/relnotes/mysql/8.0/en/)
>
> `podman pull docker.io/library/mysql:8.0.33`
>
> [MySQL 5.7 Release Notes](https://dev.mysql.com/doc/relnotes/mysql/5.7/en/)
>
> `podman pull docker.io/library/mysql:5.7.42`
>
> pod
>
> `podman run -d --pod mysql-mq-redis-pod --env MYSQL_ROOT_PASSWORD=toor --tz=Asia/Shanghai -v ~/samba/podman/mysql:/var/lib/mysql --name mysql docker.io/library/mysql:8.0.33`\
> `podman run -d --pod mysql-mq-redis-pod --env MYSQL_ROOT_PASSWORD=toor --tz=Asia/Shanghai -v ~/samba/podman/mysql:/var/lib/mysql --name mysql docker.io/library/mysql:5.7.42`
>
> no pod
>
> `podman run --rm -d --env MYSQL_ROOT_PASSWORD=toor -p 3306:3306 --tz=Asia/Shanghai -v ~/samba/podman/mysql:/var/lib/mysql --name mysql docker.io/library/mysql:8.0.33`\
> `podman run --rm -d --env MYSQL_ROOT_PASSWORD=toor -p 3306:3306 --tz=Asia/Shanghai -v ~/samba/podman/mysql:/var/lib/mysql --name mysql docker.io/library/mysql:5.7.42`

## rabbitmq

> [Downloading and Installing RabbitMQ](https://www.rabbitmq.com/download.html)
>
> `podman pull docker.io/library/rabbitmq:3.11-management-alpine`
>
> pod
>
> `podman run -d --pod mysql-mq-redis-pod --env RABBITMQ_DEFAULT_USER=guest --env RABBITMQ_DEFAULT_PASS=tseug --tz=Asia/Shanghai --name rabbitmq docker.io/library/rabbitmq:3.11-management-alpine`
>
> no pod
>
> `podman run -d --env RABBITMQ_DEFAULT_USER=guest --env RABBITMQ_DEFAULT_PASS=guest -p 5672:5672 -p 15672:15672 --tz=Asia/Shanghai --name rabbitmq docker.io/library/rabbitmq:3.11-management-alpine`

## redis

> [https://redis.io/download/](https://redis.io/download/)
>
> `podman pull docker.io/library/redis:latest`\
> `podman pull docker.io/library/redis:7.0-alpine`\
> `podman pull docker.io/library/redis:6.2-alpine`\
> `podman pull docker.io/library/redis:6.0-alpine`\
> `podman pull docker.io/library/redis:5.0-alpine`\
> `podman pull docker.io/redis/redis-stack-server:latest`\
> `podman pull docker.io/redis/redis-stack:latest`
>
> pod
>
> `podman run -d --pod mysql-mq-redis-pod -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis docker.io/library/redis:6.2-alpine`\
>
> `podman run -d --pod mysql-mq-redis-pod -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis-stack-server docker.io/redis/redis-stack-server:latest`
>
> `podman run -d --pod mysql-mq-redis-pod -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis-stack docker.io/redis/redis-stack:latest`
>
> no pod
>
> `podman run -d -p 6379:6379 -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis docker.io/library/redis:6.2-alpine`
>
> `podman run -d -p 6379:6379 -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis-stack-server docker.io/redis/redis-stack-server:latest`
>
> `podman run -d -p 6379:6379 -p 8001:8001 -v ~/samba/podman/redis/conf:/etc/redis -v ~/samba/podman/redis/data:/data --tz=Asia/Shanghai --name redis-stack docker.io/redis/redis-stack:latest`
>
> redis-cli
>
> `podman exec -it redis-stack redis-cli`

## elasticsearch

> [elasticsearch releases](https://www.elastic.co/downloads/past-releases#elasticsearch)\
> [kibana releases](https://www.elastic.co/downloads/past-releases#kibana)
>
> `podman pull docker.io/library/elasticsearch:latest docker.io/library/kibana:latest`\
> `podman pull docker.io/library/elasticsearch:8.6.0 docker.io/library/kibana:8.6.0`
>
> pod
>
> `podman pod create --publish 9200:9200,9300:9300,5601:5601 --name elk-pod`
>
> `podman pod logs -f elk-pod`
>
> `podman run -d --pod elk-pod --env "ES_JAVA_OPTS=-Xms512m -Xmx512m" --tz=Asia/Shanghai --name elasticsearch docker.io/library/elasticsearch:8.6.0`
>
> `podman run -d --pod elk-pod --env ELASTICSEARCH_URL=http://elasticsearch:9200/ --tz=Asia/Shanghai --name kibana docker.io/library/kibana:8.6.0`
>
> `podman exec -it kibana /bin/bash`
> > `cat /etc/libana/kibana.yml`
>
> no pod
>
> `podman run -d --name elasticsearch --env "ES_JAVA_OPTS=-Xms512m -Xmx512m" --tz=Asia/Shanghai -p 9200:9200 -p 9300:9300 docker.io/library/elasticsearch:8.6.0`
>
> `podman run -d --name kibana --env ELASTICSEARCH_URL=http://宿主机IP:9200/ --tz=Asia/Shanghai -p 5601:5601 docker.io/library/kibana:latest`
