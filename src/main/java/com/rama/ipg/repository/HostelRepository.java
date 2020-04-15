package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

	  
 }
