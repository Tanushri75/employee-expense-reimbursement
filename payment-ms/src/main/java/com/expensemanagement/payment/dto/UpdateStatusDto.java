package com.expensemanagement.payment.dto;

import com.expensemanagement.payment.enums.PaymentStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private PaymentStatus status;
}
