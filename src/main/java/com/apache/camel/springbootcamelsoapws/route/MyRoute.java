package com.apache.camel.springbootcamelsoapws.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apache.camel.springbootcamelsoapws.processor.CamelProcessor;

@Component
public class MyRoute extends RouteBuilder {
	
	@Autowired
	CamelContext camelContext;
	
	@Autowired
	CamelProcessor camelProcessor;
	

	@Override
	public void configure() throws Exception {
		from("direct:start").id("ROUTE_ONE").log("HELLO FROM ROUTE").end();
		
		CxfEndpoint cxf = new CxfEndpoint();
		cxf.setAddress("http://localhost:8090/Address");
		//cxf.setDefaultOperationNamespace("xmlns:s=\"http://esb.training.com/esb_adapter\"");
		//cxf.setEndpointName("AddressPort");
		cxf.setServiceClass(com.training.esb.esb_adapter.AddressEndpoint.class);
		//cxf.setServiceName("AddressService");
		cxf.setWsdlURL("classpath:wsdl/address.wsdl");
		
		cxf.setCamelContext(camelContext);
		System.out.println("CXF OPERATION NAME IS :::"+cxf.getDefaultOperationName());
		from(cxf).log("Cxf properties are :::").process(camelProcessor).log("SOAP IS CALLED!!!").end();

	}

}
