package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.LicensePayment;
import com.rama.ipg.repository.LicensePaymentRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class LicensePaymentController {
	
	private Logger logger = LoggerFactory.getLogger(LicensePaymentController.class);
	
	@Autowired
	private LicensePaymentRepository licensePaymentRepository;
	 
	
	@PostMapping("/license-payments") 
	public LicensePayment saveRegister(@RequestBody LicensePayment  licensePayment) {
		
		logger.info("IN::/license-payments:"+licensePayment); 
		
		licensePayment = licensePaymentRepository.save(licensePayment); 
		
		logger.info("Out::/license-payments:"+licensePayment); 
		return licensePayment;
	}
	
	 
	 
	

}
