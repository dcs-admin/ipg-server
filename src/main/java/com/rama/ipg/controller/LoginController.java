package com.rama.ipg.controller;

 

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.constants.IPGConstants;
import com.rama.ipg.model.IPGRetObj;
import com.rama.ipg.model.Register;
import com.rama.ipg.model.Supervisor;
import com.rama.ipg.model.Tenant;
import com.rama.ipg.model.TenantWrapper;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.repository.SupervisorRepository;
import com.rama.ipg.repository.TenantRepository;
import com.rama.ipg.service.RegisterService;
import com.rama.ipg.util.PasswordGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private BedRepository bedRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private RegisterService registerService;
	 
	
	@GetMapping("/login") 
	public IPGRetObj login(@RequestParam("id") Long id,@RequestParam("pwd") String pwd, @RequestParam("role") String role ) {
		
		logger.info("In::/login/tid:"+id);
		IPGRetObj iPGRetObj = new IPGRetObj();
		
		if(role.equals(IPGConstants.TENANT_CODE)){
			
			
			Tenant tenant = tenantRepository.getTenant(id, pwd);
			if(tenant != null){
				TenantWrapper tenantWrapper =new TenantWrapper();
				tenantWrapper.setTenant(tenant );
				if(tenantWrapper.getTenant() != null){
					tenantWrapper.setBed(bedRepository.findBedByTenant(tenantWrapper.getTenant().getId()));
				} 
				iPGRetObj.setRetMsg(tenantWrapper);
			}else{
				iPGRetObj.setRetMsg(null);
			} 
			
			
		}else if(role.equals(IPGConstants.OWNER_CODE)){
			
			Register register = registerRepository.getOwner(id, pwd);
			if(register != null){
				Date today = new Date();
				if(today.compareTo(register.getLicenceUpTo()) > 0){
					register.setErrMsg("Subscription got expired on "+register.getLicenceUpTo());
				}
			}
			iPGRetObj.setRetMsg(register);
			
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			iPGRetObj.setRetMsg(supervisorRepository.getSupervisorWithPwd(id, pwd));
		
		}
		
		logger.info("Out::"+iPGRetObj); 
		return iPGRetObj;
	}
	

	@PostMapping("/forget") 
	public IPGRetObj fogetPassword(@RequestParam("phoneNumber") Long phoneNumber, @RequestParam("role") String role ) {
		
		logger.info("In::/login/phoneNumber:"+phoneNumber+";role:"+role);
		IPGRetObj iPGRetObj = new IPGRetObj();
		String email = null;
		String name = null;
		String tmpPassword = passwordGenerator.generateRamdomPassword();
		
		if(role.equals(IPGConstants.TENANT_CODE)){
			
			Tenant tenant = tenantRepository.findByPhoneNumber(phoneNumber);
			if(tenant != null){
				email = tenant.getEmail();
				name = tenant.getName();
				tenant.setPassword(tmpPassword);
				tenantRepository.save(tenant); 
			} 
	
		}else if(role.equals(IPGConstants.OWNER_CODE)){
			
			Register register = registerRepository.findByPhoneNumber(phoneNumber);
			if(register != null){
				email = register.getEmail();
				name = register.getName();
				register.setPassword(tmpPassword);
				registerRepository.save(register);
			} 
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			Supervisor supervisor = supervisorRepository.findByPhoneNumber(phoneNumber);
			if(supervisor != null){
				email = supervisor.getEmail();
				name = supervisor.getName();
				supervisor.setPassword(tmpPassword);
				supervisorRepository.save(supervisor);
			} 
		
		}
		
		if(email == null){
			iPGRetObj.setRetMsg("We could not find you on our register ");
		}else{
			
			iPGRetObj.setRetMsg("Mr."+name+", we sent an email to "+email+" with temp password.");
		}
		
		registerService.triggerForgotPasswordEmail(phoneNumber, name, email, tmpPassword);
		
		logger.info("Out::"+iPGRetObj); 
		return iPGRetObj;
		
	}
		

}
