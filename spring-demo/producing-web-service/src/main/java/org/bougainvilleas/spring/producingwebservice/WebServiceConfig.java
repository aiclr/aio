package org.bougainvilleas.spring.producingwebservice;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	private static final String NAMESPACE_URI = "http://bougainvilleas.org/spring/producingwebservice";

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	/**
	 *  请求 uri http://localhost:8080/ws/countries
	 *  new SoapActionCallback("http://bougainvilleas.org/spring/producingwebservice/GetCountryRequest")
	 *  客户端 genJaxb 获取请求需要的class  task genJaxb {ext.schema = "http://localhost:8080/ws/countries.wsdl"...
	 */
	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountriesPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}

	@Bean(name = "countriesSchema")
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/countries.xsd"));
	}


	/**
	 *  请求 uri http://localhost:8080/ws/datetime
	 *  new SoapActionCallback("http://bougainvilleas.org/spring/producingwebservice/GetDatetimeRequest")
	 *  客户端 genJaxb 获取请求需要的class  task genJaxb {ext.schema = "http://localhost:8080/ws/datetime.wsdl"...
	 */
	@Bean(name = "datetime")
	public DefaultWsdl11Definition datetimeWsdl11Definition(XsdSchema datetimeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountriesPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
		wsdl11Definition.setSchema(datetimeSchema);
		return wsdl11Definition;
	}

	@Bean(name = "datetimeSchema")
	public XsdSchema datetimeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/datetime.xsd"));
	}
}
