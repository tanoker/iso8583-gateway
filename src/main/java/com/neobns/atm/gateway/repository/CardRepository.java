package com.neobns.atm.gateway.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neobns.atm.gateway.model.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    public List<Card> findByCardNumber(String cardNumber);

}
