package com.coupon.system.entities;
/*
 * name - ibrahim shweiki
 * customer entity jpa that create automatic  table  in mysql in name  customers with linked to coupon table  as many to one 
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;
import java.util.Collection;
@Entity(name="Customer")
@Table(name="Customers")
@EnableTransactionManagement
public class Customer implements  Serializable{
	

	/**
	 * Customer information members @id
	 * @customerName
	 * @customerPassowrd
	 * @CustomerCoupon - list
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="CUST_NAME")
	private String custName;
	@Column(name="PASSWORD")
	private String password;
	//@Column(name="COUPONS")
	
	@OneToMany(mappedBy="customer",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	//@JoinColumn(name = "coupon_id",referencedColumnName="ID")
	private Collection<Coupon> coupons;
	/**
	 * Constructor - empty
	 * 
	 */
	public Customer(){}
	/**
	 * Constructor  -initialize Customer class member
	 * @param custName
	 * @param password
	 * @param coupons
	 */
	public Customer(String custName, String password, Collection<Coupon> coupons) {
		super();
		//this.id = 0;
		this.custName = custName;
		this.password = password;
		//this.coupons = coupons;
	}
	/**
	 * getter id
	 * @return -id (Customer id )
	 */
	public long getId() {
		return id;
	}
	/**
	 * setter for Customer id 
	 * @param id -customer  id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * getter  customer  name 
	 * @return - customer  name 
	 */
	public String getCustName() {
		return this.custName;
	}
	/**
	 * setter Customer  name
	 * @param custName - customer  name 
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * getter customer password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setter Customer  password 
	 * @param password - customer  password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Customer  Coupons  list
	 * @return -list of  Customer  coupons
	 */
	//@OneToMany(targetEntity = Coupon.class, mappedBy = "Customer", 
	//	    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
/*	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * setter - for  Customer  purchased coupons
	 * @param coupons -lis  of  coupons
	 */
/*	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * string format  to show  customer  informations
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}

}
