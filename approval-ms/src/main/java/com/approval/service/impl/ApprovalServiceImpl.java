package com.approval.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.approval.client.ExpenseClient;
import com.approval.client.PaymentClient;
import com.approval.dto.ApprovalRequestDto;
import com.approval.dto.ApprovalResponseDto;
import com.approval.dto.PaymentDto;
import com.approval.dto.UpdateStatusDto;
import com.approval.entity.ApprovalRequest;
import com.approval.enums.ApprovalStatus;
import com.approval.exception.ApprovalRequestNotFoundException;
import com.approval.mapper.ApprovalRequestMapper;
import com.approval.repository.ApprovalRepository;
import com.approval.service.ApprovalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {
	
	private final ApprovalRepository approvalRepository;
	private final ApprovalRequestMapper approvalRequestMapper;
	private final ExpenseClient expenseClient;
	private final PaymentClient paymentClient;
	
	@Value("${approval.request.not.found}")
	private String approvalRequestNotFound;
	
	@Override
	public void createApprovalRequest(ApprovalRequestDto approvalRequestDto) {
		ApprovalRequest approvalRequest = approvalRequestMapper.toEntity(approvalRequestDto);
		approvalRequest.setStatus(ApprovalStatus.PENDING);
		approvalRequest.setCreatedDate(LocalDate.now());
		approvalRepository.save(approvalRequest);
	}

	@Override
	public ApprovalRequestDto getExpense(Long expenseId) {
		return approvalRepository.findByExpenseId(expenseId)
			.map(approvalRequestMapper::toDto)
			.orElseThrow(() -> {
				throw new ApprovalRequestNotFoundException(String.format(approvalRequestNotFound, expenseId));
			});		
	}
	
	@Override
	public List<ApprovalRequestDto> getExpensesByEmployee(Long employeeId) {	
		List<ApprovalRequest> approvalRequests = approvalRepository.findByEmployeeId(employeeId);
		return approvalRequests.stream().map(approvalRequest -> approvalRequestMapper.toDto(approvalRequest)).toList();
	}

	@Override
	public void processApprovalRequest(Long expenseId, ApprovalResponseDto approvalResponseDto) {
		Optional<ApprovalRequest> request = approvalRepository.findByExpenseId(expenseId);
		request.ifPresentOrElse(approvalRequest -> { 
			if(approvalRequest.getStatus()	== ApprovalStatus.PENDING) {
				approvalRequest.setStatus(ApprovalStatus.valueOf(approvalResponseDto.getStatus()));
				approvalRequest.setManagerId(approvalResponseDto.getManagerId());
				approvalRequest.setRemarks(approvalResponseDto.getRemarks());
				approvalRepository.save(approvalRequest);
			}
			else{
				throw new RuntimeException(String.format("Approval Request with expenseId: %s is already processed", expenseId));
			}
		}, () -> {
			throw new ApprovalRequestNotFoundException(String.format(approvalRequestNotFound, expenseId));
		});
		
		ApprovalStatus decision = ApprovalStatus.valueOf(approvalResponseDto.getStatus());
		// call expense-ms to update expense status
		UpdateStatusDto updateStatusDto = UpdateStatusDto.builder().status(decision).build();
		expenseClient.updateExpenseStatus(expenseId, updateStatusDto);
		
		//call payment-ms to create a payment request if expense request is approved
		if(decision == (ApprovalStatus.APPROVED)) {
			
			PaymentDto paymentDto = PaymentDto.builder()
					.expenseId(expenseId)
					.employeeId(request.get().getEmployeeId())
					.amount(request.get().getAmount())
					.build();
			paymentClient.createPayment(paymentDto);
		}
		
	}
	

}
