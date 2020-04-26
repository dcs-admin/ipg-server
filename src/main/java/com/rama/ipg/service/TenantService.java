/**
 * 
 */
package com.rama.ipg.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.rama.ipg.communication.IPGMailer;
import com.rama.ipg.communication.IPGSmsGateway;
import com.rama.ipg.constants.SMSTemplates;
import com.rama.ipg.model.Tenant;
import com.rama.ipg.model.TenantWrapper;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.util.PasswordGenerator;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class TenantService {

	private static Logger logger = LoggerFactory.getLogger(TenantService.class);

	@Autowired
	private IPGSmsGateway iPGSmsGateway;
	
	@Autowired
	private IPGMailer iPGMailer;

	private Map<String, Object> reqParamtersMap;

	@Autowired
	private TemplateEngine templateEngine;  
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private RegisterRepository registerRepository;
	
	  
	public void sendJoiningAlerts(TenantWrapper tenantWrapper){
		
		 tenantWrapper.getTenant().setPassword(passwordGenerator.generateRamdomPassword());
		
		 this.triggerJoiningEmail(tenantWrapper);
		 
		 this.triggerJoiningSMS(tenantWrapper); 
	}
	
	

	public void sendVacateAlerts(Tenant tenant){ 
		
		 this.triggerVacateEmail(tenant);
		 
		 this.triggerVacateSMS(tenant); 
	}
	

	

	private boolean triggerVacateEmail( Tenant  tenant) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerJoiningEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = tenant.getEmail(); 
			 
			subject = tenant.getName()+", your vacation confirmed with your current PG";
			ccMail = registerRepository.getEmail(tenant.getOwnerId());
			bccMail = null; 
						 
			message = this.getTemplate("tenant-vacation", tenant);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);

			System.out.println("EMIAL SENT ----------------------------------");
			emailStatus = true;

			logger.debug("Out::triggerJoiningEmail");

		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emailStatus;

	}

	private boolean triggerVacateSMS(Tenant  tenant) {

		logger.debug("In::triggerVacateSMS");
		boolean smsStatus = false;

		try {
			String message = ""; 
			 
			message = SMSTemplates.TENANT_VACATION_TEMPLATE 
					.replaceAll("##USER_NAME##", tenant.getName()) ;

			iPGSmsGateway.sendSMS("" +tenant.getPhoneNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerVacateSMS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smsStatus;

	}
	

	private boolean triggerJoiningEmail( TenantWrapper tenantWrapper) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerJoiningEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = tenantWrapper.getTenant().getEmail();
			 
			subject = "Dear "+tenantWrapper.getTenant().getName()+", welcome Onboarding to "+tenantWrapper.getBed().getHostelName();
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTemplate("tenant-onboarding-info", tenantWrapper);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);

			System.out.println("EMIAL SENT ----------------------------------");
			emailStatus = true;

			logger.debug("Out::triggerJoiningEmail");

		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emailStatus;

	}

	private boolean triggerJoiningSMS(TenantWrapper tenantWrapper) {

		logger.debug("In::triggerJoiningSMS");
		boolean smsStatus = false;

		try {
			String message = ""; 
			
			
			 
			message = SMSTemplates.TENANT_ONBOARDING_TEMPLATE 
					.replaceAll("##USER_NAME##", tenantWrapper.getTenant().getName())
					.replaceAll("##HOSTEL_NAME##", tenantWrapper.getBed().getHostelName())
					.replaceAll("##FLOOR##", tenantWrapper.getBed().getFloor())
					.replaceAll("##ROOM##", tenantWrapper.getBed().getRoomNumber())
					.replaceAll("##BED##", tenantWrapper.getBed().getBedNumber())
					.replaceAll("##RENT##", tenantWrapper.getBed().getRent())
					.replaceAll("##ADVANCE##", tenantWrapper.getTenant().getAdvanceAmount())  
					.replaceAll("##PASSWORD##", tenantWrapper.getTenant().getPassword()) ;

			iPGSmsGateway.sendSMS("" + tenantWrapper.getTenant().getPhoneNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerJoiningSMS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smsStatus;

	}
	
	
	/**
	 * 
	 * @param templateFileName Name of the template file without extension
	 * @param text
	 * @return
	 */ 
	private String getTemplate(String templateFileName, Tenant tenant) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		
		reqParamtersMap.put("id",tenant.getId());
		reqParamtersMap.put("name",tenant.getName()); 
		reqParamtersMap.put("phoneNumber",tenant.getPhoneNumber()); 
		reqParamtersMap.put("email",tenant.getEmail());  
 		
		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}
	

	/**
	 * 
	 * @param templateFileName Name of the template file without extension
	 * @param text
	 * @return
	 */ 
	private String getTemplate(String templateFileName, TenantWrapper tenantWrapper) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		
		reqParamtersMap.put("id",tenantWrapper.getTenant().getId());
		reqParamtersMap.put("name",tenantWrapper.getTenant().getName());
		reqParamtersMap.put("desc",tenantWrapper.getTenant().getDesc());
		reqParamtersMap.put("address_line1",tenantWrapper.getTenant().getAddress_line1());
		reqParamtersMap.put("address_line2",tenantWrapper.getTenant().getAddress_line2());
		reqParamtersMap.put("city",tenantWrapper.getTenant().getCity());
		reqParamtersMap.put("state",tenantWrapper.getTenant().getState());
		reqParamtersMap.put("zipcode",tenantWrapper.getTenant().getZipcode());
		reqParamtersMap.put("aadhar",tenantWrapper.getTenant().getAadhar());
		reqParamtersMap.put("phoneNumber",tenantWrapper.getTenant().getPhoneNumber());
		reqParamtersMap.put("emergency",tenantWrapper.getTenant().getEmergency());
		reqParamtersMap.put("email",tenantWrapper.getTenant().getEmail());
		reqParamtersMap.put("gender",tenantWrapper.getTenant().getGender());
		reqParamtersMap.put("date",tenantWrapper.getTenant().getDate());
		reqParamtersMap.put("rentType",tenantWrapper.getTenant().getRentType());
		reqParamtersMap.put("rentMethod",tenantWrapper.getTenant().getRentMethod());
		reqParamtersMap.put("rentAmount",tenantWrapper.getTenant().getRentAmount());
		reqParamtersMap.put("advanceAmount",tenantWrapper.getTenant().getAdvanceAmount());
		reqParamtersMap.put("eSignature",tenantWrapper.getTenant().geteSignature());
		reqParamtersMap.put("password",tenantWrapper.getTenant().getPassword());


		reqParamtersMap.put("hostelId",tenantWrapper.getBed().getHostelId());
		reqParamtersMap.put("hostelName",tenantWrapper.getBed().getHostelName());
		reqParamtersMap.put("floor",tenantWrapper.getBed().getFloor());
		reqParamtersMap.put("roomNumber",tenantWrapper.getBed().getRoomNumber());
		reqParamtersMap.put("bedNumber",tenantWrapper.getBed().getBedNumber());
		reqParamtersMap.put("sharingType",tenantWrapper.getBed().getSharingType());
		reqParamtersMap.put("bookedDate",tenantWrapper.getBed().getBookedDate()); 
 		
 		
		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}

}
