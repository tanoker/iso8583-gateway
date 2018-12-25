package com.neobns.atm.gateway.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.neobns.atm.gateway.utils.ISO8583Parser;

/**
 * 
 * @author Pavel Gaiduk
 * Class providing functionality to transform ISO8583 message as String representation to Card object
 */
public class ISO8583ParserProcessor implements Processor {

	/**
	 * Method processes the camel exchange with String object in body. Generates Card object as out body.
	 */
	public void process(Exchange exchange) throws Exception {
		String iso8583data = exchange.getIn().getBody(String.class);
		exchange.getOut().setBody(new ISO8583Parser().parseISOMessageByJPOS(iso8583data));
	}

}
