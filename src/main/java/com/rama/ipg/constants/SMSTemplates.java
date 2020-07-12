/**
 * 
 */
package com.rama.ipg.constants;

/**
 * @author user
 *
 */
public class SMSTemplates {
	
	public static final String OWNER_REGISTRATION_TEMPLATE = "Dear ##USER_NAME##,\n"+
																"Welcome to iPG services, you can now manage your PGs, tenants, payments, expenses easily with this app. \nYour tenants also can download the app and use. Awesome features will be coming for pro version soon. \n"+
																"Looking forward to serve you better.\n"+
																"\n"+
																"Thanks\n"+
																"--\n@Team iPG (http://9m.io/5I8Q/##num##)";
	
	public static final String OTP_TEMPLATE = "Dear customer,\n"+
			"Here is the OTP for your registration :  ##OTP## \nValid for 10 mins."+
			"\nThanks\n"+
			"--\n@Team iPG(http://9m.io/5I8Q/##num##)";
	
	public static final String TENANT_ONBOARDING_TEMPLATE = "Hello ##USER_NAME##, \n"+
			"Welcome to ##HOSTEL_NAME##.\n"+
			"Here is your booking details\n"+
			"Floor Num : ##FLOOR##\n"+
			"Room Num : ##ROOM##\n"+
			"Bed Num : ##BED##\n"+
			"Monthly Rent : ##RENT##\n"+
			"Advance Paid : ##ADVANCE##\n"+
			"Download app from playstore http://9m.io/5I8Q/##num## and login with temp password : ##PASSWORD##\n"+
			"Thanks\n"+
			"--\n@Team iPG";
	
	public static final String SUPERVISOR_ONBOARDING_TEMPLATE = "Hello ##USER_NAME##, \n"+
			"You are requested as supervisory operations with the request from  ##OWNER##.\n"+  
			"Download app from playstore http://9m.io/5I8Q/##num## and login with temp password : ##PASSWORD##\n"+
			"Thanks\n"+
			"--\n@Team iPG";
	
	public static final String TENANT_PAYMENT_TEMPLATE = "Dear ##USER_NAME##, \n"+
			"Payment received on : ##MONTH## to ##HOSTEL_NAME##.\n"+ 
			"Amount Received : ##RENT##\n"+
			"Pending amount : ##PENDING##.\n"+
			"Download app from playstore http://9m.io/5I8Q/##num## and enjoy our services for free.\n"+
			"Thanks\n"+
			"--\n@Team iPG"; 
	
	public static final String TENANT_VACATION_TEMPLATE = "Dear ##USER_NAME##, \n"+ 
			"As requested ypu are successfully vacated from your current PG.\n"+ 
			"Hope you enjoy seemless services and re-visit us again.\n"+
			"Download app from playstore http://9m.io/5I8Q/##num## and enjoy our services for free.\n"+
			"Thanks\n"+
			"--\n@Team iPG";
	
 

}
