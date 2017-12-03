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
	
	
	public CustomerFacade() throws ClassNotFoundException, SQLException
	{
		try{
			
		
			this._customerdbda = new CustomerDBDA();
			
			this._coupondbda = new CouponDBDA();
			this._coupon = new Coupon();
			this.set_customer(new Customer());
			this.getAllcoupons = new ArrayList<Coupon>();
		}catch(Exception  e)
		{
			//CouponException.CouponExceptionHandler(e);
			//CustomerException.CustomerExceptionHandler(e);
		}
		
	}
	@Override
	public void purchaseCoupon(Coupon coup,Customer cust) throws ClassNotFoundException, CouponException
	{
		try{
			this._customerdbda.addCouponCustomer(cust, coup);
		}catch(Exception e)
		{
			
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
	public boolean login(String CustName, String password, clientType cltyp) throws ClassNotFoundException {
		boolean is_Customer  = false;
		CustomerFacade custfacade =null;
		if(cltyp.equals("CUSTOMER"))
		{ try
			{
				if(_customerdbda.login(CustName, password))
				{
					is_Customer = true;
					custfacade = new CustomerFacade();
					System.out.println("Login succefull");
				}
				else{
					is_Customer = false;
					System.out.println("the  user  name  or  password  is  wrong  try agian");
				}
			}catch(Exception e)
			{
				
				//CustomerException.CustomerExceptionHandler(e);
			}
		}
		else
		{
			is_Customer = false;
			System.out.println("you  try to login in as"+cltyp +", you  dont  have  the write access type choose you type if customer or admin or company");
		}
		return is_Customer;
	}
	/**
	 * getter and  setter for class member
	 * @param
	 * @return 
	 */
	public CouponDBDA get_coupondbda() {
		return _coupondbda;
	}
	public void set_coupondbda(CouponDBDA _coupondbda) {
		this._coupondbda = _coupondbda;
	}
	public Coupon get_coupon() {
		return _coupon;
	}
	public void set_coupon(Coupon _coupon) {
		this._coupon = _coupon;
	}
	public Collection<Coupon> getGetAllcoupons() {
		return getAllcoupons;
	}
	public void setGetAllcoupons(Collection<Coupon> getAllcoupons) {
		this.getAllcoupons = getAllcoupons;
	}
	public CustomerDBDA get_customerdbda() {
		return _customerdbda;
	}
	public void set_customerdbda(CustomerDBDA _customerdbda) {
		this._customerdbda = _customerdbda;
	}
	public Customer get_customer() {
		return _customer;
	}
	public void set_customer(Customer _customer) {
		this._customer = _customer;
	}

}
