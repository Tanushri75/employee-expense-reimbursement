package com.expensemanagement.expense.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expensemanagement.expense.dto.ErrorResponse;
import com.expensemanagement.expense.exception.ExpenseNotFoundException;

@RestControllerAdvice
public class ExpenseControllerAdvice {
	
	@ExceptionHandler(ExpenseNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleExpenseNotFoundException(ExpenseNotFoundException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequest(HttpRequestMethodNotSupportedException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleInvalidStatusException(IllegalArgumentException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errorMessage("Not a valid status")
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
