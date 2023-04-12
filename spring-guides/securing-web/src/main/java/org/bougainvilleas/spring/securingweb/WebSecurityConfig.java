package org.bougainvilleas.spring.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((requests) -> requests
//                    .requestMatchers("/", "/home").permitAll()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
            )
            .logout((logout) -> logout.permitAll());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    System.out.println(encoder.encode("password"));

    UserDetails user = User.withUsername("user")
                    .password("{bcrypt}$2a$10$tNcVIM5.WNRq0liUxmpItemrT24buXX7RSHVjQB9IQnkkBRlOK1Na")
                    .roles("USER")
                    .build();
    return new InMemoryUserDetailsManager(user);
  }


}
