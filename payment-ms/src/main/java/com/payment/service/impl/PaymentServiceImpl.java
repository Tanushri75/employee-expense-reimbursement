package com.payment.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.client.ExpenseClient;
import com.payment.dto.PaymentDto;
import com.payment.dto.UpdateStatusDto;
import com.payment.entity.Payment;
import com.payment.enums.PaymentStatus;
import com.payment.exception.DuplicatePaymentException;
import com.payment.exception.ExpenseNotFoundException;
import com.payment.exception.PaymentFailedException;
import com.payment.mapper.PaymentMapper;
import com.payment.repository.PaymentRepository;
import com.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PaymentMapper paymentMapper;
	private final ExpenseClient expenseClient;
	
	@Value("${expense.not.found}")
	private String expenseNotFound;
	@Value("${duplicate.expense.found}")
	private String duplicateExpenseFound;
	@Value("${payment.failed}")
	private String paymentFailed;

	@Override
	public void createPayment(PaymentDto paymentDto) {
		paymentRepository.findByExpenseId(paymentDto.getExpenseId())
						.ifPresent(payment -> {
							throw new DuplicatePaymentException(String.format(duplicateExpenseFound,payment.getExpenseId()));
						});
				
		paymentDto.setStatus(PaymentStatus.PROCESSING);
		paymentDto.setCreatedAt(LocalDate.now());
		paymentDto.setUpdatedAt(LocalDate.now());
		Payment payment = paymentMapper.toEntity(paymentDto);
		paymentRepository.save(payment);
		log.info("Payment request submitted");
		processPayment(payment);			
		
	}

	@Override
	public PaymentDto getPaymentByExpense(Long expenseId) {
		Payment payment = paymentRepository.findByExpenseId(expenseId)
				.orElseThrow(() -> new ExpenseNotFoundException(String.format(expenseNotFound, expenseId)));
		return paymentMapper.toDto(payment);
	}

	@Override
	public List<PaymentDto> getPaymentsByEmployee(Long employeeId) {
		List<Payment> payments = paymentRepository.findByEmployeeId(employeeId);
		return payments.stream().map(paymentMapper::toDto).toList();
	}
	
	@Transactional(rollbackFor = PaymentFailedException.class)
	public void processPayment(Payment payment) {
		log.info("Processing payment worth amount : {}", payment.getAmount());
		try {
			Thread.sleep(5000);
			payment.setStatus(PaymentStatus.REIMBURSED);
			payment.setUpdatedAt(LocalDate.now());
			paymentRepository.save(payment);
		}	
		catch(InterruptedException e) {
			throw new PaymentFailedException(String.format(paymentFailed, payment.getAmount(), payment.getId()));			
		}
		log.info("payment successfully processed");
			
		callExpenseService(payment.getExpenseId(),payment.getStatus());
	}
	
	public void callExpenseService(Long expenseId, PaymentStatus status) {
		UpdateStatusDto updateStatusDto = UpdateStatusDto.builder().status(status).build();
		expenseClient.updateExpenseStatus(expenseId, updateStatusDto);
	}

}
