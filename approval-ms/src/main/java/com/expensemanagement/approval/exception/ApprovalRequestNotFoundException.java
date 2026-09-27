package com.expensemanagement.approval.exception;

public class ApprovalRequestNotFoundException extends RuntimeException {

	static final long serialVersionUID = 1L;

	public ApprovalRequestNotFoundException(String message) {
		super(message);
	}
}
