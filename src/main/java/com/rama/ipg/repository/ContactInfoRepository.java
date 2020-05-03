package com.rama.ipg.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.ContactInfo; 

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

	@Query(value="SELECT t FROM ContactInfo t WHERE t.hostelId=?1 ") 
	public List<ContactInfo> findByHostelId(Long hostelId );
	
	@Modifying
	@Query("delete from ContactInfo c where c.hostelId=?1")
	public void deleteByHostelId(Long hostelId);
	 
	  
 }
