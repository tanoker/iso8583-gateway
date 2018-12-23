package com.neobns.atm.gateway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neobns.atm.gateway.model.Card;
import com.neobns.atm.gateway.repository.CardRepository;

@Service
public class CardService {

	@Autowired 
	private CardRepository cardRepository;
	
	public Card getCardByNumber(String cardNumber) {
		List<Card> cardsFromDB = cardRepository.findByCardNumber(cardNumber);
		if(cardsFromDB.size() > 0) {
			return cardsFromDB.get(0);
		}
		return null;
	}
	
}
