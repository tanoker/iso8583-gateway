package com.neobns.atm.gateway.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Pavel Gaiduk
 * The {@code DateUtils} class providing functionality to get current date for given format
 */
public class DateUtils {

	/**
	 * 
	 * @param format The format of the date to return
	 * @return String representation of the current time for given format
	 */
	public static String currentDateTime(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return dateFormat.format(cal.getTime());
	}

}
