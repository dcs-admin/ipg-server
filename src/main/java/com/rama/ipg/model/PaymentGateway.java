package com.rama.ipg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paymentGateway")
public class PaymentGateway implements Serializable {
	

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column Long  id ;  

	@Column private  Date  eDate ;      
	@Column private  String  key  ;     
	@Column private  String  secret ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentGateway [id=");
		builder.append(id);
		builder.append(", eDate=");
		builder.append(eDate);
		builder.append(", key=");
		builder.append(key);
		builder.append(", secret=");
		builder.append(secret);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	 
	
	
}
