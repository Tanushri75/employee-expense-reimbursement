package com.expensemanagement.expense.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.expensemanagement.expense.dto.ApprovalRequestDto;


@FeignClient(name = "approval-ms")
public interface ApprovalClient {
	
	@PostMapping("/approvals")
	public ResponseEntity<Void> createApprovalRequest(@RequestBody ApprovalRequestDto approvalRequestDto);
	
}
