package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
 

	@Query(value="SELECT t FROM Supervisor t WHERE t.phoneNumber=?1 and t.password=?2") 
	public Supervisor getSupervisorWithPwd(Long tid, String pwd);
	 
	
	@Query(value="SELECT t FROM Supervisor t WHERE t.phoneNumber=?1 and t.ownerId=?2") 
	public Supervisor getSupervisorByPhoneAndOwner(Long phoneNumber, Long ownerId);
	
	
	@Query(value="SELECT t FROM Supervisor t WHERE t.ownerId=?1") 
	public List<Supervisor> getSupervisorsForOwner(Long ownerId);
	
	@Query(value="SELECT s FROM Supervisor s WHERE s.phoneNumber=?1 ") 
	public Supervisor findByPhoneNumber(Long phoneNumber); 
	
	
	@Query(value="DELETE FROM Supervisor t WHERE t.phoneNumber=?1 ") 
	public Supervisor deleteByPhoneNumber(Long phoneNumber); 
	
	  
 }
