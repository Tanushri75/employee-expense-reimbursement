package com.expensemanagement.approval.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.expensemanagement.approval.dto.PaymentDto;

@FeignClient(name="payment-ms")
public interface PaymentClient {
	
	@PostMapping("/payments")
	public ResponseEntity<Void> createPayment(@RequestBody PaymentDto paymentDto);
	
}
