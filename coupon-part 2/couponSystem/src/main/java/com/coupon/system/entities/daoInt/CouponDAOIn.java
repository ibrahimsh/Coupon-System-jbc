package com.coupon.system.entities.daoInt;

import java.util.Collection;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;

public interface CouponDAOIn {
	/**
	 * Create Coupon table by connecting to coupon database table  throw MYSQL query ,
	 * added coupon to system 
	 * @param coup -the coupon we want to add to system 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void createCoupon(Coupon coup) ;
	/**
	 * remove  specific Coupon ,from coupon system and  removing the coupon from company that created and the customer  that purchase this  coupon 
	 * @param coup -the  coupon remove
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void removeCoupon(Coupon coup) ;
	/**
	 * update Specific Coupon information
	 * @param coup
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void updatecoupon(Coupon coup) ;
	/**
	 * find specific Coupon information by id 
	 * @param id -coupon id we want to find
	 * @return -coupon information how to stored in database
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public Coupon getCoupon(int id ) ;
	/**
	 * find all coupons stored in database Coupon table 
	 * @return - list of  coupons founded
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 * @throws CustomerException 
	 */
	public Collection<Coupon> getAllCoupon() ;
	/**
	 * find coupons by specific Type
	 * @param coupt -coupon type 
	 * @return - list of  coupon in type 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public Collection<Coupon> getCouponByType(CouponType coupt) ;
	/**
	 * find coupon id  for the database  while the name  of  coupon is unique  cannot add more than one  coupon in the same name 
	 * @param coup -coupon name 
	 * @return - coupon id 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	long CouponId(Coupon coup) ;

}
