package Coupon.System.Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.CustomerException;
import com.sys.exception.companyException;

import DAO_connection.CouponDBDA;
import DAO_connection.CustomerDBDA;
import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;

public class CustomerFacade implements CustomerFacadeDAO ,CouponClientFacadeDAO{

	private CouponDBDA _coupondbda ; 
	private Coupon _coupon;
	private CustomerDBDA _customerdbda;
	private Collection<Coupon>getAllcoupons ;
	private Customer _customer ;
	
	/**
	 * constructor customer facade
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public CustomerFacade() throws ClassNotFoundException, SQLException
	{
		
			
		
			this._customerdbda = new CustomerDBDA();
			
			this._coupondbda = new CouponDBDA();
			this._coupon = new Coupon();
			this._customer = new Customer();
			this.getAllcoupons = new ArrayList<Coupon>();
	
		
	}
	@Override
	public Collection<Coupon>findCoupons() throws InterruptedException, SQLException, CouponException
	{
		this.getAllcoupons =this._coupondbda.getAllCoupon();
		return this.getAllcoupons;
	}
	/**
	 * customer purchase new  coupon
	 */
	@Override
	public void purchaseCoupon(Coupon coup,Customer cust) throws ClassNotFoundException, CouponException
	{
		try{
			this._customerdbda.addCouponCustomer(cust, coup);
		}catch(Exception e)
		{
			e.printStackTrace();
			CustomerException.CustomerExceptionHandler(e);
		}
		
	}

	@Override
	public Collection<Coupon> getAllPurchasedCoupons(Customer cust)
	{	try
		{
			getAllcoupons =_customerdbda.getCoupons(cust);
		}catch(Exception e)
		{
			
			CustomerException.CustomerExceptionHandler(e);
		}
		return getAllcoupons;
	}

	@Override
	public Collection<Coupon> getAllpurcheasedCouponsbyType(CouponType ctype)
	{
		try
		{
			this.getAllcoupons =this._customerdbda.getAllCouponsByType(ctype, this._customer);
		}catch(Exception e)
		{
			
			CustomerException.CustomerExceptionHandler(e);
		}
		return this.getAllcoupons;
		
	}

	@Override
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price) 
	{
		try{
			this.getAllcoupons = _customerdbda.getAllCustomerCouponByPrice(_customer, price);
		}catch(Exception e)
		{
			
			CustomerException.CustomerExceptionHandler(e);
		}
		return this.getAllcoupons;
	}

	@Override
	public CouponClientFacadeDAO login(String CustName, String password, clientType cltyp) throws ClassNotFoundException, CustomerException {
		boolean is_Customer  = false;
		CustomerFacade custfacade =null;
		if(cltyp.equals(clientType.CUSTOMER))
		{ try
			{
				if(_customerdbda.login(CustName, password)==true)
				{
					is_Customer = true;
					custfacade = new CustomerFacade();
					System.out.println("Login successfully");
				}
				else{
					is_Customer = false;
					System.out.println("the  user  name  or  password  is  wrong  try agian");
				}
			}catch(Exception e)
			{
				System.out.println(e.getClass().getSimpleName());
				CustomerException.CustomerExceptionHandler(e);
			}
		}
		else
		{
			is_Customer = false;
			throw new CustomerException("you  try to login in as"+cltyp +", you  dont  have  the write access type choose you type if customer or admin or company");
		}
		return custfacade;
	}
	
	

}
