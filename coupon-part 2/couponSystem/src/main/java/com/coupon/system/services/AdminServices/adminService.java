package com.coupon.system.services.AdminServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.Customer;
import com.coupon.system.entity.dao.implementation.CompanyDAOImp;
import com.coupon.system.entity.dao.implementation.CouponDAOImp;
import com.coupon.system.entity.dao.implementation.CustomerDAOImp;


@Service
public class adminService implements adminServiceInterface {
	@Autowired
	private CompanyDAOImp compDao;
	@Autowired
	private CustomerDAOImp custDao;
	@Autowired
	private CouponDAOImp coupDao;
	
	@Transactional
	@Override
	public void createCompany(Company comp) {
		compDao.createCompany(comp);
		
	}

	@Transactional
	@Override
	public void removeCompany(Company comp) {
		compDao.removeCompany(comp);
		
	}

	@Transactional
	@Override
	public void updateCompany(Company comp) {
		compDao.updateCompany(comp);
		
	}
	@Transactional(readOnly=true)
	@Override
	public Company getCompany(int id) {
		
		return compDao.getCompany(id);
	}

	@Transactional(readOnly =true)
	@Override
	public Collection<Company> getAllCompanies() {
		
		return compDao.getAllCompanies();
	}

	@Transactional
	@Override
	public void createCustomer(Customer cust) {
		custDao.createCustomer(cust);
		
	}
	@Transactional
	@Override
	public void removeCustomer(Customer cust) {
		custDao.removeCustomer(cust);
		
	}
	@Transactional
	@Override
	public void updateCustomer(Customer cust) {
		custDao.updateCustomer(cust);
		
	}
	@Transactional(readOnly = true)
	@Override
	public Customer getcustomer(int id) {
		return custDao.getCustomer(id);
		
	}
	@Transactional(readOnly =true)
	@Override
	public Collection<Customer> getAllcustomer() {
		
		return custDao.getAllCustomers();
	}
	@Transactional(readOnly = true)
	@Override
	public Collection<Coupon> getAllcoupon() {
		
		return coupDao.getAllCoupon();
	}

	public CompanyDAOImp getCompDao() {
		return compDao;
	}

	public void setCompDao(CompanyDAOImp compDao) {
		this.compDao = compDao;
	}

	public CustomerDAOImp getCustDao() {
		return custDao;
	}

	public void setCustDao(CustomerDAOImp custDao) {
		this.custDao = custDao;
	}

	public CouponDAOImp getCoupDao() {
		return coupDao;
	}

	public void setCoupDao(CouponDAOImp coupDao) {
		this.coupDao = coupDao;
	}

}
