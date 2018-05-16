package com.coupon.system.entities;
/*
 * name   - ibrahim shweiki
 * company entity , to create automatic  table  in mysql with name companies and generate the columns
 * also join coupon class as entity in company by using coupon class as many to one 
 */
import javax.persistence.Access;
import javax.persistence.AccessType;
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
@Entity(name = "Company")
@Table(name="COMPANIES")
@EnableTransactionManagement
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="NAME")
	private String compName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="EMAIL")
	private String Email;
	//@Access(AccessType.PROPERTY)
	//(targetEntity = Coupon.class, mappedBy = "company", 
		//	cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	
	@OneToMany( mappedBy = "company",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	//@JoinColumn(name = "coupon_id",referencedColumnName="ID")
	private  Collection<Coupon> coupons;
	/**
	 * Constructor 
	 * empty Constructor
	 */
	public Company(){}
	/**
	 * Constructor 
	 * @param compName - to initialize company name 
	 * @param password - initialize company password 
	 * @param Email -  to initialize Company Email  
	 * @param coupons - initialize Company coupon list 
	 */
	public Company(String compName, String password,String Email, Collection<Coupon> coupons) {
		super();
		//this.id=0 ;
		this.compName = compName;
		this.password = password;
		this.Email = Email;
		//this.coupons = coupons;
	}
	/**
	 * getEmail value
	 * @return - company email 
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Company Email setter
	 * @param email - set company email value
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * get company id inside the system 
	 * @return - Company id
	 */
	public long getId() {
		return id;
	}
	/**
	 * set company id  with value
	 * @param id - id  as parameter to set 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * get Company name 
	 * @return - company name 
	 */
	public String getCompName() {
		return compName;
	}
	/**
	 * set Company value if you intend to change  company name 
	 * @param compName - get the name we want to change  as  parameter
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	/**
	 * get  Company password 
	 * @return - return company password 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set new value  for company password
	 * @param password - as parameter new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * show company coupon list 
	 * @return - list of  coupons that company owne
	 */
	
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * set company coupon list  
	 * @param coupons -  passing the coupon list that company add to account 
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * toString method  that print company information in formula we want to show  to user 
	 * @return - string formula include company information 
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password +",Email="+ Email + ", coupons=" + coupons + "]";
	}
	
	

}
