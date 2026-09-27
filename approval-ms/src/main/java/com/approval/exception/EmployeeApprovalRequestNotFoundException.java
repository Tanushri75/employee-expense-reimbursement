package com.approval.exception;

public class EmployeeApprovalRequestNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeApprovalRequestNotFoundException(String message) {
		super(message);
	}

}
