package com.coupon.system.controllers;
/**
 * @author MatrixComp
 * @name ibrahim shweiki
 * admin controller - method  used post ,delete ,put,get , this  class to interact with database facade and  show  the result
 *   on html file  and  using  postman to show  result  as json format
 * 
 */
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.Customer;
import com.coupon.system.services.AdminServices.adminService;
@RestController
@RequestMapping("admin")
public class adminController {
	
	@Autowired
	private adminService adminUser ;
	
	@PostMapping("company")
	public void addCompany(@RequestBody Company comp)
	{
		System.out.println("company :"+" "+comp);
		adminUser.createCompany(comp);
	}
	@DeleteMapping("RemoveCompany")
	public void removeCompany(@RequestBody Company comp)
	{
		adminUser.removeCompany(comp);
	}
	@DeleteMapping("RemoveCustomer")
	public void removeCustomer(@RequestBody Customer cust)
	{
		adminUser.removeCustomer(cust);
		
	}
	@PutMapping("updateCompany")
	public void updateCompany(@RequestBody Company comp)
	{
		
		adminUser.updateCompany(comp);
		
	}
	@PostMapping("addCustomer")
	public  void addCustomer(@RequestBody Customer cust)
	{
		adminUser.createCustomer(cust);
	}
	@GetMapping("getAllCompanies")
	public Collection<Company> getAllCompanies()
	{
		return  adminUser.getAllCompanies();
	}
	@GetMapping("getAllCustomers")
	public Collection<Customer> getAllcustomer()
	{
		return adminUser.getAllcustomer();
	}
	@PutMapping("updateCustomer")
	public void updateCustomer(@RequestBody Customer cust)
	{
		adminUser.updateCustomer(cust);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("customerById/{id}")
	public Customer getCustomer(@PathVariable("id") int id) {

		return adminUser.getcustomer(id);
		
	}
	@GetMapping("companyById/{id}")
	public Company getCompany(@PathVariable("id") int id)
	{
		return adminUser.getCompany(id);
	}
	@GetMapping("getAllCoupons")
	public Collection<Coupon>getAllcoupon()
	{
		return adminUser.getAllcoupon();
	}

}
