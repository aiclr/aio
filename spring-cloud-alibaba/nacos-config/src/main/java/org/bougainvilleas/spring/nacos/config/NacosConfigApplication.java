package org.bougainvilleas.spring.nacos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
    String userName = applicationContext.getEnvironment().getProperty("user.name");
    String userAge = applicationContext.getEnvironment().getProperty("user.age");
    System.err.println("user name :" +userName+"; age: "+userAge);
  }

  @RestController
  class EchoController {

    private final ConfigurableApplicationContext applicationContext;

    EchoController(ConfigurableApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {
      String userName = applicationContext.getEnvironment().getProperty("user.name");
      String userAge = applicationContext.getEnvironment().getProperty("user.age");
      return "user name :" +userName+"; age: "+userAge;
    }
  }

}