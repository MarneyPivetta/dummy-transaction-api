package com.fiserv.dummy_transaction_api.basic.validation;

import com.fiserv.dummy_transaction_api.basic.util.Messages;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {

	String message() default Messages.INVALID_DATE_PARAMETER;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
