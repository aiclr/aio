package org.bougainvilleas.spring.consumingwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class DatetimeConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller4Datetime() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> specified in
    // pom.xml or build.gradle
    /**
     * xjc(destdir: sourcesDir, schema: schema,
     *                     package: "org.bougainvilleas.spring.wsdl.datetime") {...
     */
    marshaller.setContextPath("org.bougainvilleas.spring.wsdl.datetime");
    return marshaller;
  }

  @Bean
  public DatetimeClient datetimeClient(Jaxb2Marshaller marshaller4Datetime) {
    DatetimeClient client = new DatetimeClient();
    client.setDefaultUri("http://localhost:8080/ws");
    client.setMarshaller(marshaller4Datetime);
    client.setUnmarshaller(marshaller4Datetime);
    return client;
  }
}
