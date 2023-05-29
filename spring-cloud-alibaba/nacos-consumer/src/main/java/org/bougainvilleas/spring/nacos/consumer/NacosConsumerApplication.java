package org.bougainvilleas.spring.nacos.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApplication {

  @RestController
  public class NacosController{

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplate restTemplate2;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/echo/app-name")
    public String echoAppName(){
      //Access through the combination of LoadBalanceClient and RestTemplate
      ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
      String path = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),appName);
      System.out.println("request path:" +path);
      return restTemplate.getForObject(path,String.class);
    }

    @GetMapping("/echo/app-name2")
    public String echoAppName2(){
      //Access through the combination of LoadBalanceClient and RestTemplate
      return restTemplate2.getForObject("http://nacos-provider/echo/" + appName, String.class);
    }

  }

  //Instantiate RestTemplate Instance
  @Bean
  public RestTemplate restTemplate(){

    return new RestTemplate();
  }

  //Instantiate RestTemplate Instance
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate2(){

    return new RestTemplate();
  }

  public static void main(String[] args) {

    SpringApplication.run(NacosConsumerApplication.class,args);
  }

}