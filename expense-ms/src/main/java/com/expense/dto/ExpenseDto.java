package com.expense.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.expense.enums.Category;
import com.expense.enums.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ExpenseDto {
	
	private Long id;
	private Long employeeId;
	private double amount;
	private Category category;
	private String description;
	private LocalDate expenseDate;
	private Status status;
	private LocalDateTime createdAt;	
	
}
