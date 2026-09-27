package com.expense.service;

import java.util.List;

import com.expense.dto.ExpenseDto;
import com.expense.dto.ExpenseResponse;

public interface ExpenseService {
	
	public ExpenseResponse createExpense(ExpenseDto expenseRequest);
	
	public ExpenseDto getExpenseById(Long id);
	
	public List<ExpenseDto> getExpensesByEmployeeId(Long employeeId);
	
}
