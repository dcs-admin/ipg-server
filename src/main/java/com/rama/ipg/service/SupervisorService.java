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
import com.rama.ipg.model.Supervisor;
import com.rama.ipg.util.PasswordGenerator;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class SupervisorService {

	private static Logger logger = LoggerFactory.getLogger(SupervisorService.class);

	@Autowired
	private IPGSmsGateway iPGSmsGateway;
	
	@Autowired
	private IPGMailer iPGMailer;

	private Map<String, Object> reqParamtersMap;

	@Autowired
	private TemplateEngine templateEngine;  
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	  
	public void sendJoiningAlerts(Supervisor supervisor){
		
		 supervisor.setPassword(passwordGenerator.generateRamdomPassword());
		
		 this.triggerJoiningEmail(supervisor);
		 
		 this.triggerJoiningSMS(supervisor); 
	} 
	  

	private boolean triggerJoiningEmail(Supervisor supervisor) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerJoiningEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = supervisor.getEmail();
			 
			subject = "Dear "+supervisor.getName()+", welcome onboarding as a supervisor for assigned PGs";
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTemplate("supervisor-onboarding-info", supervisor);

			 
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

	private boolean triggerJoiningSMS(Supervisor supervisor) {

		logger.debug("In::triggerJoiningSMS");
		boolean smsStatus = false;

		try {
			String message = "";  
			 
			message = SMSTemplates.SUPERVISOR_ONBOARDING_TEMPLATE 
					.replaceAll("##USER_NAME##", supervisor.getName())
					.replaceAll("##OWNER##", supervisor.getOwnerId())
					.replaceAll("##PASSWORD##", supervisor.getPassword()) ;

			iPGSmsGateway.sendSMS("" + supervisor.getPhoneNumber(), message);

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
	private String getTemplate(String templateFileName, Supervisor supervisor) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		 
		reqParamtersMap.put("name",supervisor.getName()); 
		reqParamtersMap.put("phoneNumber",supervisor.getPhoneNumber()); 
		reqParamtersMap.put("password",supervisor.getPassword());
		reqParamtersMap.put("owner",supervisor.getOwnerId()); 
 		
 		
		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}

}
