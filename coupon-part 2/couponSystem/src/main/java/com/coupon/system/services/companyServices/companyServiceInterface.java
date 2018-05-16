package com.coupon.system.services.companyServices;
/*
 * ibrahim shweiki
 * company service  its  like company faced in first part
 */
import java.util.Collection;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;

public interface companyServiceInterface {
	public void createCoupon(Coupon coup) ;
	public void removeCoupon(Coupon coup);
	public void updateCoupon(Coupon coup);
	public Collection<Coupon>getAllcoupons(Company comp);
	public Coupon getCoupon(int id);
	public Collection<Coupon>getCouponByType(CouponType ctyp);
	
	

}
