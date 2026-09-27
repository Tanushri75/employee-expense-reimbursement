package com.expensemanagement.approval.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensemanagement.approval.dto.ApprovalRequestDto;
import com.expensemanagement.approval.dto.ApprovalResponseDto;
import com.expensemanagement.approval.service.ApprovalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/approvals")
public class ApprovalController {
	
	private final ApprovalService approvalService;
	
	@PostMapping
	public ResponseEntity<Void> createApprovalRequest(@RequestBody ApprovalRequestDto approvalRequestDto) {
		approvalService.createApprovalRequest(approvalRequestDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{expenseId}")
	public ResponseEntity<ApprovalRequestDto> getExpense(@PathVariable Long expenseId){
		ApprovalRequestDto response = approvalService.getExpense(expenseId);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<ApprovalRequestDto>> getExpensesByEmployee(@PathVariable Long employeeId){
		List<ApprovalRequestDto> response = approvalService.getExpensesByEmployee(employeeId);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{expenseId}")
	public ResponseEntity<Void> processApprovalRequest(@PathVariable Long expenseId, @RequestBody ApprovalResponseDto approvalResponseDto) {
		approvalService.processApprovalRequest(expenseId, approvalResponseDto);
		return ResponseEntity.noContent().build();
	}
	
	
}
