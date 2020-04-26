/**
 * 
 */
package com.rama.ipg.model;

import java.io.Serializable;

/**
 * @author Mine
 *
 */
public class ChangePasswd implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String role;
	private String password;
	private String currPassword;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCurrPassword() {
		return currPassword;
	}
	public void setCurrPassword(String currPassword) {
		this.currPassword = currPassword;
	}
	
	

}
