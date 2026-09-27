package com.expensemanagement.approval.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
	
	private HttpStatus status;
	private String errorMessage;
	private int errorCode;
	private LocalDateTime timestamp;	
	
}
