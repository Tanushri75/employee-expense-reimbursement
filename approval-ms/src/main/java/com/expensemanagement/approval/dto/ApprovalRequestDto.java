package com.expensemanagement.approval.dto;

import java.time.LocalDate;

import com.expensemanagement.approval.enums.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class ApprovalRequestDto {
	private Long id;
	private Long expenseId;
	private Long employeeId;
	private Double amount;
	private ApprovalStatus status;
	private Long managerId;
	private String remarks;
	private LocalDate createdDate;
	
}
