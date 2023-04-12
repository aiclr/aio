package org.bougainvilleas.spring.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Eureka Server 加上安全认证之后，服务无法注册
 * 是因为新版本的security默认开启csrf,关闭即可
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关闭csrf
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证

        return http.build();
    }
}
