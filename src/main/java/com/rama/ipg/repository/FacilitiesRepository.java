package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Facilities;

@Repository
public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {

	@Query(value="SELECT f FROM Facilities f WHERE f.hostelId=?1") 
	public Facilities findByHostelId(Long hostelId); 
 }
