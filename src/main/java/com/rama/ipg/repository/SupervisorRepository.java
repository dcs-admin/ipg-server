package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
 

	@Query(value="SELECT t FROM Supervisor t WHERE t.phoneNumber=?1 and t.password=?2") 
	public Supervisor getSupervisorWithPwd(String tid, String pwd);
	 
	
	@Query(value="SELECT t FROM Supervisor t WHERE t.phoneNumber=?1 and t.ownerId=?2") 
	public Supervisor getSupervisorByPhoneAndOwner(String phoneNumber, String ownerId);
	
	
	@Query(value="SELECT t FROM Supervisor t WHERE t.ownerId=?1") 
	public List<Supervisor> getSupervisorsForOwner(String ownerId);
	
	
	
	@Query(value="DELETE FROM Supervisor t WHERE t.phoneNumber=?1 ") 
	public Supervisor deleteByPhoneNumber(String phoneNumber); 
	
	  
 }
