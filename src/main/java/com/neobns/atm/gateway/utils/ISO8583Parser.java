package com.neobns.atm.gateway.utils;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import com.neobns.atm.gateway.model.Card;

/**
 * 
 * @author Pavel Gaiduk
 *
 *The {@code ISO8583Parser} class providing functionality to transform ISO8583 message in string representation to java.util.Map object.
 *
 */
public class ISO8583Parser {

	/**
	 * 
	 * @param data String representation of the ISO8583 message.
	 * @return com.neobns.atm.gateway.model.Card object including parsed PAN and PIN.
	 */
	public Card parseISOMessageByJPOS(String data) {
		try {
			String messageType = data.substring(0, 4);
			GenericPackager packager = new GenericPackager(this.getClass().getClassLoader().getResourceAsStream("templates/" + messageType + ".xml"));
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			isoMsg.unpack(data.getBytes());
			Card toReturn = new Card();
			toReturn.setCardNumber(isoMsg.getString(2));
			toReturn.setCardPIN(isoMsg.getString(52));
			return toReturn;
		} catch (ISOException e) {
			System.out.println(e.getMessage());
		}
		return null;		
	}

}
