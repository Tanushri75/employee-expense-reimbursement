package com.approval.entity;

import java.time.LocalDate;

import com.approval.enums.ApprovalStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "approval_table")
public class ApprovalRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "expense_id", nullable = false)
	private Long expenseId;
	@Column(name = "employee_id", nullable = false)
	private Long employeeId;
	@Column(name = "manager_id")
	private Long managerId;
	@Column(name = "remarks")
	private String remarks;
	@Column(name = "amount", nullable = false)
	private Double amount;
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private ApprovalStatus status;
	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;
	
}
