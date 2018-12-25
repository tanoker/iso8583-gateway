package com.neobns.atm.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neobns.atm.gateway.service.CardService;

/**
 * 
 * @author Pavel Gaiduk
 * Class for configuration of the spring boot application
 */
@Configuration
public class ApplicationConfiguration {

	/**
	 * @return CardService object.
	 */
	@Bean
	public CardService getCardService() {
		CardService serviceToReturn = new CardService();
		return serviceToReturn;
	}
	
}
