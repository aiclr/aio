FROM nginx:stable-alpine

MAINTAINER bougainvilleas <bougainvilleas@qq.com>

WORKDIR app

ENV TZ=Asia/Shanghai \
    LANG=en_US.UTF-8

COPY nginx.conf /etc/nginx/nginx.conf
# 将npm build 生成的前端包拷贝 /usr/share/nginx/html/ 这个目录下面
COPY dist  /usr/share/nginx/html/

RUN echo 'echo init ok!!'