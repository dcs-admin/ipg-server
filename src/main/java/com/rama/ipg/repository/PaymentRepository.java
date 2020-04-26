package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	 
	@Query(value="SELECT p FROM Payment p WHERE p.ownerId=?1 and MONTH(p.paidDate)=MONTH(CURRENT_DATE())") 
	public List<Payment> findThisMonthPayments(Long ownerId);
	
	@Query(value="SELECT p FROM Payment p WHERE p.ownerId=?1 and MONTH(p.paidDate)=MONTH(CURRENT_DATE())-1") 
	public List<Payment> findLastPayments(Long ownerId);
	
	
	@Query(value="SELECT p FROM Payment p  WHERE p.ownerId=?1 and MONTH(p.paidDate)=?2 and YEAR(p.paidDate)=?3") 
	public List<Payment> getPaymentsToShowForOwner(Long ownerId,  int month, int year );
	
	@Query(value="SELECT p FROM Payment p WHERE p.ownerId=?1 and p.supervisorId=?2 and MONTH(p.paidDate)=?3 and YEAR(p.paidDate)=?4 ") 
	public List<Payment> getPaymentsToShowForSupervisor(Long ownerId, Long supervisorId, int month, int year);
	
	@Query(value="SELECT p FROM Payment p WHERE p.tenantId=?1 ") 
	public List<Payment> getPaymentHistory(Long tid);
	
	
	  
 }
