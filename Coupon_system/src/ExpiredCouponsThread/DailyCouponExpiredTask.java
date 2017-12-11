package ExpiredCouponsThread;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.CouponSystemException;
import com.sys.exception.CustomerException;

import DAO_connection.CompanyDBDA;
import DAO_connection.CouponDBDA;
import java_beans.Coupon;

/**
 * 
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * @see
 *<b>DailyCouponExpiredTask implement from runnable interface class
 * to clean the system from old or expired coupons</b>
 */
public class DailyCouponExpiredTask implements Runnable {
/**
 * {@link Method} run() use to enable  the process that clean system from expired coupons 
 */
	
	private CouponDBDA coupondbda ;
	//private Date today = (Date)Calendar.getInstance().getTime();
	//java.sql.Date todayDate = java.sql.Date.valueOf(today);
	
	private boolean is_quite =true;
	/**
	 * Constructor 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public DailyCouponExpiredTask()
	{
		//this.coupondbda = new CouponDBDA();
		
	}
	/**
	 * Constructor - to  initialize  the  is  quite value  if  needed 
	 * @param is_quite
	 */
	/*
	public DailyCouponExpiredTask( boolean is_quite)
	{
		this.is_quite = is_quite;
	}*/
	/**
	 * run the daily thread while its work for 24 hours every day  and  remove  the  expired coupons from system
	 * @throws CouponException 
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	public void coupons() throws InterruptedException, SQLException, CouponException
	{
		for(Coupon c : this.coupondbda.getAllCoupon())
		{
			System.out.println(c);
		}
	}
	@Override
	public void run() 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		java.sql.Date todayDate = java.sql.Date.valueOf(localDate);
		
		Collection<Coupon>getAllCoupons = new ArrayList<Coupon>();
		System.out.println(todayDate);
		System.out.println("start "+ is_quite);
		
			try {
				coupondbda = new CouponDBDA();
			} catch (ClassNotFoundException | SQLException e2) {
				
				e2.printStackTrace();
				CouponSystemException.CouponExceptionHandler(e2);
				
			}
	
		while(is_quite)
		{
			
			try {
				getAllCoupons = this.coupondbda.getAllCoupon();
			} catch (InterruptedException | SQLException | CouponException e1) {
				
				//e1.printStackTrace();
				CouponSystemException.CouponExceptionHandler(e1);
			}
		
			System.out.println(getAllCoupons);
			for (Coupon coup : getAllCoupons)
			{
				System.out.println(coup);
				System.out.println(coup.getEndDate());
				if(todayDate.before(coup.getEndDate()))
				{
					
						try {
							coupondbda.removeCoupon(coup);
						} catch (InterruptedException | SQLException | CouponException e) {
							
							//e.printStackTrace();
							CouponSystemException.CouponExceptionHandler(e);
						}
				
					
				}
				
			}
		}
		
		try {
			Thread.sleep(24*60*60*1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			CouponSystemException.CouponExceptionHandler(e);
		}
		
	}
	/**
	 * shut down the thread that remove  all expired coupons daily
	 */
	public void stop()
	{
		is_quite =false;
	}
	/**
	 * setter and getter for is_quite id  true or  false
	 * {@link -setIs_quite}
	 * @return is_quite value
	 * @param is_quite -true  or  false
	 */
	public boolean isIs_quite() {
		return is_quite;
	}
	/**
	 * setter and getter for is_quite id  true or  false
	
	 * @return is_quite value
	 * @param is_quite -true  or  false
	 */
	public void setIs_quite(boolean is_quite) {
		this.is_quite = is_quite;
	}

}
