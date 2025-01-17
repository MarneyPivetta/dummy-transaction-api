package com.fiserv.dummy_transaction_api.basic.validation;

import com.fiserv.dummy_transaction_api.basic.util.DateUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeValidator implements ConstraintValidator<ValidTime, String> {

	@Override
	public boolean isValid(String time, ConstraintValidatorContext constraintValidatorContext) {

		if (time == null || time.isEmpty()) return true;

		try {
			LocalTime.parse(time, DateTimeFormatter.ofPattern(DateUtil.DEFAULT_TIME_FORMAT));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
