package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Payment;
import com.rama.ipg.repository.PaymentRepository;
import com.rama.ipg.service.PaymentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class PaymentController {
	
	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/payments") 
	public Payment savePayment(@RequestBody Payment  payment) {
		logger.info("In::"); 
		
		payment = paymentRepository.save(payment);
		
		paymentService.sendAlerts(payment);
		
		logger.info("Out::"+payment); 
		
		return payment;
	}
	
	

}
