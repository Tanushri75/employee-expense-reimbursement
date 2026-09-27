package com.expense.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDto;
import com.expense.dto.ExpenseResponse;
import com.expense.dto.UpdateStatusDto;
import com.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping("/expenses")
	public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseDto expenseRequest) {
		ExpenseResponse expenseResponse = expenseService.createExpense(expenseRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(expenseResponse);
	}
	
	@GetMapping("/expenses/{id}")
	public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
		ExpenseDto expenseDto = expenseService.getExpenseById(id);
		return ResponseEntity.status(HttpStatus.OK).body(expenseDto);
	}
	
	@GetMapping("/expenses/employee/{employeeId}")
	public ResponseEntity<List<ExpenseDto>> getExpensesByEmployeeId(@PathVariable Long employeeId) {
		List<ExpenseDto> expenses = expenseService.getExpensesByEmployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(expenses);
	}
	
	@PatchMapping("/internal/expenses/{id}")
	public ResponseEntity<Void> updateExpenseStatus(@PathVariable Long id, @RequestBody UpdateStatusDto updateStatusDto){
		expenseService.updateExpenseStatus(id, updateStatusDto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
		
}
