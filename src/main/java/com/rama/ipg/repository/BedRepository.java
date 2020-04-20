package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Bed;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {

	@Query(value="SELECT b FROM Bed b WHERE b.tenantId=?1") 
	public Bed findBedByTenant(Long tid);
	
	
	@Query(value="SELECT b FROM Bed b WHERE b.hostelId=?1 and b.roomId=?2 ") 
	public List<Bed> getBeds(Long hostelId, Long roomId); 
	
	  
	@Modifying
	@Query("delete from Bed b where b.hostelId=?1")
	public void deleteByHostelId(Long hostelId);
 }
