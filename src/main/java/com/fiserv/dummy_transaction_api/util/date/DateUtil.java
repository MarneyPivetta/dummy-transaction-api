package com.fiserv.dummy_transaction_api.util.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

	public static String addDay(String date, int amount) {
		LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
		LocalDate result = parsed.plusDays(amount);

		return result.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
	}

}
