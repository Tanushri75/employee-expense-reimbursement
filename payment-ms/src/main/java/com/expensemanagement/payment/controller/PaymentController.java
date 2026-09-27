package com.expensemanagement.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensemanagement.payment.dto.PaymentDto;
import com.expensemanagement.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<Void> createPayment(@RequestBody PaymentDto paymentDto){
		paymentService.createPayment(paymentDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{expenseId}")
	public ResponseEntity<PaymentDto> getPaymentByExpense(@PathVariable Long expenseId){
		PaymentDto response = paymentService.getPaymentByExpense(expenseId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<List<PaymentDto>> getPaymentsByEmployee(@PathVariable Long employeeId){
		List<PaymentDto> response = paymentService.getPaymentsByEmployee(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
