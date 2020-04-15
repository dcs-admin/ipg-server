package com.rama.ipg.repository;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rama.ipg.model.LicensePayment;

@Repository
public interface LicensePaymentRepository extends JpaRepository<LicensePayment, Long> {

	  
 }
