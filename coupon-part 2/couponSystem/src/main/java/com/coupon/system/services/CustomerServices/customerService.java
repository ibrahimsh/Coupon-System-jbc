package com.coupon.system.services.CustomerServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.Customer;
import com.coupon.system.entity.dao.implementation.CouponDAOImp;
import com.coupon.system.entity.dao.implementation.CustomerDAOImp;
import com.coupon.system.services.CustomerServices.customerServiceInterface;
@Service
public class customerService implements customerServiceInterface {
	@Autowired
	CouponDAOImp coupDao ;
	@Autowired
	CustomerDAOImp custDao;
	@Transactional
	@Override
	public void purchaseCoupon(Coupon coup, Customer cust) {
		coupDao.createCoupon(coup);
		
	}
	@Transactional
	@Override
	public Collection<Coupon> getAllPurchasedCoupons(Customer cust) {
		
		return custDao.getCoupons(cust);
	}

	@Override
	public Collection<Coupon> getAllpurcheasedCouponsbyType(CouponType ctype) {
		Customer cust = new Customer();
		return custDao.getAllCouponsByType(ctype,cust);
	}

	@Override
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) {
		Customer cust = new Customer();
		return custDao.getAllCustomerCouponByPrice(cust, price);
	}

	@Override
	public Collection<Coupon> findCoupons() {
		
		return coupDao.getAllCoupon();
	}

}
