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
