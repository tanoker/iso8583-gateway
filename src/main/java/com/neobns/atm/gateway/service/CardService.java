package com.neobns.atm.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neobns.atm.gateway.model.Card;
import com.neobns.atm.gateway.repository.CardRepository;

/**
 * 
 * @author Pavel Gaiduk
 *
 * The {@code CardService} class providing functionality to perform operations with cards.
 */
@Service
public class CardService {

	@Autowired 
	private CardRepository cardRepository;
	
	/**
	 * 
	 * @param cardNumber Number of the card
	 * @return The Card object from database for the given card number or null if there is no card with given number
	 */
	public Card getCardByNumber(String cardNumber) {
		List<Card> cardsFromDB = cardRepository.findByCardNumber(cardNumber);
		if(cardsFromDB.size() > 0) {
			return cardsFromDB.get(0);
		}
		return null;
	}
	
}
