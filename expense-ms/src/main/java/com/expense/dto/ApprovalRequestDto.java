package com.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ApprovalRequestDto {
	private Long expenseId;
	private Long employeeId;
	private Double amount;
	
}
