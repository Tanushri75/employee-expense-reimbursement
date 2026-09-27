package com.expensemanagement.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expensemanagement.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

	@Query("SELECT e FROM Expense e WHERE e.employeeId = :employeeId")
	List<Expense> findByEmployeeId(Long employeeId);

}
