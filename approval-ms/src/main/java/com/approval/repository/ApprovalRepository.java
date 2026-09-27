package com.approval.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.approval.entity.ApprovalRequest;

public interface ApprovalRepository extends JpaRepository<ApprovalRequest, Long> {
	
	public Optional<ApprovalRequest> findByExpenseId(Long expenseId);
	
	public List<ApprovalRequest> findByEmployeeId(Long employeeId);

}
