package com.hotel.app.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 1️⃣ Validation errors (@Valid)
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(
			org.springframework.web.bind.MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	// 2️⃣ JSON parse / null / malformed input errors
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> handleJsonParseErrors(HttpMessageNotReadableException ex) {

		Map<String, String> error = new HashMap<>();

		Throwable cause = ex.getCause();

		if (cause instanceof MismatchedInputException mie) {
			error.put("error", "Invalid request payload: null or wrong data type provided");
		} else {
			error.put("error", "Malformed JSON request");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
