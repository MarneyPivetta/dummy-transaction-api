package com.fiserv.dummy_transaction_api.adapters.inbound.controller;

import com.fiserv.dummy_transaction_api.adapters.inbound.response.ExceptionResponse;
import com.fiserv.dummy_transaction_api.basic.util.Messages;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleRequestValidationExceptions(MethodArgumentNotValidException exception) {

		log.error("Client error (Invalid payload)", exception);

		Map<String, String> errors = new HashMap<>();

		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		return buildResponse(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ExceptionResponse> handlerUnauthorizedRequestExceptions(AuthenticationException exception) {

		log.error("Client error (Unauthorized request)", exception);

		return buildResponse(Messages.CLIENT_UNAUTHORIZED_ERROR, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handlerMethodNotSupportedExceptions(HttpRequestMethodNotSupportedException exception) {

		log.error("Client error (Method not supported)");

		return buildResponse(Messages.NOT_FOUND_ERROR, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> fallbackHandlerDefaultExceptions(Exception exception) {

		log.error("Internal error", exception);

		return buildResponse(Messages.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ResponseEntity<ExceptionResponse> buildResponse(Object errorObject, HttpStatus status) {
		ExceptionResponse response = ExceptionResponse.builder()
				.timestamp(LocalDateTime.now().toString())
				.status(status.value())
				.error(errorObject)
				.build();

		return new ResponseEntity<>(response, status);
	}

}
