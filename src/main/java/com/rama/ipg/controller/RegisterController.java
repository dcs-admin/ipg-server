package com.rama.ipg.controller;

import java.util.List;
import java.util.Optional;

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
import com.rama.ipg.model.ChangePasswd;
import com.rama.ipg.model.Register;
import com.rama.ipg.model.Requirement;
import com.rama.ipg.model.Supervisor;
import com.rama.ipg.model.Tenant;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.repository.RequirementRepository;
import com.rama.ipg.repository.SupervisorRepository;
import com.rama.ipg.repository.TenantRepository;
import com.rama.ipg.service.RegisterService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class RegisterController {
	
	private Logger logger = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private RequirementRepository requirementRepository;
	
	
	@Autowired
	private RegisterService registerService;
	 
	
	@PostMapping("/register") 
	public Register saveRegister(@RequestBody Register  register) {
		
		logger.info("IN::/register:"+register); 
		
		register = this.registerService.doRegister(register);
		
		register = registerRepository.save(register); 
		
		logger.info("Out::/register:"+register);
		return register;
	}
	
	@PostMapping("/register/otp") 
	public Register sendOTP(@RequestBody Register  register) throws Exception {
		
		logger.info("IN::/register/otp/"+register);
		
		if(registerRepository.findById(register.getMobileNumber()).isPresent()){
			throw new Exception("This mobileNumber already registered, please choose diffrent one.");
		}
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
		 
		logger.info("In::");
		requirement = requirementRepository.save(requirement);
		//System.out.println("obj::"+requirement); 
		logger.info("Out::"+requirement);
		return requirement;
	}
	

	
	@GetMapping("/subscriptions") 
	public List<Register> getAllSubscriptions( ) {
		
		logger.info("In::/subscriptions:");
		 
		List<Register> subscriptions =  registerRepository.findAll();
		
		logger.info("Out::"+subscriptions);
		
		return subscriptions;
	}
	
	@PostMapping("/register/changepassword") 
	public String changePassword(@RequestBody ChangePasswd  changePasswd) throws Exception{
		  
		logger.info("In::"+changePasswd);
		Optional<?> optional = null; 
		
		if(changePasswd.getRole().equals(IPGConstants.OWNER_CODE)){
			
			Register register  = registerRepository.findByPhoneNumber(changePasswd.getUserId());
			if( register != null){	 
				
				if(!register.getPassword().equals(changePasswd.getCurrPassword() )){
					throw new Exception("Current Password not matched");
				}
				register.setPassword(changePasswd.getPassword()); 
				registerRepository.save(register); 
			}
			
		}else if(changePasswd.getRole().equals(IPGConstants.SUPERVISOR_CODE)){
			
			optional = supervisorRepository.findById(changePasswd.getUserId());
			if(optional.isPresent()){
				Supervisor supervisor = (Supervisor) optional.get();
				if(!supervisor.getPassword().equals(changePasswd.getCurrPassword() )){
					throw new Exception("Current Password not matched");
				}
				
				supervisor.setPassword(changePasswd.getPassword()); 
				supervisorRepository.save(supervisor); 
			}
			
		}else if(changePasswd.getRole().equals(IPGConstants.TENANT_CODE)){
			
			optional = tenantRepository.findById(changePasswd.getUserId());
			if(optional.isPresent()){
				Tenant tenant = (Tenant) optional.get();
				if(!tenant.getPassword().equals(changePasswd.getCurrPassword() )){
					throw new Exception("Current Password not matched");
				}
				
				tenant.setPassword(changePasswd.getPassword()); 
				tenantRepository.save(tenant); 
			} 
			
		} 
		return "success";  
	}
	 
	@GetMapping("/register/{ownerId}") 
	public Register getRegister(
			@PathVariable("ownerId") Long ownerId
			) {
		
		logger.info("In::/register/{mobileNumber}"+ownerId); 
		
		Register register = registerRepository.findByPhoneNumber(ownerId); 
		
		logger.info("Out::"+register);
		return register;
	}
	

	@GetMapping("/register/byrole/{role}") 
	public Register getRegisterWithOptions(
			@PathVariable("role") String role,
			@RequestParam("ownerId") Long ownerId ,
			@RequestParam("supervisorId") Long supervisorId
			) {
		
		logger.info("In::/register/{role}"+role+";o"+ownerId+";s"+supervisorId); 
		
		Register register  = null;
		if(role.equals(IPGConstants.OWNER_CODE)){
			
			register  = registerRepository.findByPhoneNumber(ownerId);
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			Optional<?>  optional = supervisorRepository.findById(supervisorId);
			if(optional.isPresent()){
				Supervisor supervisor = (Supervisor) optional.get();
				register  = registerRepository.findByPhoneNumber(supervisor.getOwnerId()); 
			} 
		}
		
		logger.info("Out::"+register);
		return register;
	}
	

}
