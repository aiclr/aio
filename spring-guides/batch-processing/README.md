# 批处理 将csv数据持久化到数据库

# 参考文档

- [spring demo](https://github.com/spring-guides/gs-batch-processing.git)
- [spring guides](https://spring.io/guides/gs/batch-processing/)
- [spring docs](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.spring-application.application-exit)

# csv 文件与 excel 可以自由转换

```mysql
DROP TABLE IF EXISTS people ;

create table people
(
    person_id BIGINT auto_increment comment '主键',
    first_name varchar(20) null comment '名',
    last_name varchar(20) null comment '姓',
    constraint people_pk
        primary key (person_id)
)comment '菜单表';
```

# 注意

```yaml
spring:
  batch:
    jdbc:
      initialize-schema: always # 创建 batch_job 相关表
```