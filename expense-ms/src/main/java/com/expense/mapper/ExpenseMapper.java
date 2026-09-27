package com.expense.mapper;

import org.mapstruct.Mapper;

import com.expense.dto.ExpenseDto;
import com.expense.entity.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
	
	ExpenseDto toExpenseDto(Expense expense);
	Expense toEntity(ExpenseDto expenseDto);

}
