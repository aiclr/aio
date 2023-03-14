package org.bougainvilleas.spring.producingwebservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Endpoint
public class DatetimeEndpoint {
  private static final String NAMESPACE_URI = "http://bougainvilleas.org/spring/producingwebservice";

  /**
   *  请求 uri http://localhost:8080/ws/countries
   *  new SoapActionCallback("http://bougainvilleas.org/spring/producingwebservice/GetCountryRequest")
   *  {@link GetDatetimeRequest}
   */
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDatetimeRequest")
  @ResponsePayload
  public GetDatetimeResponse getCountry(@RequestPayload GetDatetimeRequest request) {
    GetDatetimeResponse response = new GetDatetimeResponse();
    Datetime datetime=new Datetime();
    LocalDateTime now = LocalDateTime.now();
    datetime.setUnix(now.toEpochSecond(ZoneOffset.ofHours(8)));
    datetime.setFormat(now.format(DateTimeFormatter.ofPattern(request.getFormat())));
    response.setDatetime(datetime);
    return response;
  }
}
