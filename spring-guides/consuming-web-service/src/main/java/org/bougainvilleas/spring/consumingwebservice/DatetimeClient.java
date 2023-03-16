package org.bougainvilleas.spring.consumingwebservice;

import org.bougainvilleas.spring.wsdl.datetime.GetDatetimeRequest;
import org.bougainvilleas.spring.wsdl.datetime.GetDatetimeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class DatetimeClient extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(DatetimeClient.class);

  public GetDatetimeResponse getDatetime(String format) {

    GetDatetimeRequest request = new GetDatetimeRequest();
    request.setFormat(format);

    log.info("Requesting location for " + format);

    GetDatetimeResponse response = (GetDatetimeResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                    "http://localhost:8080/ws/datetime",
                    request,
                    new SoapActionCallback("http://bougainvilleas.org/spring/producingwebservice/GetDatetimeResponse")
            );

    return response;
  }
}
