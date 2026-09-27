package com.payment.service;

import java.util.List;

import com.payment.dto.PaymentDto;

public interface PaymentService {
	
	public void createPayment(PaymentDto paymentDto);
	public PaymentDto getPaymentByExpense(Long expenseId);
	public List<PaymentDto> getPaymentsByEmployee(Long employeeId);
	
}
