package Coupon.System.Facade;

import java.sql.SQLException;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.companyException;

import java_beans.Company;
import java_beans.Coupon;
import java_beans.CouponType;
/**
 * 
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * @see
 * <b>Company Facade interface </b>
 * this  interface include  all method that company's can use to control there account 
 *
 */
public interface CompanyFacadDAO
{
	/**
	 * the company can create coupon 
	 * @param coup -the coupon  created 
	 * @throws companyException 
	 */
	public void createCoupon(Coupon coup,Company comp) throws companyException;
	/**
	 * the company can remove specific coupon
	 * @param coup - coupon need to remove
	 * @throws companyException 
	 * @throws CouponException 
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	public void removeCoupon(Coupon coup) throws companyException, InterruptedException, SQLException, CouponException;
	/**
	 * The company can update specific coupon 
	 * @param coup -the coupon need to update it 
	 */
	public void updateCoupon(Coupon coup);
	/**
	 * the company can get all own coupons
	 * @param comp - company information to found  coupons in system
	 * @return -list of companies coupons
	 */
	public Collection<Coupon>getAllcoupons(Company comp);
	/**
	 * find  company coupon by coupon id 
	 * @param id -coupon id 
	 * @return- coupon if  found  by id 
	 */
	public Coupon getCoupon(long id);
	/**
	 * the company can search for coupons by type 
	 * @param ctyp -type  of  coupons 
	 * @return -list of  coupons in specific type  
	 */
	public Collection<Coupon>getCouponByType(CouponType ctyp,Company comp);
	
	
	
	
}
