package com.coupon.system.services.CustomerServices;
/*
 * ibrahim shweiki
 */
import java.util.Collection;

import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.Customer;

public interface customerServiceInterface {
	/*
	 * customer service like customer facade in first part 
	 */
	public void purchaseCoupon(Coupon coup,Customer cust);
	public Collection<Coupon>getAllPurchasedCoupons(Customer cust);
	public Collection<Coupon>getAllpurcheasedCouponsbyType(CouponType ctype);
	public Collection<Coupon>getAllPurchasedCouponsByPrice(double price);
	Collection<Coupon> findCoupons();
	
	

}
