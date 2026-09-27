package com.expensemanagement.approval.service;

import java.util.List;

import com.expensemanagement.approval.dto.ApprovalRequestDto;
import com.expensemanagement.approval.dto.ApprovalResponseDto;

public interface ApprovalService {

	public void createApprovalRequest(ApprovalRequestDto approvalRequestDto);
	
	public ApprovalRequestDto getExpense(Long expenseId);
	
	public List<ApprovalRequestDto> getExpensesByEmployee(Long employeeId);
	
	public void processApprovalRequest(Long expenseId, ApprovalResponseDto approvalStatusDto);
	
}
