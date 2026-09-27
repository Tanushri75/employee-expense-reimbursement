package com.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.payment.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	public Optional<Payment> findByExpenseId(Long expenseId);
	
	public List<Payment> findByEmployeeId(Long employeeId);
	
	@Modifying
	@Query("UPDATE Payment p Set p.updatedAt = CURRENT_DATE WHERE p.id = :id")
	public int modifyUpdatedAt(Long id);
}
