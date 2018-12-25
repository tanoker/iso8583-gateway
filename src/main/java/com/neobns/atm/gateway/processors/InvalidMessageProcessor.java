package com.neobns.atm.gateway.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.neobns.atm.gateway.utils.ISO8583Generator;

/**
 * 
 * @author Pavel Gaiduk
 * Class providing functionality to generate 1644 response
 */
public class InvalidMessageProcessor implements Processor {

	/**
	 * Method generates 1644 response as out body.
	 */
	public void process(Exchange exchange) throws Exception {
		Map<Object, Object> response = new HashMap<Object, Object>();
		response.put("Field0", "1644");
		response.put("Field44", "650");
		exchange.getOut().setBody(new ISO8583Generator().generateMessage(response));
	}

}
