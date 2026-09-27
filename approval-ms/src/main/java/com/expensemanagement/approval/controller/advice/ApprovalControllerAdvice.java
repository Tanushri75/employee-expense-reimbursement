package com.expensemanagement.approval.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expensemanagement.approval.dto.ErrorResponse;
import com.expensemanagement.approval.exception.ApprovalRequestNotFoundException;

@RestControllerAdvice
public class ApprovalControllerAdvice {
	
	@ExceptionHandler(ApprovalRequestNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleApprovalRequestNotFoundException(ApprovalRequestNotFoundException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.errorMessage(ex.getMessage())
				.errorCode(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleInvalidStatusException(IllegalArgumentException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errorMessage("You can either Approve or Reject the request")
				.errorCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleInvalidStatusException(HttpMessageNotReadableException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(HttpStatus.BAD_REQUEST)
				.errorMessage("Something went wrong while reading your request")
				.errorCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.build();	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
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
