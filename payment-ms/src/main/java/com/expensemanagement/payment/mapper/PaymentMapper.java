package com.expensemanagement.payment.mapper;

import org.mapstruct.Mapper;

import com.expensemanagement.payment.dto.PaymentDto;
import com.expensemanagement.payment.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	
	Payment toEntity(PaymentDto paymentDto);
	PaymentDto toDto(Payment payment);
	
}
