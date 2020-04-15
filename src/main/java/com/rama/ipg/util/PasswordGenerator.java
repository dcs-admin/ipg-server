package com.rama.ipg.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator { 
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
	public String generateRamdomPassword() {
		int count = 8;
		StringBuilder builder = new StringBuilder();
		while (count-- > 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	} 
	 
}