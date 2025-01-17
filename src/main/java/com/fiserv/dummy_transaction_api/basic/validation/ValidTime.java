package com.fiserv.dummy_transaction_api.basic.validation;

import com.fiserv.dummy_transaction_api.basic.util.Messages;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTime {
	String message() default Messages.INVALID_DATE_PARAMETER;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
