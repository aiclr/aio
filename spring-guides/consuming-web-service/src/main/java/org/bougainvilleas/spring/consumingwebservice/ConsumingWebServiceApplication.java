package org.bougainvilleas.spring.consumingwebservice;

import org.bougainvilleas.spring.wsdl.GetCountryResponse;
import org.bougainvilleas.spring.wsdl.datetime.GetDatetimeResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumingWebServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConsumingWebServiceApplication.class, args);
  }

  @Bean
  CommandLineRunner lookup(CountryClient quoteClient) {
    return args -> {
      String country = "Spain";

      if (args.length > 0) {
        country = args[0];
      }
      GetCountryResponse response = quoteClient.getCountry(country);
      System.err.println(response.getCountry().getCurrency());
    };
  }

  @Bean
  CommandLineRunner datetime(DatetimeClient datetimeClient) {
    return args -> {
      String format = "yyyy年MM月dd日 HH时mm分ss秒";

      if (args.length > 0) {
        format = args[0];
      }
      GetDatetimeResponse response = datetimeClient.getDatetime(format);
      System.err.println(response.getDatetime().getUnix());
      System.err.println(response.getDatetime().getFormat());
    };
  }

}
