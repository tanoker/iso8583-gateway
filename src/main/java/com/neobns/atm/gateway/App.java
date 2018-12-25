package com.neobns.atm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Pavel Gaiduk
 * Class is to run spring boot application
 */
@SpringBootApplication
public class App {
    
	/**
	 * 
	 * @param args Command line arguments
	 */
	public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
}