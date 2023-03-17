package org.bougainvilleas.spring.batch;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchApplication {
  @Bean
  public ExitCodeGenerator exitCodeGenerator() {
    return () -> 42;
  }

  public static void main(String[] args) {
    System.exit(SpringApplication.exit(SpringApplication.run(BatchApplication.class, args)));
  }
}
