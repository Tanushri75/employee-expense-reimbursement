package com.expensemanagement.expense.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expensemanagement.expense.client.ApprovalClient;
import com.expensemanagement.expense.dto.ApprovalRequestDto;
import com.expensemanagement.expense.dto.ExpenseDto;
import com.expensemanagement.expense.dto.ExpenseResponse;
import com.expensemanagement.expense.dto.UpdateStatusDto;
import com.expensemanagement.expense.entity.Expense;
import com.expensemanagement.expense.enums.Status;
import com.expensemanagement.expense.exception.ExpenseNotFoundException;
import com.expensemanagement.expense.mapper.ExpenseMapper;
import com.expensemanagement.expense.repository.ExpenseRepository;
import com.expensemanagement.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	private final ExpenseMapper expenseMapper;
	private final ApprovalClient approvalClient;
	
	@Value("${expense.not.found}")
	private String expenseNotFound;

	@Override
	public ExpenseResponse createExpense(ExpenseDto expenseRequest) {
		Expense expense = expenseMapper.toEntity(expenseRequest);
		expense.setCreatedAt(LocalDateTime.now());
		expense.setStatus(Status.CREATED);
		expenseRepository.save(expense);
		ExpenseResponse expenseResponse = new ExpenseResponse();
		expenseResponse.setId(expense.getId());
		expenseResponse.setCreatedAt(expense.getCreatedAt());
		expenseResponse.setStatus(expense.getStatus());
		
		// Send approval request to approval-ms
		ApprovalRequestDto approvalRequestDto = ApprovalRequestDto.builder()
				.expenseId(expense.getId())
				.employeeId(expense.getEmployeeId())
				.amount(expense.getAmount())
				.build();
		
		sendForApproval(approvalRequestDto);
		
		return expenseResponse;	
	}

	@Override
	public ExpenseDto getExpenseById(Long id) {
		Expense expense = expenseRepository.findById(id)
			.orElseThrow(() -> new ExpenseNotFoundException(String.format(expenseNotFound,id)));
		return expenseMapper.toExpenseDto(expense);	
	}

	@Override
	public List<ExpenseDto> getExpensesByEmployeeId(Long employeeId) {
		List<Expense> expenses = expenseRepository.findByEmployeeId(employeeId);	
		return expenses.stream()
			.map(expenseMapper::toExpenseDto)
			.toList();
	}

	@Override
	public void updateExpenseStatus(Long id, UpdateStatusDto updateStatusDto) {
		expenseRepository.findById(id)
					.ifPresentOrElse(expense -> {
						expense.setStatus(updateStatusDto.getStatus());
						expenseRepository.save(expense);					
					},
					() -> {
						throw new ExpenseNotFoundException(String.format(expenseNotFound, id));
					});	
	}
	
	public void sendForApproval(ApprovalRequestDto approvalRequestDto) {
		approvalClient.createApprovalRequest(approvalRequestDto);
	}
	
}
