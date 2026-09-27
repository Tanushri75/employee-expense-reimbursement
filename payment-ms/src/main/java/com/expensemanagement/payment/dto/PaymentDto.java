package com.expensemanagement.payment.dto;

import java.time.LocalDate;

import com.expensemanagement.payment.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
	private Long id;
	private Long expenseId;
	private Long employeeId;
	private Double amount;
	private PaymentStatus status;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
