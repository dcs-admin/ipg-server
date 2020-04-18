package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.constants.IPGConstants;
import com.rama.ipg.model.IPGRetObj;
import com.rama.ipg.model.TenantWrapper;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.repository.SupervisorRepository;
import com.rama.ipg.repository.TenantRepository;

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
	 
	
	@GetMapping("/login") 
	public IPGRetObj login(@RequestParam("id") String id,@RequestParam("pwd") String pwd, @RequestParam("role") String role ) {
		
		logger.info("In::/login/tid:"+id);
		IPGRetObj iPGRetObj = new IPGRetObj();
		
		if(role.equals(IPGConstants.TENANT_CODE)){
			
			TenantWrapper tenantWrapper =new TenantWrapper();
			tenantWrapper.setTenant( tenantRepository.getTenant(id, pwd));
			if(tenantWrapper.getTenant() != null){
				tenantWrapper.setBed(bedRepository.getBed(tenantWrapper.getTenant().getId()));
			} 
			
			iPGRetObj.setRetMsg(tenantWrapper);
		}else if(role.equals(IPGConstants.OWNER_CODE)){
			
			iPGRetObj.setRetMsg(registerRepository.getOwner(id, pwd));
			
		}else if(role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			iPGRetObj.setRetMsg(supervisorRepository.getSupervisorWithPwd(id, pwd));
		
		}
		
		logger.info("Out::"+iPGRetObj); 
		return iPGRetObj;
	}
	
	 

}
