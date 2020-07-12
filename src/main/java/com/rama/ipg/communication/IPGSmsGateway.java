package com.rama.ipg.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import freemarker.template.TemplateException;

/**
 * 
 * @author Ram
 *
 */

@Service 
public class IPGSmsGateway {  

	private Logger logger = LoggerFactory.getLogger(IPGSmsGateway.class);
	
	
	@Value("${ipg.sms.auth-key:}")
	private String authkey;
	
	@Value("${ipg.sms.sender-id:iPGApp}")
	private String senderId;
	
	@Value("${ipg.sms.route:4}")
	private String route;
	
	@Value("${ipg.sms.host-url:}")
	private String hostUrl;
	
	public boolean sendSMSCommented(String toMobileNumbers, String message ) {
		return true;
	}
	
	public boolean sendSMS(String toMobileNumbers, String message ) throws MailException, MessagingException, IOException, TemplateException {
    
		logger.debug("In::"+toMobileNumbers+":"+message);
		boolean sentStatus = false;
		 
        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL = null;
        BufferedReader reader = null;
        InputStreamReader isr = null;

        //encoding message
        String encoded_message=URLEncoder.encode(message);

        //Send SMS API
        String mainUrl= hostUrl+"?";

        //Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("authkey="+authkey);
        sbPostData.append("&mobiles="+toMobileNumbers);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&route="+route);
        sbPostData.append("&sender="+senderId);

        //final string
        mainUrl = sbPostData.toString();
        try
        {
        	
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            isr = new InputStreamReader(myURLConnection.getInputStream());
            reader= new BufferedReader(isr);
            //reading response
            String response;
            while ((response = reader.readLine()) != null){
            	  //print response
            	logger.debug("Msg sent success : "+response);
            }
          
            sentStatus = true;
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally{
        	 
        	if(isr != null){
        		isr.close();
        	}
        	
        	 //finally close connection
        	if(reader != null){
        		reader.close();
        	}
        }
 
        logger.debug("Out:: Sent Status: "+sentStatus);
		return sentStatus;
		 

	}

}
