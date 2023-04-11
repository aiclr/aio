# Building a GraphQL service

# 参考文档

- [spring guides](https://spring.io/guides/gs/graphql-server/)
- [spring demo](https://github.com/spring-guides/gs-graphql-server.git)

## 测试

> 访问 `http://localhost:8080/graphiql` \
> 输入 
> ```
> query bookDetails {
>     bookById(id: "book-1") {
>         id
>         name
>         pageCount
>         author {
>             id
>             firstName
>             lastName
>         }
>     }
> }
> ```