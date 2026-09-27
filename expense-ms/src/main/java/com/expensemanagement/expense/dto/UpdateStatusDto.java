package com.expensemanagement.expense.dto;

import com.expensemanagement.expense.enums.Status;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private Status status;
}
