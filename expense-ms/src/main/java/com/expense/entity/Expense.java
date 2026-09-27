package com.expense.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.expense.enums.Category;
import com.expense.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expense_table")
public class Expense{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "employee_id", nullable = false)
	private Long employeeId;
	@Column(name = "amount", nullable = false)
	private double amount;
	@Column(name = "category", nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "expense_date", nullable = false)
	private LocalDate expenseDate;
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;	
	
}
