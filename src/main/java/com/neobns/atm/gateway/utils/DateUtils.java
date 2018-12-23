package com.neobns.atm.gateway.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String currentDateTime(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return dateFormat.format(cal.getTime());
	}

}
