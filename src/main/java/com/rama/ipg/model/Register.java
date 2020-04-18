package com.rama.ipg.model;

 

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "register")
public  class Register implements Serializable {

	private static final long serialVersionUID = 1L;

	 
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//private Long  id ; 
	@Id 
	@Column private String  mobileNumber ; 
	@Column private String  name ;
	@Column private String  subscriptionType ;
	@Column private Date  licenceUpTo ;
	@Column private String  licenseString ;
	@Column  private String  appVersion  ;
	@Column private Date  installationDate ;
	@Column private String  isLicenceValid ;
	@Column private String  months ;
	@Column  private Date  installationUpdateDate ;
	@Column  private String  email ;
	@Column  private String  amount ;
	@Column  private String  otp ; 
	@Column  private String  password ;
	
	public String getMobileNumber() {
		return mobileNumber.trim() ;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public Date getLicenceUpTo() {
		return licenceUpTo;
	}
	public void setLicenceUpTo(Date licenceUpTo) {
		this.licenceUpTo = licenceUpTo;
	}
	public String getLicenseString() {
		return licenseString;
	}
	public void setLicenseString(String licenseString) {
		this.licenseString = licenseString;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public Date getInstallationDate() {
		return installationDate;
	}
	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}
	public String getIsLicenceValid() {
		return isLicenceValid;
	}
	public void setIsLicenceValid(String isLicenceValid) {
		this.isLicenceValid = isLicenceValid;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public Date getInstallationUpdateDate() {
		return installationUpdateDate;
	}
	public void setInstallationUpdateDate(Date installationUpdateDate) {
		this.installationUpdateDate = installationUpdateDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Register [mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", name=");
		builder.append(name);
		builder.append(", subscriptionType=");
		builder.append(subscriptionType);
		builder.append(", licenseString=");
		builder.append(licenseString);
		builder.append(", appVersion=");
		builder.append(appVersion);
		builder.append(", isLicenceValid=");
		builder.append(isLicenceValid);
		builder.append(", months=");
		builder.append(months);
		builder.append(", email=");
		builder.append(email);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", otp=");
		builder.append(otp);
		builder.append("]");
		return builder.toString();
	}
	
	
	 
}
