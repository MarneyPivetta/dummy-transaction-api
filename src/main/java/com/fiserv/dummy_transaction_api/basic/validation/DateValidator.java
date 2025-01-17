package com.fiserv.dummy_transaction_api.basic.validation;

import com.fiserv.dummy_transaction_api.basic.util.DateUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

	@Override
	public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {

		if (date == null || date.isEmpty()) return true;

		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern(DateUtil.DEFAULT_DATE_FORMAT));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
