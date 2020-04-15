/**
 * 
 */
package com.rama.ipg.schedular;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rama.ipg.service.PaymentService;

/**
 * @author Mine
 *
 */
@Component
public class IPGScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(IPGScheduler.class);
	
	@Autowired
	private PaymentService paymentService;
	
	//@Autowired
	//private TenantService tenantService;
	
	
	@Scheduled(cron = "${ipg.cron.payments-invoice-trigger}")
	public void autoOwnerPaymentsEmail()  {
		
		logger.info("In::AutoTrigger:autoPaymentsInvoiceToOwners");
		
		
		paymentService.autoOwnerPaymentsEmail();
		
		
		logger.info("Out::AutoTrigger");
		
	}
	
	
	@Scheduled(cron = "${ipg.cron.payments-remaindar-trigger}")
	public void autoTenantPaymentRemaindarEmail()  {
		
		logger.info("In::autoPaymentsRemaindarsToTenants");
		
		paymentService.autoTenantPaymentRemainderEmail();
		
		logger.info("Out::autoPaymentsRemaindarsToTenants");
		
		
	}

}
