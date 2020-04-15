package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	 
	@Query(value="SELECT p FROM Payment p WHERE p.ownerId=?1 and MONTH(p.paidDate)=MONTH(CURRENT_DATE())") 
	public List<Payment> findThisMonthPayments(String ownerId);
	
	@Query(value="SELECT p FROM Payment p WHERE p.ownerId=?1 and MONTH(p.paidDate)=MONTH(CURRENT_DATE())-1") 
	public List<Payment> findLastPayments(String ownerId);
	
	  
 }
