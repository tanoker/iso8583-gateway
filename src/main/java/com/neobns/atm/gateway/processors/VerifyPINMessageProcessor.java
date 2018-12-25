package com.neobns.atm.gateway.processors;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.neobns.atm.gateway.model.Card;
import com.neobns.atm.gateway.service.CardService;
import com.neobns.atm.gateway.utils.ISO8583Generator;

/**
 * 
 * @author Pavel Gaiduk
 * Class providing functionality to generate 0110 response for given Card object
 */
public class VerifyPINMessageProcessor implements Processor {

	private CardService cardService;
	
	/**
	 * 
	 * @param cardService CardService object to get Card obejcts
	 */
	public VerifyPINMessageProcessor(CardService cardService) {
		this.cardService = cardService;
	}
	
	/**
	 * Method processes the camel exchange with Card object in body. Generates 0110 response as out body.
	 */
	public void process(Exchange exchange) throws Exception {
		Map<Object, Object> response = new HashMap<Object, Object>();
		Card card = exchange.getIn().getBody(Card.class);
		
		response.put("Field0", "0110");
		response.put("Field2", card.getCardNumber());

		Card cardFromDB = cardService.getCardByNumber(card.getCardNumber());
		if(cardFromDB != null && cardFromDB.getCardPIN().equals(card.getCardPIN())) {
			response.put("Field39", "3030");
		} else {
			response.put("Field39", "3535");
		}
		exchange.getOut().setBody(new ISO8583Generator().generateMessage(response));
	}

}
