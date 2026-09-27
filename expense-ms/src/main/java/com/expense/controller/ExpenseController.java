package com.expense.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.dto.ExpenseDto;
import com.expense.dto.ExpenseResponse;
import com.expense.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
	
	private final ExpenseService expenseService;
	
	@PostMapping
	public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseDto expenseRequest) {
		ExpenseResponse expenseResponse = expenseService.createExpense(expenseRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(expenseResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
		ExpenseDto expenseDto = expenseService.getExpenseById(id);
		return ResponseEntity.status(HttpStatus.OK).body(expenseDto);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<ExpenseDto>> getExpensesByEmployeeId(@PathVariable Long employeeId) {
		List<ExpenseDto> expenses = expenseService.getExpensesByEmployeeId(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(expenses);
	}
		
}
