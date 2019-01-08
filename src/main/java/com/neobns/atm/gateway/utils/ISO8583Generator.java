package com.neobns.atm.gateway.utils;

import java.util.Map;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

/**
 * 
 * @author Pavel Gaiduk
 *
 * The {@code ISO8583Generator} class providing functionality to transform java.util.Map object to ISO8583 message. 
 */
public class ISO8583Generator {

	/**
	 * 
	 * @param message java.util.Map Contains fields values for ISO8583 message. Every key is of the form FieldX where X is a number of the field.
	 * @return String message in ISO8583 format
	 */
	public String generateMessage(Map<Object, Object> message) {
		try {
			String messageType = message.get("Field0").toString();
			message.put("Field7", DateUtils.currentDateTime("MMddHHmmss"));
			GenericPackager packager = new GenericPackager(this.getClass().getClassLoader().getResourceAsStream("templates/" + messageType + ".xml"));
			ISOMsg isoMsg = new ISOMsg();
			isoMsg.setPackager(packager);
			for(int i = 0; i < 200; i++) {
				if(packager.getFieldPackager(i) == null) continue;
				if(message.containsKey("Field" + i)) {
					isoMsg.set(i, message.get("Field" + i).toString());
				}
			}
			byte[] bytes = isoMsg.pack();
			return new String(bytes).concat("\r\n");
		} catch (ISOException e) {
			System.out.println(e.getMessage());
		}
		return "\r\n";		
	}

}
