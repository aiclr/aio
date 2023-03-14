# spring web & springdoc

## build.gradle

```kotlin
ext {
    set('webName', 'undertow') // 'tomcat' 'jetty' 'undertow'
}
//可以依赖 模块
implementation(project(":spring-web"))
// 可以发布到maven 依赖 jar
implementation("org.bougainvillea.spring:spring-web:${webName}.${springBootVersion}")
```

## application.yml **推荐**

```yaml
spring:
  profiles:
    active: docs,dev # docs 往前放后面的配置会覆盖掉不用的配置
swagger:
  enable: true
  info:
    title: Global interface
    description: 希君生羽翼 一化北溟鱼
    termsOfService: http://localhost:${PORT}
    version: 0.0.3
    contact:
      name: caddy
      url: https://bougainvilleas.github.io/lotus/
      email: caddyren@qq.com
    license:
      name: MIT
      url: https://github.com/caddyRen/spring/blob/develop-2.0x/LICENSE
    external:
      description: other
      url: http://localhost


springdoc:
  show-actuator: true # To display the actuator endpoints
  use-management-port: false # To expose the swagger-ui on the actuator management port
  swagger-ui:
    path: /swagger-ui.html
    enabled: ${swagger.enable:false}
  api-docs:
    enabled: ${swagger.enable:false}
    path: /v3/api-docs # For custom path of the OpenAPI documentation in Json format
    groups:
      enabled: true
  group-configs:
    - group: default
      packages-to-scan: org.bougainvilleas.spring
#      参考以下配置
#    - group: default1
#      packages-to-scan: org.bougainvilleas.spring.default1
#      packages-to-exclude: org.bougainvilleas.spring.default2
#      paths-to-match: /default1/**
#      paths-to-exclude: /default2/**
#    - group: default2
#      packages-to-scan: org.bougainvilleas.spring.default2
#      packages-to-exclude: org.bougainvilleas.spring.default1
#      paths-to-match: /default2/**
#      paths-to-exclude: /default1/**
```

## SwaggerConfig & SwaggerInfo **不推荐**

```java
package org.bougainvilleas.spring.web;


import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @author renqiankun
 * 2022-03-04 16:03:46 星期五
 */
@Configuration
@EnableConfigurationProperties(SwaggerInfo.class)
public class SwaggerConfig
{

    private final SwaggerInfo swaggerInfo;

    public SwaggerConfig(SwaggerInfo swaggerInfo)
    {
        Assert.notNull(swaggerInfo, "SwaggerConfig must be not null");
        this.swaggerInfo = swaggerInfo;
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("default")
                .packagesToScan("org.bougainvilleas.spring")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(swaggerInfo.getInfo())
                .externalDocs(swaggerInfo.getExternal());
    }

}

```

## SwaggerInfo
```java
package org.bougainvilleas.spring.web;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author renqiankun
 * 2022-03-04 17:26:53 星期五
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo
{
    private boolean enable;

    private Info info;

    private ExternalDocumentation external;

}

```
