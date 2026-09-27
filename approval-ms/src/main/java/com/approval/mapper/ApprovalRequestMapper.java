package com.approval.mapper;

import org.mapstruct.Mapper;

import com.approval.dto.ApprovalRequestDto;
import com.approval.entity.ApprovalRequest;

@Mapper(componentModel = "spring")
public interface ApprovalRequestMapper {
	
	ApprovalRequestDto toDto(ApprovalRequest approvalRequest);

	ApprovalRequest toEntity(ApprovalRequestDto approvalRequestDto);
	

}
