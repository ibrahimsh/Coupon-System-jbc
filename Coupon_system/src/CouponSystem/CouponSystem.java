package CouponSystem;
import DAO_connection.*;
import ExpiredCouponsThread.DailyCouponExpiredTask;
import java_beans.Company;
import java_beans.Coupon;
import java_beans.Customer;
import system_connection.connectionPool;

import java.sql.SQLException;

import com.sys.exception.CouponException;
import com.sys.exception.CouponSystemException;
import com.sys.exception.CustomerException;
import com.sys.exception.companyException;

import Coupon.System.Facade.*;



public class CouponSystem implements CouponClientFacadeDAO {
	
	 private connectionPool conpool ;
	 
	 
	 private AdminFacade Admin ;
	 private CustomerFacade customer ;
	 private CompanyFacade company;
	 private DailyCouponExpiredTask deCoupTask = new DailyCouponExpiredTask();
	
	 Thread cleanOldCoupons = new Thread(deCoupTask);
	 public static CouponSystem instance ;
	 
	 public static synchronized CouponSystem getInstance() throws ClassNotFoundException, SQLException
	 {
		 if(instance == null)
		 {
			 instance = new CouponSystem();
		 }
		 return instance;
	 }
	 private CouponSystem() throws ClassNotFoundException, SQLException
		{
		 
		 	connectionPool.getInstance();
		 	cleanOldCoupons.setDaemon(true);
		 	cleanOldCoupons.start();
		 	

		  
		}
	@Override
	public CouponClientFacadeDAO login(String UserName, String password, clientType cliType) throws ClassNotFoundException, SQLException, CustomerException 
	{
		
		
		switch(cliType)
		{
			case ADMIN  :
				Admin  =new AdminFacade();
				if(Admin.login(UserName, password,cliType)!=null)
				{
					return Admin;
				}
			
			break;
			case CUSTOMER:
				 customer = new CustomerFacade() ;
				 if(customer.login(UserName, password, cliType) !=null)
				 {
					 return customer;
				 }
			break;
			case COMPANY :
				 company = new CompanyFacade();
				if( company.login(UserName, password, cliType)!=null)
				{
					return company;
				}
				
				break;
				
		}
		return null;
		
			
	}
	public void shutDawn()
	{
		try {
			connectionPool.getInstance().getConnection().close();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			CouponSystemException.CouponExceptionHandler(e);
		}
		
		deCoupTask.stop();
		
	}
	

}
