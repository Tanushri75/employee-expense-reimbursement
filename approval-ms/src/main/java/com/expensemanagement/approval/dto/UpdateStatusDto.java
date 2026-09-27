package com.expensemanagement.approval.dto;

import com.expensemanagement.approval.enums.ApprovalStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private ApprovalStatus status;
}
