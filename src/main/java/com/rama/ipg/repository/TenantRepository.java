package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Tenant; 

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	@Query(value="SELECT t FROM Tenant t WHERE t.phoneNumber=?1 and t.password=?2") 
	public Tenant getTenant(Long phoneNumber, String pwd);
	
	@Query(value="SELECT t FROM Tenant t WHERE t.id=?1 and t.ownerId=?2") 
	public Tenant getTenantByIdAndOwner(Long id, Long ownerId);
	
	
	@Query(value="SELECT t FROM Tenant t WHERE t.phoneNumber=?1 and t.ownerId=?2") 
	public Tenant getTenantByPhoneAndOwner(Long phoneNumber, Long ownerId);
	
	
	@Query(value="SELECT t FROM Tenant t WHERE t.ownerId=?1") 
	public List<Tenant> getTenantsByOwnerId(Long ownerId);
	
	@Query(value="SELECT t FROM Tenant t WHERE t.supervisorId=?1") 
	public List<Tenant> getTenantsBySupervisorId(Long supervisorId);
	  
 }
