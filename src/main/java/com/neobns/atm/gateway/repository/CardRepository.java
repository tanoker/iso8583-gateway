package com.neobns.atm.gateway.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neobns.atm.gateway.model.Card;

/**
 * 
 * @author Pavel Gaiduk
 * Interface describing signatures of methods to work with Card class/table and additional method to get cards list by card number
 */
@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

	/**
	 * 
	 * @param cardNumber Number of the card
	 * @return List of the Card objects for given card number
	 */
    public List<Card> findByCardNumber(String cardNumber);

}
