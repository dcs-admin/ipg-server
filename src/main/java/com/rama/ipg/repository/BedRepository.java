package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Bed;
import com.rama.ipg.model.Tenant;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {

	@Query(value="SELECT b FROM Bed b WHERE b.tenantId=?1") 
	public Bed getBed(String tid);
	
	  
 }
