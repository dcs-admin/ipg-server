/**
 * 
 */
package com.rama.ipg.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Mine
 *
 */

@Entity
@Table(name = "supervisor")
public class Supervisor { 
	 
//		@GeneratedValue(strategy = GenerationType.AUTO)
//		@Id 
//		private Long  id ; 
//		
		@Id 
		@Column private Long  phoneNumber ; 
		@Column private String  name ;  
		@Column  private String  email ;
		@Column @JsonIgnore private String  password ;
		
		@Column  private Long  ownerId ;
		
		@Transient
		private List<String> managedHostels = new ArrayList();

		
		
		public List<String> getManagedHostels() {
			return managedHostels;
		}

		public void setManagedHostels(List<String> managedHostels) {
			this.managedHostels = managedHostels;
		}

		public Long getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(Long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Long getOwnerId() {
			return ownerId;
		}

		public void setOwnerId(Long ownerId) {
			this.ownerId = ownerId;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Supervisor [phoneNumber=");
			builder.append(phoneNumber);
			builder.append(", name=");
			builder.append(name);
			builder.append(", email=");
			builder.append(email);
			builder.append(", password=");
			builder.append(password);
			builder.append(", ownerId=");
			builder.append(ownerId);
			builder.append("]");
			return builder.toString();
		}
		
		
		
		

}
