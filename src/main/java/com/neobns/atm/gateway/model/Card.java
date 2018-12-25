package com.neobns.atm.gateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Pavel Gaiduk
 * The {@code Card} class is the entity for Card table
 */
@Entity(name="Card")
@Table(name="Card")
public class Card {

	@Id
	@GeneratedValue
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="PAN")
	private String cardNumber;
	
	@Column(name="PIN")
	private String cardPIN;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardPIN() {
		return cardPIN;
	}
	public void setCardPIN(String cardPIN) {
		this.cardPIN = cardPIN;
	}
	
}
