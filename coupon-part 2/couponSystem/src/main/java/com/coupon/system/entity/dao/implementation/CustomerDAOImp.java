package com.coupon.system.entity.dao.implementation;
/*
 * ibrahim shweiki
 */
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.Customer;
import com.coupon.system.entities.daoInt.CustomerDAOIn;
@Repository
@Transactional
public class CustomerDAOImp implements CustomerDAOIn {
	private static final Logger log = LoggerFactory.getLogger(CustomerDAOImp.class);
	@PersistenceContext
	EntityManager emngr;//manager entity using  jpa to(create,update,delete ,get ) from mysql table some time using  sql query
	@Override
	public void createCustomer(Customer cust) {
		
		emngr.persist(cust);
	}

	@Override
	public void removeCustomer(Customer cust) {
		Query query = emngr.createNativeQuery("DELETE FROM customers where cust_name=?").setParameter(1, cust.getCustName().trim());
		query.executeUpdate();
		//emngr.remove(cust);
		
	}

	@Override
	public Customer getCustomer(int id) {
		
		return emngr.find(Customer.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Customer> getAllCustomers() {
		String qu = "FROM Customer as cust";
		return (Collection<Customer>) emngr.createQuery(qu).getResultList();
		
	}

	@Override
	public boolean login(String cust, String password) {
		boolean exist =true;
		//String q = "select from Customer as cust Where cust.CUST_NAME = "+cust+"AND"+"CUST.PASSWORD="+password ;
		List<?> list = emngr.createQuery("SELECT cus FROM Customer cus WHERE cus.CUST_NAME=? and cust.PASSWORD=?")
				.setParameter(1,cust).setParameter(2, password).getResultList();
		if(list.isEmpty()) {
			exist = false;
		}
		return exist;
	}

	@Override
	public Collection<Coupon> getCoupons(Customer cust) {
		
		@SuppressWarnings("unchecked")
		Collection<Coupon> coup =  (Collection<Coupon>) emngr.createQuery("SELECT FROM Coupons Where Customer cust cust.id=?").setParameter(1, cust.getId());
		
		return coup;
	}

	@Override
	public void updateCustomer(Customer cust) {
		//emngr.merge(cust);
		Query q = emngr.createNativeQuery("UPDATE Customers SET cust_name=?,password=? WHERE ID=?")
				.setParameter(1,cust.getCustName()).setParameter(2,cust.getPassword()).setParameter(3,cust.getId());
		//emngr.flush();
		//emngr.merge(com);
		int updating = q.executeUpdate();
		if(updating ==1)
		{
			log.info("the customer updated");
		}
		
	}

	@Override
	public Collection<Coupon> getAllCustomerCouponByPrice(Customer cust, double price) {
		@SuppressWarnings("unchecked")
		Collection<Coupon> coup =  (Collection<Coupon>) emngr.createQuery("SELECT FROM Coupons as coup Where Customer as cust cust.id=? AND coup.Price=?").setParameter(1, cust.getId()).setParameter(2,price);
		return coup;
	}

	@Override
	public long getCustomerID(Customer cust) {
		
		return 0;
	}

	@Override
	public void addCouponCustomer(Customer cust, Coupon coup) {
	cust   = emngr.find(Customer.class,1);
	coup.setCustomer(cust);
	
		
		
	}

	@Override
	public Collection<Coupon> getAllCouponsByType(CouponType ctyp, Customer cust) {
		@SuppressWarnings("unchecked")
		Collection<Coupon> coup =  (Collection<Coupon>) emngr.createQuery("SELECT FROM Coupons as coup Where Customer as cust cust.id=? AND coup.CTYPE=?").setParameter(1, cust.getId()).setParameter(2,ctyp);
		return coup;
	}

	@Override
	public Collection<Coupon> getAllCoupons() {
		@SuppressWarnings("unchecked")
		Collection<Coupon> coup =  (Collection<Coupon>) emngr.createQuery("SELECT FROM Coupons as coup ");
		return coup;
	}

	@Override
	public Collection<Coupon> getCouponById(long id) {
		@SuppressWarnings("unchecked")
		Collection<Coupon> coup = (Collection<Coupon>) emngr.createQuery("select from Coupons as coup where coup.id =?").setParameter(1,id);
		return coup;
	}

}
