package DAO_connection;

import java.sql.SQLException;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.CustomerException;

import java_beans.*;
/**
 * 
 * @author MatrixComp
 *@couponDAO
 * interface  for coupon that include  all method  that should use  inside  system to deal  with coupons
 */
public interface CouponDAO {
	/**
	 * Create Coupon table by connecting to coupon database table  throw MYSQL query ,
	 * added coupon to system 
	 * @param coup -the coupon we want to add to system 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void createCoupon(Coupon coup) throws InterruptedException, SQLException, CouponException;
	/**
	 * remove  specific Coupon ,from coupon system and  removing the coupon from company that created and the customer  that purchase this  coupon 
	 * @param coup -the  coupon remove
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void removeCoupon(Coupon coup) throws InterruptedException, SQLException, CouponException;
	/**
	 * update Specific Coupon information
	 * @param coup
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public void updatecoupon(Coupon coup) throws InterruptedException, SQLException, CouponException;
	/**
	 * find specific Coupon information by id 
	 * @param id -coupon id we want to find
	 * @return -coupon information how to stored in database
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public Coupon getCoupon(long id ) throws InterruptedException, SQLException, CouponException;
	/**
	 * find all coupons stored in database Coupon table 
	 * @return - list of  coupons founded
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 * @throws CustomerException 
	 */
	public Collection<Coupon> getAllCoupon() throws InterruptedException, SQLException, CouponException, CustomerException;
	/**
	 * find coupons by specific Type
	 * @param coupt -coupon type 
	 * @return - list of  coupon in type 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	public Collection<Coupon> getCouponByType(CouponType coupt) throws InterruptedException, SQLException, CouponException;
	/**
	 * find coupon id  for the database  while the name  of  coupon is unique  cannot add more than one  coupon in the same name 
	 * @param coup -coupon name 
	 * @return - coupon id 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	long CouponId(Coupon coup) throws InterruptedException, SQLException, CouponException;
}
