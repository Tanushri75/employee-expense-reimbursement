package com.expensemanagement.expense.mapper;

import org.mapstruct.Mapper;

import com.expensemanagement.expense.dto.ExpenseDto;
import com.expensemanagement.expense.entity.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
	
	ExpenseDto toExpenseDto(Expense expense);
	Expense toEntity(ExpenseDto expenseDto);

}
