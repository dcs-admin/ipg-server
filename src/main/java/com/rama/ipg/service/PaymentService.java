/**
 * 
 */
package com.rama.ipg.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.rama.ipg.model.Expense;
import com.rama.ipg.model.Payment;
import com.rama.ipg.model.Register;
import com.rama.ipg.model.Tenant;
import com.rama.ipg.repository.ExpenseRepository;
import com.rama.ipg.repository.PaymentRepository;
import com.rama.ipg.repository.RegisterRepository;
import com.rama.ipg.repository.TenantRepository;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class PaymentService {

	private static Logger logger = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private IPGSmsGateway iPGSmsGateway;
	
	@Autowired
	private IPGMailer iPGMailer;

	private Map<String, Object> reqParamtersMap;

	@Autowired
	private TemplateEngine templateEngine; 
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	

	@Autowired
	private PaymentRepository paymentRepository;
	
	
	@Autowired
	private ExpenseRepository expenseRepository;
	   
	
	public void sendAlerts(Payment payment){ 
		
		Tenant tenant = tenantRepository.getTenantByIdAndOwner(payment.getTenantId(), payment.getOwnerId());
		
		if(tenant != null){
			
			this.triggerAlertEmail(payment, tenant);
		 
			this.triggerSMS(payment, tenant);
			
		}else{
			logger.error("Unable to get Tenant info for "+payment.getTenantId()+";"+ payment.getOwnerId());
		} 
	}
	

	public void autoOwnerPaymentsEmail(){
		
		for(Register register: registerRepository.findAll()){ 
			
			List<Payment> payments = paymentRepository.findLastPayments(register.getMobileNumber());
			
			List<Expense> expenses = expenseRepository.findLastExpenses(register.getMobileNumber());
			 
			
			//logger.info("MN:"+register.getMobileNumber()+";payments: "+payments);
			//if(payments.size() > 0){
				this.triggerPaymentsEmailToOwner(register, payments, expenses);	
			//}else{
			//	logger.info("No Payment report found for owner: "+register.getMobileNumber());
			//}
			
		}
	}
	
	
	public void autoTenantPaymentRemainderEmail(){
		
		for(Tenant tenant: tenantRepository.findAll()){ 
			
			if(tenant.getStatus() != null && tenant.getStatus().equals("A")){
				
				this.triggerPaymentRemainderToTenant(tenant);
			} 		
			
		}
	}
	  
	

	private boolean triggerPaymentsEmailToOwner(Register register, List<Payment> payments,  List<Expense> expenses ) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerPaymentsEmailToOwner");
			YearMonth lastMonth    = YearMonth.now().minusMonths(1); 
			DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
			

			String email, subject, ccMail, bccMail, message = null; 
			email = register.getEmail();
			subject = register.getName()+", Account Statement for "+lastMonth.format(monthYearFormatter);
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTemplate("owner-monthly-payments-report", register, payments, expenses);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);
			
			logger.info("EMIAL SENT:  "+email+";"+subject); 
		 
			emailStatus = true;

			logger.debug("Out::triggerPaymentsEmailToOwner");

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
	
	

	private boolean triggerPaymentRemainderToTenant(Tenant tenant ) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerPaymentRemainderToTenant");

			String email, subject, ccMail, bccMail, message = null;
			email = tenant.getEmail();
			 
			subject = tenant.getName()+", Gentle rent remainder of current month for "+tenant.getHostelName();
			ccMail = null;
			bccMail = null; 
						 
			message = this.getTenantTemplate("tenant-payment-remainder", tenant);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);
			
			logger.info("EMIAL SENT:  "+email+";"+subject); 
		 
			emailStatus = true;

			logger.debug("Out::triggerPaymentRemainderToTenant");

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
	


	private boolean triggerAlertEmail(Payment payment, Tenant tenant ) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerAlertEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = tenant.getEmail();
			 
			subject = payment.gettName()+", your payment confimation with "+payment.getHostelName();
			ccMail = registerRepository.getEmail(tenant.getOwnerId());
			bccMail = null; 
						 
			message = this.getTemplate("tenant-payment", payment);

			 
			iPGMailer.sendEmail(email, subject, ccMail, bccMail, message);
			
			logger.info("EMIAL SENT:  "+email+";"+subject); 
		 
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

	private boolean triggerSMS(Payment payment, Tenant tenant) {

		logger.debug("In::triggerSMS");
		boolean smsStatus = false;

		try {
			String message = "";  
			 
			message = SMSTemplates.TENANT_PAYMENT_TEMPLATE 
					.replaceAll("##USER_NAME##", payment.gettName())
					.replaceAll("##HOSTEL_NAME##", payment.getHostelName())
					.replaceAll("##RENT##", ""+payment.getAmount())
					.replaceAll("##PENDING##",""+ payment.getPendingAmount())
					.replaceAll("##MONTH##", payment.getPaidDate().toString()) ;

			iPGSmsGateway.sendSMS("" + tenant.getPhoneNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerAlertSMS");

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
	private String getTemplate(String templateFileName, Payment payment ) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		
		reqParamtersMap.put("tenantId",payment.getTenantId());
		reqParamtersMap.put("tName",payment.gettName());
		
		reqParamtersMap.put("paymentId",payment.getPid());
		 
		
		reqParamtersMap.put("amount", payment.getAmount()); 
		reqParamtersMap.put("hostelName", payment.getHostelName()); 
		reqParamtersMap.put("paidDate", payment.getPaidDate()); 
		reqParamtersMap.put("rentMethod", payment.getRentMethod()); 
		reqParamtersMap.put("pendingAmount", payment.getPendingAmount()); 
		reqParamtersMap.put("comments", payment.getComments());  


		/*reqParamtersMap.put("hostelId",payment.getBed().getHostelId());
		reqParamtersMap.put("hostelName",payment.getBed().getHostelName());
		reqParamtersMap.put("floor",payment.getBed().getFloor());
		reqParamtersMap.put("roomNumber",payment.getBed().getRoomNumber());
		reqParamtersMap.put("bedNumber",payment.getBed().getBedNumber());
		reqParamtersMap.put("sharingType",payment.getBed().getSharingType());
		reqParamtersMap.put("bookedDate",payment.getBed().getBookedDate()); 
 		*/
 		
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
	private String getTenantTemplate(String templateFileName, Tenant tenant) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		Date date = new Date();
		String month = new SimpleDateFormat("MMMM").format(date); 
		 
		reqParamtersMap.put("hostelName",tenant.getHostelName());
		reqParamtersMap.put("tName",tenant.getName()); 
		reqParamtersMap.put("amount",tenant.getRentAmount());
		reqParamtersMap.put("month",month); 
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
	private String getTemplate(String templateFileName, Register register, List<Payment> payments,  List<Expense> expenses) {
		logger.trace("In::");
		reqParamtersMap = new HashMap<>(); 
		
		//Date date = new Date(); 
		YearMonth lastMonth    = YearMonth.now().minusMonths(1); 
		DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");

		//System.out.printf("Today: %s\n", thisMonth.format(monthYearFormatter));
		//System.out.printf("Last Month: %s\n", lastMonth.format(monthYearFormatter)); 
		
		//String month = new SimpleDateFormat("MMMM").format(date); 
		//String year = new SimpleDateFormat("YYYY").format(date); 
		Long psum = payments.stream().mapToLong(Payment::getAmount).sum();
		Long esum = expenses.stream().mapToLong(Expense::getAmount).sum();
		
		reqParamtersMap.put("name", register.getName());
		reqParamtersMap.put("mobileNumber", register.getMobileNumber());
		reqParamtersMap.put("email",register.getEmail());
		reqParamtersMap.put("month",lastMonth.format(monthYearFormatter));
		reqParamtersMap.put("amount",psum); 
		reqParamtersMap.put("expense_amount",esum); 
		//reqParamtersMap.put("year",year); 
		
		reqParamtersMap.put("payments",payments); 
		reqParamtersMap.put("expenses",expenses); 

		/*reqParamtersMap.put("hostelId",payment.getBed().getHostelId());
		reqParamtersMap.put("hostelName",payment.getBed().getHostelName());
		reqParamtersMap.put("floor",payment.getBed().getFloor());
		reqParamtersMap.put("roomNumber",payment.getBed().getRoomNumber());
		reqParamtersMap.put("bedNumber",payment.getBed().getBedNumber());
		reqParamtersMap.put("sharingType",payment.getBed().getSharingType());
		reqParamtersMap.put("bookedDate",payment.getBed().getBookedDate()); 
 		*/
 		
		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}
	
	
	

}
