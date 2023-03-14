package org.bougainvilleas.spring.producingwebservice;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

	private static final String NAMESPACE_URI = "http://bougainvilleas.org/spring/producingwebservice";

	private CountryRepository countryRepository;

	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	/**
	 *  请求 uri http://localhost:8080/ws/countries
	 *  new SoapActionCallback("http://bougainvilleas.org/spring/producingwebservice/GetCountryRequest")
	 *  {@link GetCountryRequest}
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		return response;
	}
}
