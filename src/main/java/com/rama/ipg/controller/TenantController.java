package com.rama.ipg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.ipg.model.Tenant;
import com.rama.ipg.model.TenantWrapper;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.TenantRepository;
import com.rama.ipg.service.TenantService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
@RestController
public class TenantController {
	
	private Logger logger = LoggerFactory.getLogger(TenantController.class);
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private BedRepository bedRepository;
	
	@Autowired
	private TenantService tenantService;
	
	@PostMapping("/tenants") 
	public TenantWrapper saveTenant(@RequestBody TenantWrapper  tenantWrapper) {
		
		logger.info("In::");
		
		tenantService.sendJoiningAlerts(tenantWrapper);
		
		tenantWrapper.setTenant( tenantRepository.save(tenantWrapper.getTenant() ));
		
		tenantWrapper.setBed( bedRepository.save(tenantWrapper.getBed()));
		
		logger.info("Out::"+tenantWrapper);
		
		return tenantWrapper;
	}
	
	
	@GetMapping("/tenants") 
	public TenantWrapper getTenant(@RequestParam("tid") String tid,@RequestParam("pwd") String pwd ) {
		
		logger.info("In::/tenants/tid:"+tid);
		
		TenantWrapper tenantWrapper =new TenantWrapper();
		tenantWrapper.setTenant( tenantRepository.getTenant(tid, pwd));
		if(tenantWrapper.getTenant() != null){
			tenantWrapper.setBed(bedRepository.getBed(tenantWrapper.getTenant().getId()));
		} 
		
		logger.info("Out::"+tenantWrapper);
		
		return tenantWrapper;
	}
	
	
	@PostMapping("/tenants/vacate") 
	public Tenant vacateTenant(@RequestBody Tenant tenant) {
		
		logger.info("In::"+tenant.getPhoneNumber()+";"+tenant.getOwnerId());
		
		//tenant = tenantRepository.getTenantByOwner(tenant.getPhoneNumber(), tenant.getOwnerId());
		if(tenant != null ){
			tenantService.sendVacateAlerts(tenant);
			tenant.setStatus("P");
			tenantRepository.save(tenant);
			//TODO Need to vacate the bed from server
		} 
		
		logger.info("Out::"+tenant); 
		return tenant;
	}
	
	
//	@PostMapping("/tenants/bedbook") 
//	public Bed bookBedForTenant(@RequestBody Bed  bed) {
//		
//		logger.info("In::");
//		bed = bedRepository.save(bed);
//		logger.info("Out::"+bed);
//		
//		return bed;
//	}
	

}
