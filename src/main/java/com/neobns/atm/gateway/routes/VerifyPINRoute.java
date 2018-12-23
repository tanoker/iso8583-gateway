package com.neobns.atm.gateway.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neobns.atm.gateway.processors.ISO8583ParserProcessor;
import com.neobns.atm.gateway.processors.VerifyPINMessageProcessor;
import com.neobns.atm.gateway.service.CardService;

@Component
public class VerifyPINRoute extends RouteBuilder {

	@Autowired
	CardService cardService;
	
	@SuppressWarnings("unused")
	@Override
	public void configure() throws Exception {
		CamelContext context = new DefaultCamelContext();
		Processor pinValidator = new VerifyPINMessageProcessor(cardService);
		Processor iso8583parser = new ISO8583ParserProcessor();

		from("netty4:tcp://localhost:9090?textline=true")
			.log(LoggingLevel.INFO, "Start flow. Incoming message: ${body}")
			.process(iso8583parser)
			.choice()
				.when(simple("${body} is 'com.neobns.atm.gateway.model.Card'"))
					.to("direct:validatePIN")
				.otherwise()
					.log(LoggingLevel.INFO, "Unhandable message received")
					.setBody(simple("0110"))
			.end()
			.log(LoggingLevel.INFO, "End Flow")
		.end();

		from("direct:validatePIN")
			.log("Start PIN validation flow")
			.process(pinValidator)
			.log("End PIN validation flow")
		.end();
	
	}

}
