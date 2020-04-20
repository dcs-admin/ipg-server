package com.rama.ipg.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.constants.IPGConstants;
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
	
	
	@GetMapping("/payments/{role}") 
	public List<Payment> getPayments(
			@PathVariable("role") String role,
			@RequestParam("ownerId") String ownerId ,
			@RequestParam("supervisorId") String supervisorId,
			@RequestParam("year") int year ,
			@RequestParam("month") int month
			) {
		
		logger.info("In::/hostels/{role}"+role+";o"+ownerId+";s"+supervisorId+";y"+year+";m"+month); 
		
		List<Payment> payments = new ArrayList<Payment>();
		if(role.equals(IPGConstants.OWNER_CODE)){
			
			payments = paymentRepository.getPaymentsToShowForOwner(ownerId, month, year);
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			payments = paymentRepository.getPaymentsToShowForSupervisor(ownerId, supervisorId, month, year);
			
		} 
		
		logger.info("Out::"+payments);
		return payments;
	}
	
	

}
