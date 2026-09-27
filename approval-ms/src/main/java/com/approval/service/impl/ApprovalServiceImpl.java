package com.approval.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.approval.dto.ApprovalRequestDto;
import com.approval.dto.ApprovalResponseDto;
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
		approvalRepository.findByExpenseId(expenseId).ifPresentOrElse(approvalRequest -> {
			approvalRequest.setStatus(ApprovalStatus.valueOf(approvalResponseDto.getStatus()));
			approvalRequest.setManagerId(approvalResponseDto.getManagerId());
			approvalRequest.setRemarks(approvalResponseDto.getRemarks());
			approvalRepository.save(approvalRequest);
		}, () -> {
			throw new ApprovalRequestNotFoundException(String.format(approvalRequestNotFound, expenseId));
		});
		
	}
	

}
