package com.neobns.atm.gateway.processors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.neobns.atm.gateway.model.Card;
import com.neobns.atm.gateway.repository.CardRepository;
import com.neobns.atm.gateway.utils.ISO8583Generator;

/**
 * 
 * @author Pavel Gaiduk
 * Class providing functionality to generate 0110 response for given Card object
 */
public class VerifyPINMessageProcessor implements Processor {

	private CardRepository cardRepository;
	
	/**
	 * 
	 * @param cardRepository CardRepository object to get Card objects
	 */
	public VerifyPINMessageProcessor(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	/**
	 * Method processes the camel exchange with Card object in body. Generates 0110 response as out body.
	 */
	public void process(Exchange exchange) throws Exception {
		Card card = exchange.getIn().getBody(Card.class);
		if(card != null) {
			Map<Object, Object> response = new HashMap<Object, Object>();
			response.put("Field0", "0110");
			response.put("Field2", card.getCardNumber());
			List<Card> cards = cardRepository.findByCardNumber(card.getCardNumber());
			Card cardFromDB = cards.size() > 0 ? cards.get(0) : null;
			if(cardFromDB != null && cardFromDB.getCardPIN().equals(card.getCardPIN())) {
				response.put("Field39", "3030");
			} else {
				response.put("Field39", "3535");
			}
			exchange.getOut().setBody(new ISO8583Generator().generateMessage(response));
		} else {
			exchange.getOut().setBody(null);
		}
	}

}
