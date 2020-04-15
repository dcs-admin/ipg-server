/**
 * 
 */
package com.rama.ipg.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

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
import com.rama.ipg.model.Register; 

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class RegisterService {

	private static Logger logger = LoggerFactory.getLogger(RegisterService.class);

	@Autowired
	private IPGSmsGateway iPGSmsGateway;
	
	@Autowired
	private IPGMailer iPGMailer;

	private Map<String, Object> reqParamtersMap;

	@Autowired
	private TemplateEngine templateEngine; 
	
	private Random rand = new Random(); 
	
	
	
	public void doRegister(Register register){
		
		 this.triggerAlertEmail(register);
		 
		 this.triggerSMS(register);
		
		
	}
	
	
	public Register sendOTP(Register register){
		 
		 
		logger.debug("In::sendOTP"+register);
		String otp = ""+getNum()+""+getNum()+""+getNum()+""+getNum();
		register.setOtp(otp);

		try {
			String message = "";  
			message = SMSTemplates.OTP_TEMPLATE .replaceAll("##OTP##", otp); 
			iPGSmsGateway.sendSMS(register.getMobileNumber()+"", message);  
			logger.debug("Out::triggerAlertSMS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			String email, subject, ccMail, bccMail, message = null;
			email = register.getEmail();
			 
			subject = "Your OTP for Ownership Subscription";
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTemplate("otp-template", register);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);

			System.out.println("EMIAL SENT ----------------------------------");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		logger.debug("In::sendOTP"+otp);
		return register;
		
		
	}
	
	
	int getNum(){
		
		int num = rand.nextInt(9);
		if(num <= 0){
			num *= -1;
		}
		return num;
	}
	
	
	
	

	/**
	 * 
	 * @param templateFileName Name of the template file without extension
	 * @param text
	 * @return
	 */ 
	public String getTemplate(String templateFileName , Register register) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>();

//		if(user.getRole().endsWith(NSConstants.ROLE_TENANT)&& user.getStatus().equals("A")) {
//		reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
//		reqParamtersMap.put("name", user.getName());
// 
//		reqParamtersMap.put("hostel_name", (hostelRepo.getOne(user.getTenantBooking().getHostelId())).getHostelName());
//		reqParamtersMap.put("floor_number", user.getTenantBooking().getFloorId());
//		reqParamtersMap.put("room_number", user.getTenantBooking().getRoomId());
//		reqParamtersMap.put("room_rent", user.getTenantBooking().getRoomRent());	
//		reqParamtersMap.put("bed", user.getTenantBooking().getRoomBedId());
//		}
//		
//		else if(user.getRole().endsWith(NSConstants.ROLE_TENANT) && user.getStatus().equals("NA")) {
//			
//			reqParamtersMap.put("name", user.getName());
//			
//		}
//		else if(user.getRole().endsWith(NSConstants.ROLE_GUEST)) {
//			reqParamtersMap.put("name", user.getName());
//
//			reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
//
//		}
// 
//		
//		else if(user.getRole().endsWith(NSConstants.ROLE_ADMIN)) {
//			reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
//			reqParamtersMap.put("name", user.getName());
//			reqParamtersMap.put("subscription", user.getSubscriptions());
//		}
		 
		
		reqParamtersMap.put("id", register.getMobileNumber());
		reqParamtersMap.put("date", register.getInstallationDate());
 		reqParamtersMap.put("mobileNumber",register.getMobileNumber());
 		reqParamtersMap.put("validity",register.getMonths());
 		reqParamtersMap.put("installationDate", register.getInstallationDate());
 		reqParamtersMap.put("email", register.getEmail()); 
 		reqParamtersMap.put("AppVersion", register.getAppVersion()); 
 		reqParamtersMap.put("LicenceUpTo", register.getLicenceUpTo()); 
 		reqParamtersMap.put("name", register.getName()); 
 		reqParamtersMap.put("otp", register.getOtp()); 
 		reqParamtersMap.put("today_date", ""+new Date());
 		
 		
		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}

	public boolean triggerAlertEmail( Register register) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerAlertEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = register.getEmail();
			 
			subject = "Your PG Ownership Subscription Confirmed";
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTemplate("registration-info", register);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);

			System.out.println("EMIAL SENT ----------------------------------");
			emailStatus = true;

			logger.debug("Out::triggerAlertEmail");

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

	public boolean triggerSMS( Register register) {

		logger.debug("In::triggerSMS");
		boolean smsStatus = false;

		try {
			String message = ""; 
			 
			message = SMSTemplates.OWNER_REGISTRATION_TEMPLATE 
					.replaceAll("##USER_NAME##", register.getName());

			iPGSmsGateway.sendSMS("" + register.getMobileNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerAlertSMS");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smsStatus;

	}

}
