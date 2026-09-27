package com.approval.dto;

import com.approval.enums.ApprovalStatus;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateStatusDto {	
	private ApprovalStatus status;
}
