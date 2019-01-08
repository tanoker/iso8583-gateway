package com.neobns.atm.gateway.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neobns.atm.gateway.processors.ISO8583ParserProcessor;
import com.neobns.atm.gateway.processors.InvalidMessageProcessor;
import com.neobns.atm.gateway.processors.VerifyPINMessageProcessor;
import com.neobns.atm.gateway.repository.CardRepository;

/**
 * 
 * @author Pavel Gaiduk
 * Class contains definitions of the camel routes for receive ISO8583 messages as bytes and validate PIN
 */
@Component
public class VerifyPINRoute extends RouteBuilder {

	@Autowired
	CardRepository cardRepository;
	
	/**
	 * Method confgiures the camel routes
	 */
	@Override
	public void configure() throws Exception {
		
		Processor pinValidator = new VerifyPINMessageProcessor(cardRepository);
		Processor iso8583parser = new ISO8583ParserProcessor();
		Processor invalidProcessor = new InvalidMessageProcessor();
		
		from("netty4:tcp://localhost:9090?textline=true")
			.log(LoggingLevel.INFO, "Start flow. Incoming message: ${body}")
			.process(iso8583parser)
			.choice()
				.when(simple("${body} == null"))
					.process(invalidProcessor)
				.when(simple("${body} is 'com.neobns.atm.gateway.model.Card'"))
					.to("direct:validatePIN")
				.otherwise()
					.log(LoggingLevel.INFO, "Unhandable message received")
					.process(invalidProcessor)
			.end()
			.log(LoggingLevel.INFO, "End Flow. Outgoing message: ${body}")
		.end();

		from("direct:validatePIN")
			.log("Start PIN validation flow")
			.process(pinValidator)
			.log("End PIN validation flow")
		.end();
	
	}

}
