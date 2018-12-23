package com.neobns.atm.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.neobns.atm.gateway.service.CardService;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public CardService getCardService() {
		CardService serviceToReturn = new CardService();
		return serviceToReturn;
	}
	
}
