package com.coupon.system.services.CustomerServices;

import java.io.Serializable;

public enum clientType implements  Serializable {

	ADMIN("Admin"),
	COMPANY("Company"),
	CUSTOMER("Customer");
	
	String value;
	clientType(){}
	clientType(String type)
	{
		this.value = type;
		
	}
}
