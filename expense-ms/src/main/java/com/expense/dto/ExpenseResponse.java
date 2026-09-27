package com.expense.dto;

import java.time.LocalDateTime;

import com.expense.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpenseResponse {	
	
	private Long id;
	private Status status;
	private LocalDateTime createdAt;	

}
