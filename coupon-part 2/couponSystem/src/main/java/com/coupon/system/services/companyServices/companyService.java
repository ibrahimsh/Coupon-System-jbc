package com.coupon.system.services.companyServices;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entity.dao.implementation.CouponDAOImp;

@Service
public class companyService implements companyServiceInterface{
	@Autowired
	private CouponDAOImp coupDao;
	
	@Transactional
	@Override
	public void createCoupon(Coupon coup) {
		coupDao.createCoupon(coup);
		
	}
	@Transactional
	@Override
	public void removeCoupon(Coupon coup) {
		 coupDao.removeCoupon(coup);
		
	}
	@Transactional
	@Override
	public void updateCoupon(Coupon coup) {
		coupDao.updatecoupon(coup);
		
	}
	@Transactional
	@Override
	public Collection<Coupon> getAllcoupons(Company comp) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public Coupon getCoupon(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	@Override
	public Collection<Coupon> getCouponByType(CouponType ctyp) {
		
		return coupDao.getCouponByType(ctyp);
	}
	

}
