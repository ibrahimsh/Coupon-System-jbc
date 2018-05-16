package com.coupon.system.services.AdminServices;
/*
 * ibrahim shweiki
 * admin service like  facade in the first part but  in spring  boot 
 * in this  class we specified the admin method  that can use to interact with the system
 */
import java.util.Collection;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.Customer;

public interface adminServiceInterface {
	public void createCompany(Company comp);
	public void removeCompany(Company comp);
	public void updateCompany(Company comp);
	public Company getCompany(int id) ;
	public Collection<Company> getAllCompanies();
	public void createCustomer(Customer cust) ;
	public void removeCustomer(Customer cust);
	public void updateCustomer(Customer cust);
	public Customer getcustomer(int id);
	public Collection<Customer> getAllcustomer();
	public Collection<Coupon>getAllcoupon();
	//public CouponClientFacadeDAO login(String UserName, String password, clientType clienttyp)
	
	
	

}
