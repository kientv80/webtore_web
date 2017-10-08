package com.xyz.hayhay.service.ads;

import com.xyz.hayhay.service.user.Person;

public class LuckyPerson extends Person{
	/**
	 * @return the telco
	 */
	public String getTelco() {
		return telco;
	}
	/**
	 * @param telco the telco to set
	 */
	public void setTelco(String telco) {
		this.telco = telco;
	}
	/**
	 * @return the mobileCode
	 */
	public String getMobileCode() {
		return mobileCode;
	}
	/**
	 * @param mobileCode the mobileCode to set
	 */
	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	String telco;
	String mobileCode;
	int value;
}
