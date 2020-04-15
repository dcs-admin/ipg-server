package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Register;
import com.rama.ipg.model.Requirement;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.repository.RequirementRepository;
import com.rama.ipg.service.RegisterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class RegisterController {
	
	private Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private RequirementRepository requirementRepository;
	
	
	@Autowired
	private RegisterService registerService;
	 
	
	@PostMapping("/register") 
	public Register saveRegister(@RequestBody Register  register) {
		
		logger.info("IN::/register:"+register);
		
		 this.registerService.doRegister(register);
		
		register = registerRepository.save(register); 
		
		logger.info("Out::/register:"+register);
		return register;
	}
	
	@PostMapping("/register/otp") 
	public Register sendOTP(@RequestBody Register  register) {
		
		logger.info("IN::/register/otp/"+register);
		
		//String otp = "1234"; 
		register = this.registerService.sendOTP(register); 
		//Register register = new Register();
		//register.setMobileNumber(Long.parseLong(mobileNumber));
		//register.setOtp(otp); 
		register = registerRepository.save(register); 
	 
		logger.info("Out:::/register/otp/:"+register.getOtp());
		return register;
	}
	
	
	@PostMapping("/register/requirement") 
	public Requirement saveRequirement(@RequestBody Requirement  requirement) {
		 
		requirement = requirementRepository.save(requirement);
		System.out.println("obj::"+requirement); 
		
		return requirement;
	}
	 
	

}
