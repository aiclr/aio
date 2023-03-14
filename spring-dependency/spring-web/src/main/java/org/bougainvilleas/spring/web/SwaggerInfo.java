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
