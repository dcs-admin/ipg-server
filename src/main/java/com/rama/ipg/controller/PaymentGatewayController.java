package com.rama.ipg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.PaymentGateway;
import com.rama.ipg.model.PaymentGateway;
import com.rama.ipg.repository.PaymentGatewayRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class PaymentGatewayController {
	
	private Logger logger = LoggerFactory.getLogger(PaymentGatewayController.class);
	 
	PaymentGateway paymentGateway = new PaymentGateway();
	
	
	@Autowired
	private PaymentGatewayRepository paymentGatewayRepository;
	
	public PaymentGatewayController(){
		this.paymentGateway.setId(1l);
		this.paymentGateway.setKey("rzp_test_1SAEXeH3t4EtTn");
	}
	
	
	@GetMapping("/payment-gateway-info") 
	public PaymentGateway getPaymentGatewayInfo() {
		
		logger.info("In::/payment-gateway-info"); 
		PaymentGateway paymentGateway = null;
		
		List<PaymentGateway> paymentGateways = paymentGatewayRepository.findAll();
		
		if(paymentGateways != null && paymentGateways.size() > 0){
			paymentGateway = paymentGateways.get(0);
		}else{
			paymentGateway = this.paymentGateway;
		}
		
		logger.info("Out::"+paymentGateway);
		return paymentGateway;
	} 

}
