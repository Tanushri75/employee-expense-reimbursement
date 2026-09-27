package com.expensemanagement.approval.mapper;

import org.mapstruct.Mapper;

import com.expensemanagement.approval.dto.ApprovalRequestDto;
import com.expensemanagement.approval.entity.ApprovalRequest;

@Mapper(componentModel = "spring")
public interface ApprovalRequestMapper {
	
	ApprovalRequestDto toDto(ApprovalRequest approvalRequest);

	ApprovalRequest toEntity(ApprovalRequestDto approvalRequestDto);
	

}
