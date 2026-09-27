package com.payment.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.payment.dto.ErrorResponse;
import com.payment.exception.DuplicatePaymentException;
import com.payment.exception.ExpenseNotFoundException;
import com.payment.exception.PaymentFailedException;

@RestControllerAdvice
public class PaymentControllerAdvice {

	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<com.payment.dto.ErrorResponse> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
		com.payment.dto.ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(DuplicatePaymentException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateExpenseException(DuplicatePaymentException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.CONFLICT)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.CONFLICT.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<ErrorResponse> handlePaymentFailedException(PaymentFailedException ex) {
		com.payment.dto.ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<com.payment.dto.ErrorResponse> handleInvalidRequest(HttpRequestMethodNotSupportedException ex) {
		com.payment.dto.ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
