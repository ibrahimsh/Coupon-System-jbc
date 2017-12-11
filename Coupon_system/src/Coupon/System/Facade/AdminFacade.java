package Coupon.System.Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.DuplicateFormatFlagsException;

import com.sys.exception.CouponException;
import com.sys.exception.CustomerException;
import com.sys.exception.companyException;

import DAO_connection.CompanyDBDA;
import DAO_connection.CouponDBDA;
import DAO_connection.CustomerDBDA;
import java_beans.Company;
import java_beans.Coupon;
import java_beans.Customer;
/**
 * @AminFacade
 * 
 * @author MatrixComp
 * this  class of AdminFace,the admin page control all system
 *create,update,remove,search by id, by name , for both Company and  customer
 *this class implement form two interfaces 
 *@interface AdminFacadeDAO  that contain all method  need to manage the system 
 *@interface CouponclientFacade  that contain min login method  to  get inside  the  system
 *
 */
public class AdminFacade implements AdminFacadeDAO,CouponClientFacadeDAO
{
	/**
	 * initialize the member of  ADmin facade that composite from java beans class and  the classes that connect to database
	 * and interact with table by using  queries
	 */
	private CompanyDBDA _companydbda;
	private CouponDBDA _coupondbda;
	private CustomerDBDA _customerdbda;
	private Collection<Company> _allCompanys = new ArrayList<Company>();
	private Collection<Customer> _allCustomers = new ArrayList<Customer>();
	private Collection<Coupon> _allcoupons = new ArrayList<Coupon>();
	private Company Comp ;
	private Customer _customer ;
	private Coupon _coupon;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @AdminFacade constructor to initialize class facade member
	 */
	public AdminFacade() throws ClassNotFoundException, SQLException {
		this._companydbda =new CompanyDBDA();
		this._customerdbda =new CustomerDBDA();
		this._coupondbda = new CouponDBDA();
		this.Comp = new Company();
		this._customer = new Customer();
		this._coupon = new Coupon();
	}
/**
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws companyException 
 * @createCompany
 * create company with @Companydbda 
 */
	@Override
	public void createCompany(Company comp)  {
		try{
			this._companydbda.createCompany(comp);
		}catch(Exception e)
		{
			//companyException.CompanyExceptionHandler(e);
			e.getMessage();
		}
	
		
	}
/**
 * @removeComapny 
 * @param Company
 * removing  company with CompanyDBDA from system
 * @throws  
 */
	@Override
	public void removeCompany(Company comp)   {
		try {
			this._companydbda.removeCompany(comp);
		}catch(Exception e)
		{
			companyException.CompanyExceptionHandler(e);
			//e.getMessage();
		}
	
		
	}
/**
 * @updateCompany
 * @param Company
 * to update company information in system via CompanyDBDA class 
 * @throws companyException 
 * @throws SQLException 
 * @throws  
 * @throws SQLException 
 * @throws InterruptedException 
 * @throws ClassNotFoundException 
 */
	@Override
	public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, companyException   {
		try{
			this._companydbda.updateCompany(comp);
		}catch(Exception e)
		{
			companyException.CompanyExceptionHandler(e);
		//	e.getMessage();
		}
	
		
	}
/**
 * @getCompany
 * @param id
 * found company by id in system using  CompanyDBDA to find  the  company in database
 * @return
 * @throws companyException 
 */
	@Override
	public Company getCompany(int id) 
	{
		
		try{
			this.Comp = this._companydbda.getCompany(id);
		}catch (Exception e)
		{
			companyException.CompanyExceptionHandler(e);
		}
		return this.Comp;
	}
/**
 * @getAllCopany as a list of company
 * find  all company in system that exist  in system database
 * @return
 */
	@Override
	public Collection<Company> getAllCompanies() {
		try{
			
			this._allCompanys=_companydbda.getAllCompanies();
		}catch (Exception e)
		{
			companyException.CompanyExceptionHandler(e);
		}
		return this._allCompanys;

	}
/**
 * @createCustomer
 * @param Customer
 * add customer to system database by connect to database through @CustomerDBDA
 * @throws CustomerException 
 */

	@Override
	public void createCustomer(Customer cust) 
	{
		try{
			this._customerdbda.createCustomer(cust);
		}catch (Exception e)
		{
			CustomerException.CustomerExceptionHandler(e);
			//e.getMessage();
		}
	}
/**
 * remove  Customer  from System through CustomerDBDA
 * @removCustomer
 * @param(Customer)
 */
	@Override
	public void removeCustomer(Customer cust) 
	{	try
		{
	
				this._customerdbda.removeCustomer(cust);
		}catch (Exception e)
		{
			e.printStackTrace();
			CustomerException.CustomerExceptionHandler(e);
		}
	
	}
/**
 * update Customer information 
 * @updatCustomer
 * @param(Customer)
 */
	@Override
	public void updateCustomer(Customer cust)
	{
		try
		{
			this._customerdbda.updateCustomer(cust);
		}catch (Exception e)
		{
			CustomerException.CustomerExceptionHandler(e);
		}
	}
/**
 * find customer  in system by id 
 * @getCustomer
 * @param(id)
 * @return
 */
	@Override
	public Customer getcustomer(long id)
	{
		try
		{

			this._customer =this._customerdbda.getCustomer(id);
		}catch (Exception e)
		{
			e.printStackTrace();
			CustomerException.CustomerExceptionHandler(e);
		}
		return this._customer;
		
	}
/**
 * find all customer in system 
 * @getAllcustomer
 * @return
 */
	@Override
	public Collection<Customer> getAllcustomer()
	{
		try
		{
		 this._allCustomers=_customerdbda.getAllCustomers();
		}catch (Exception e)
		{
			CustomerException.CustomerExceptionHandler(e);
		}
		 return this._allCustomers;
		
	}
	/**
	 * find all Coupons in system that stored in system database
	 * @getAllCoupons
	 * @return
	 */
	public Collection<Coupon>getAllcoupon()
	{
		try{
		this._allcoupons = _coupondbda.getAllCoupon();
		}catch (Exception e)
		{
			CouponException.CouponExceptionHandler(e);
		}
		return _allcoupons;
		
	}
	/**
	 * @login
	 * @return
	 * login for only administrator that manage the system 
	 * @param (username,password,client type ->ADMIN)
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public CouponClientFacadeDAO login(String UserName, String password, clientType clienttyp) throws ClassNotFoundException, SQLException 
	{
		boolean isAdmin = false;
		AdminFacade adminfacade = null ;
		if(UserName.equals("admin") && password.equals("1234") && clienttyp.equals(clientType.ADMIN))
		{
			isAdmin = true;
			adminfacade = new AdminFacade();
			System.out.println("hi Admin you are in");
		}
		else{
			isAdmin = false;
			System.err.println("admin user name or  paswword wrong try agian or  change  to ADMIN mode");
		}
		return adminfacade;
	}

}
