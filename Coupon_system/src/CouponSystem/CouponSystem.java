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
	 
	 private CouponDBDA coupondbda ;
	 private CompanyDBDA companydbda;
	 private CustomerDBDA customerdbda;
	 private AdminFacade Admin ;
	 private CustomerFacade customer ;
	 private CompanyFacade company;
	 private DailyCouponExpiredTask deCoupTask;// = new DailyCouponExpiredTask();
	 public static CouponSystem instance ;
	 
	 public static CouponSystem getInstance()
	 {
		 if(instance == null)
		 {
		 instance = new CouponSystem();
		 }
		 return instance;
	 }
	 private CouponSystem()
		{
		 try{
			  coupondbda = new CouponDBDA() ;
			
			  companydbda = new CompanyDBDA();
			  //customerdbda = new CustomerDBDA();
			  //Admin  =new AdminFacade();
			  //customer = new CustomerFacade() ;
			  //company = new CompanyFacade();
			  this.deCoupTask = new DailyCouponExpiredTask();
		 }catch(ClassNotFoundException|SQLException e)
			{
				CouponException.CouponExceptionHandler(e);
				CustomerException.CustomerExceptionHandler(e);
				companyException.CompanyExceptionHandler(e);
			}
		  
		}
	@Override
	public boolean login(String UserName, String password, clientType cliType) throws CouponSystemException, ClassNotFoundException, SQLException {
		boolean islogin = false;
		switch(cliType)
		{
		case ADMIN :
			Admin  =new AdminFacade();
			if(Admin.login(UserName, password, cliType))
			{
				islogin =true;
				//return Admin;
			
			}
			break;
		case CUSTOMER:
			 customer = new CustomerFacade() ;
			 if(customer.login(UserName, password, cliType))
			{
				islogin= true;
				//return customer;
			}
			 break;
		case COMPANY :
			 company = new CompanyFacade();
			 if(company.login(UserName, password, cliType))
			{
				islogin =true;
				//return company;
			}
			 break;
			default:
				islogin=false;
				throw new CouponSystemException("login fail you dont have permession to access the system connect to admin");
			
		}
			return islogin;
	}
	public void shutDawn()
	{
		try {
			conpool.getInstance().getConnection().close();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deCoupTask.stop();
		
	}
	

}
