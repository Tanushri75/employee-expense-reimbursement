package com.expense.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expense.dto.ExpenseDto;
import com.expense.dto.ExpenseResponse;
import com.expense.dto.UpdateStatusDto;
import com.expense.entity.Expense;
import com.expense.enums.Status;
import com.expense.exception.ExpenseNotFoundException;
import com.expense.mapper.ExpenseMapper;
import com.expense.repository.ExpenseRepository;
import com.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	private final ExpenseRepository expenseRepository;
	private final ExpenseMapper expenseMapper;
	
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

}
