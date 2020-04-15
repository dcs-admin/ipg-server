package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Tenant; 

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	@Query(value="SELECT t FROM Tenant t WHERE t.phoneNumber=?1 and t.password=?2") 
	public Tenant getTenant(String tid, String pwd);
	
	@Query(value="SELECT t FROM Tenant t WHERE t.id=?1 and t.ownerId=?2") 
	public Tenant getTenantByIdAndOwner(String id, String ownerId);
	
	
	@Query(value="SELECT t FROM Tenant t WHERE t.phoneNumber=?1 and t.ownerId=?2") 
	public Tenant getTenantByPhoneAndOwner(String phoneNumber, String ownerId);
	  
 }