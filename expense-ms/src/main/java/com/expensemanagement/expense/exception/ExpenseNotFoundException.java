package com.expensemanagement.expense.exception;

public class ExpenseNotFoundException extends RuntimeException {

	static final long serialVersionUID = 1L;

	public ExpenseNotFoundException(String message) {
		super(message);
	}

}
