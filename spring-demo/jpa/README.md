# JPA 

```yaml
spring:
  jpa:
    hibernate:
      # create 每次 run 没有表格会新建，表内数据会清空
      # create-drop 每次程序结束时候清空表
      # update 每次 run 没有表格会新建，表内数据不会清空，会更新表格
      # create 每次 run 校验 Entity 与 数据库的字段 是否匹配 不匹配则报错
      # none  Disable DDL handling
      ddl-auto: create-drop
```

# 参考文档

- [spring guides for H2](https://spring.io/guides/gs/accessing-data-jpa/)
- [spring demo for H2](https://github.com/spring-guides/gs-accessing-data-jpa)
- [spring guides for mysql](https://spring.io/guides/gs/accessing-data-mysql/)
- [spring demo for mysql](https://github.com/spring-guides/gs-accessing-data-mysql)
- [spring guides for MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
- [spring demo for MongoDB](https://github.com/spring-guides/gs-accessing-data-mongodb)