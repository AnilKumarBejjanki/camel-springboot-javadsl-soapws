package com.apache.camel.springbootcamelsoapws.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.training.esb.esb_adapter.GetAddressRequest;
import com.training.esb.esb_adapter.GetAddressResponse;

@Component
public class CamelProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		GetAddressResponse out = new GetAddressResponse();
		out.setCity("bpl");
		out.setState("TG");
		// Get input from exchange
		List soaList = exchange.getIn().getBody(List.class);
		GetAddressRequest input = (GetAddressRequest) soaList.get(0);
		System.out.println("Address id " + input.getAddressId().toString());
		// set output in exchange
		exchange.getOut().setBody(out);
	}

}
