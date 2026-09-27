package com.payment.entity;

import java.time.LocalDate;

import com.payment.enums.PaymentStatus;

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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_table")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "expense_id", unique = true, nullable = false)
	private Long expenseId;
	@Column(name = "employee_id", nullable = false)
	private Long employeeId;
	@Column(name = "amount", nullable = false)
	private Double amount;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;
	@Column(name = "updated_at", nullable = false)
	private LocalDate updatedAt;
}
