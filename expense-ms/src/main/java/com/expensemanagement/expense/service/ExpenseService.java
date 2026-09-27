package com.expensemanagement.expense.service;

import java.util.List;

import com.expensemanagement.expense.dto.ExpenseDto;
import com.expensemanagement.expense.dto.ExpenseResponse;
import com.expensemanagement.expense.dto.UpdateStatusDto;

public interface ExpenseService {
	
	public ExpenseResponse createExpense(ExpenseDto expenseRequest);
	
	public ExpenseDto getExpenseById(Long id);
	
	public List<ExpenseDto> getExpensesByEmployeeId(Long employeeId);
	
	public void updateExpenseStatus(Long id, UpdateStatusDto updateStatusDto);
}
