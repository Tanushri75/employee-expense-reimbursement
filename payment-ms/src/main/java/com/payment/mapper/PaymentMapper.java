package com.payment.mapper;

import org.mapstruct.Mapper;

import com.payment.dto.PaymentDto;
import com.payment.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
	
	Payment toEntity(PaymentDto paymentDto);
	PaymentDto toDto(Payment payment);
	
}
