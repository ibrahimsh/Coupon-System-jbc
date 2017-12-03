package Coupon.System.Facade;

import java.util.Collection;

import com.sys.exception.CouponException;

import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;
/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * @see
 * <b> Customer Facade interface </b> 
 * this  interface include  all Customer  method  that can use to control his  account
 *
 */
public interface CustomerFacadeDAO
{
	/**
	 * the customer can purchase coupon
	 * @param coup - the coupon that customer  want to purchase
	 * @param cust - the customer 
	 * @throws CouponException 
	 * @throws ClassNotFoundException 
	 */
	public void purchaseCoupon(Coupon coup,Customer cust) throws ClassNotFoundException, CouponException;
	/**
	 * the customer  can find all coupons that he purchase throw the system
	 * @param cust - specific customer 
	 * @return - list of  all customer coupons
	 */
	public Collection<Coupon>getAllPurchasedCoupons(Customer cust);
	/**
	 * the Customer can found  all coupons that purchased throu the system by coupon type
	 * @param ctype - coupon type 
	 * @return - list of  coupons founded
	 */
	public Collection<Coupon>getAllpurcheasedCouponsbyType(CouponType ctype);
	/**
	 * the customer can found his purchased coupon by specific Price
	 * @param price - the price
	 * @return - list of coupon with the price 
	 */
	public Collection<Coupon>getAllPurchasedCouponsByPrice(double price);
	

}
