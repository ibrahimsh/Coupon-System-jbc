package com.coupon.system.entities;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/*
 * JavaBeans are Java classes which  simple coding convention. All you have to do is to

Implement java.io.Serializable interface - To save the state of an object
use a public empty argument constructor - To instantiate the object
And provide public getter and setter methods - To get and set the values of private variables (properties ).
 */
public enum CouponType implements Serializable {
	
	RESTURANTS("Resturants"),
	ELECTRICITY("Electricity"),
	FOOD("Food"),
	HEALTH("Health"),
	SPORT("Sport"),
	CAMPING("Camping"),
	TRAVELLING("Travell");
	
	CouponType(){}
	private String value;
	
	public final static  Map<String,CouponType> findType = new HashMap<String,CouponType>();
	static {
		for (CouponType ct : CouponType.values()) {
			findType.put(ct.getValue(),ct);
		}
	}
	CouponType(String value)
	{
		this.value=value;
		/*for (CouponType ct : CouponType.values()) {
			findType.put(ct.getValue(),this);}
	*/}
	public  void setValue(String val)
	{
		this.value=val;
	}
	public String getValue() {
		return value;
	}
	public static  CouponType findValue(String value)
	{
		return findType.get(value);
	}
	public String toString()
	{
		return this.value;
	}
	}
