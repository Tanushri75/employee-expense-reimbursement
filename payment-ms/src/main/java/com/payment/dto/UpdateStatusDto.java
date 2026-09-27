package com.payment.dto;

import com.payment.enums.PaymentStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private PaymentStatus status;
}
