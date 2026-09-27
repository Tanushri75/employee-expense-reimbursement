package com.expense.dto;

import com.expense.enums.Status;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private Status status;
}
