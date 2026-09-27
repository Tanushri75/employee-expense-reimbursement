package com.expensemanagement.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.expensemanagement.payment.dto.UpdateStatusDto;


@FeignClient(name = "expense-ms", url = "${expense-ms.base.url}")
public interface ExpenseClient {
	
	@PatchMapping("/internal/expenses/{id}")
	public ResponseEntity<Void> updateExpenseStatus(@PathVariable Long id, @RequestBody UpdateStatusDto updateStatusDto);
	
}
