package com.rama.ipg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rama.ipg.constants.IPGConstants;
import com.rama.ipg.model.Bed;
import com.rama.ipg.model.Tenant;
import com.rama.ipg.model.TenantWrapper;
import com.rama.ipg.repository.BedRepository;
import com.rama.ipg.repository.TenantRepository;
import com.rama.ipg.service.StorageService;
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
	

	@Autowired
	private StorageService storageService;
	
	@PostMapping("/tenants") 
	public TenantWrapper saveTenant(@RequestBody TenantWrapper  tenantWrapper) {
		
		logger.info("In::");
		
		tenantService.sendJoiningAlerts(tenantWrapper);
		
		Tenant tenant = tenantRepository.save(tenantWrapper.getTenant());
		tenantWrapper.setTenant(tenant );
		tenantWrapper.getBed().setTenantId(tenant.getId());
		tenantWrapper.getBed().settName(tenant.getName());
		tenantWrapper.getBed().setStatus("Y");
		
		tenantWrapper.setBed( bedRepository.save(tenantWrapper.getBed()));
		
		logger.info("Out::"+tenantWrapper);
		
		return tenantWrapper;
	}
	
	
	@GetMapping("/tenants") 
	public TenantWrapper getTenant(@RequestParam("tid") Long tid,@RequestParam("pwd") String pwd ) {
		
		logger.info("In::/tenants/tid:"+tid);
		
		TenantWrapper tenantWrapper =new TenantWrapper();
		tenantWrapper.setTenant( tenantRepository.getTenant(tid, pwd));
		if(tenantWrapper.getTenant() != null){
			tenantWrapper.setBed(bedRepository.findBedByTenant(tenantWrapper.getTenant().getId()));
		} 
		
		logger.info("Out::"+tenantWrapper);
		
		return tenantWrapper;
	}
	
	
	@GetMapping("/tenants/{tid}/bed-info") 
	public Bed getBedInfoForTenant(@PathVariable("tid") Long tid ) {
		
		logger.info("/tenants/{tid}/bed-info"+tid);
		Bed bed = bedRepository.findBedByTenant(tid);
		
		logger.info("Out::"+bed);
		
		return bed;
	}
	
	
	@PutMapping("/tenants/{id}")
	public Tenant updateTenant(@PathVariable Long id, @Valid @RequestBody Tenant tenant) {
		logger.info("In::"+id); 
		
		if(tenantRepository.findById(id).isPresent()){
			tenant.setId(id);
			tenant = tenantRepository.save(tenant);
		} 
		
		logger.info("Out::"+tenant);
		return tenant;
	}
	
	@DeleteMapping("/tenants/{id}")
	public ResponseEntity<?> deleteTenant(@PathVariable Long id) {
		logger.info("IN::deleteTenant::" + id);

		tenantRepository.findById(id).ifPresent(tenant ->{
			
			this.vacateTenant(tenant);
			
			tenantRepository.deleteById(id); 
			
		});
		
		
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/tenants/{role}") 
	public List<Tenant> getTenants(
			@PathVariable("role") String role,
			@RequestParam("ownerId") Long ownerId ,
			@RequestParam("supervisorId") String supervisorId
			) {
		
		logger.info("In::/tenants/{role}"+role+";o"+ownerId+";s"+supervisorId); 
		
		List<Tenant> tenants = new ArrayList<Tenant>();
		if(role.equals(IPGConstants.OWNER_CODE) ){
			
			tenants = tenantRepository.getTenantsByOwnerId(ownerId);
			
		}else if( role.equals(IPGConstants.SUPERVISOR_CODE)){
			
			tenants = tenantRepository.getTenantsBySupervisorId(Long.parseLong((supervisorId)));
			
		}  
		
		logger.info("Out::"+tenants);
		return tenants;
	}
	
	
	
	
	
	@PostMapping("/tenants/vacate") 
	public TenantWrapper vacateTenant(@RequestBody Tenant tenant) {
		
		logger.info("In::"+tenant);
		
		TenantWrapper tenantWrapper = new TenantWrapper();
		
		//tenant = tenantRepository.getTenantByOwner(tenant.getPhoneNumber(), tenant.getOwnerId());
		if(tenant != null ){
			
			tenantService.sendVacateAlerts(tenant);
			
			tenant.setStatus("P");
			tenant.setHostelId("-1");
			tenant.setHostelName("");
			tenant = this.updateTenant(tenant.getId(), tenant);
			tenantWrapper.setTenant(tenant); 
			
			Bed bed = bedRepository.findBedByTenant(tenant.getId());
			if(bed != null){
				bed.setTenantId(-1L);
				bed.settName("");
				bed.setStatus("N");
				bed = bedRepository.save(bed);
				
				tenantWrapper.setBed(bed);
			} 
		} 
		
		logger.info("Out::"+tenantWrapper); 
		return tenantWrapper;
	}
	
	@PostMapping("/tenants/{id}/upload/{cat}") 
	public void storeTenantImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws Exception {

		logger.info("IN::POST::/tenants/{id}/upload/{cat}::uploadTenantImages::" + id + "::" + cat);
		storageService.storeTenantImage(file, cat, id);
		logger.info("OUT::POST:://tenants/uploadImage/{cat}/{id}::uploadTenantImages::" + id + "::" + cat);

	} 
	
	@GetMapping("/tenants/{id}/retrive/{cat}") 
	public ResponseEntity<InputStreamResource> retriveTenantImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws Exception, IOException {
		logger.info("IN::POST::/tenants/{id}/retrive/{cat}::retriveHostelImage::" + id + "::" + cat);
		logger.info("OUT::POST::/tenants/{id}/retrive/{cat}::retriveHostelImage::" + id + "::" + cat);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(storageService.retrivetenantImage(id, cat));

	}

	 

}
