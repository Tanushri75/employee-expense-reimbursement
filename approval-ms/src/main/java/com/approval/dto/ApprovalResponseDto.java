package com.approval.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApprovalResponseDto {
	
	private Long managerId;
	private String remarks;
	private String status;

}
