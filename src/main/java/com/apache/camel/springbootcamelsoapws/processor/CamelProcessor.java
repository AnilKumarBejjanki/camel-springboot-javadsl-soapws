package com.apache.camel.springbootcamelsoapws.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.training.esb.esb_adapter.GetAddressRequest;
import com.training.esb.esb_adapter.GetAddressResponse;
import com.training.esb.esb_adapter.GetNameRequest;
import com.training.esb.esb_adapter.GetNameResponse;

@Component
public class CamelProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {

		GetAddressResponse out = new GetAddressResponse();
		out.setCity("bpl");
		out.setState("TG");
		
		GetNameResponse nameResponse = new GetNameResponse();
		nameResponse.setFirstName("Anil Kumar Bejjanki");
		// Get input from exchange
		List soaList = exchange.getIn().getBody(List.class);		
		Object input = (Object) soaList.get(0);
		if(input instanceof GetAddressRequest) {
			GetAddressRequest input1 = (GetAddressRequest)input;
			System.out.println("Address id " + input1.getAddressId().toString());
			// set output in exchange
			exchange.getOut().setBody(out);
		}
		if(input instanceof GetNameRequest) {
			GetNameRequest input2 = (GetNameRequest)input;
			System.out.println("NAME id ::" + input2.getNameId().toString());
			// set output in exchange
			exchange.getOut().setBody(nameResponse);
		}
		
		
	}

}
