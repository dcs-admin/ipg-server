package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query(value="SELECT p FROM Expense p WHERE p.ownerId=?1 and MONTH(p.eDate)=MONTH(CURRENT_DATE())") 
	public List<Expense> findThisMonthExpenses(Long ownerId);
	
	@Query(value="SELECT p FROM Expense p WHERE p.ownerId=?1 and MONTH(p.eDate)=MONTH(CURRENT_DATE())-1") 
	public List<Expense> findLastExpenses(Long ownerId);
	
	
	@Query(value="SELECT p FROM Expense p WHERE p.ownerId=?1 and MONTH(p.eDate)=?2 and YEAR(p.eDate)=?3") 
	public List<Expense> getExpensesToShowForOwner(Long ownerId,  int month, int year );
	
	@Query(value="SELECT p FROM Expense p WHERE p.ownerId=?1 and p.supervisorId=?2 and MONTH(p.eDate)=?3 and YEAR(p.eDate)=?4 ") 
	public List<Expense> getExpensesToShowForSupervisor(Long ownerId, Long supervisorId, int month, int year);
	
	  
 }
