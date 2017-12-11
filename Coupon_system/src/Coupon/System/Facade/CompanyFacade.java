package Coupon.System.Facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.companyException;

import DAO_connection.CompanyDBDA;
import DAO_connection.CouponDBDA;
import java_beans.Company;
import java_beans.Coupon;
import java_beans.CouponType;
/**
 * 
 * @author MatrixComp
 *@CompanyFaced Company class to manage  company coupon ,create coupons ,delete coupon,update coupon
 *find coupon by id  ..etc
 *and  this  class  implement from two interfaces 
 *@interface CompanyFacadeDAO that  contain all methods for  company to use
 *@interface CouponClientFacadeDAO the contain main login method  to give  the  company the ability to enter the system 
 *using  user name and  password with CCOMPY client type  mode 
 */
public class CompanyFacade implements CompanyFacadDAO,CouponClientFacadeDAO
{
/**
 * CompanyFacade member that composite from DBDA classes to connect to database and java beans classes to initialize object information
 */
	private CompanyDBDA _companydbda;
	private CouponDBDA _coupondbda;
	private Company _company;
	private Coupon _coupon;
	private Collection<Coupon>allCoupons = new ArrayList<Coupon>();
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @CompanyFacade
	 * @constructor
	 * initializing  the class member
	 */
	public CompanyFacade() throws ClassNotFoundException, SQLException
	{
		this._companydbda = new CompanyDBDA();
		this._coupondbda = new CouponDBDA();
		this._company =new Company();
		this._coupon =new Coupon();
		
	}
	/**
	 * @createCoupon
	 * @param {@Coupon}
	 * this  method get  company the  ability to create coupon
	 * @throws companyException 
	 * @throws  
	 * @throws CouponException 
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	@Override
	public void createCoupon(Coupon coup,Company comp) throws companyException{
		
		try{
			this._coupondbda.createCoupon(coup);
			//this._company.setId(_companydbda.getID(this._company.getCompName()));
			//System.out.println("the id  of  company"+this._company.getId());
			this._companydbda.addCouponToCompany(coup,comp);
		}catch(Exception e)
		{
			System.out.println("createCoupon Company facade :: "+e.getClass().getSimpleName());
			//throw new companyException("problem with creating  coupon");
			companyException.CompanyExceptionHandler(e);
			e.printStackTrace();
		}
		
		
	}
/**
 *@throws companyException 
 * @throws CouponException 
 * @throws SQLException 
 * @throws InterruptedException 
 * @removeCoupon
 *@param{Coupon}
 *this method  give  the  company to remove  coupon from there table 
 */
	@Override
	public void removeCoupon(Coupon coup) throws companyException, InterruptedException, SQLException, CouponException {

		try{
			this._coupondbda.removeCoupon(coup);
		}catch (Exception e)
		{
			companyException.CompanyExceptionHandler(e);
		}
		
		
		
	}
/**
 * the Company can update  coupon information through CouponDBDA
 * @updateCoupon
 * @param Coupon
 */
	@Override
	public void updateCoupon(Coupon coup) 
	{ 	try
		{
				this._coupondbda.updatecoupon(coup);
		}catch(Exception e)
		{
			e.printStackTrace();
			CouponException.CouponExceptionHandler(e);
		
		}
		
	}
/**
 * get All coupons  that company created 
 * @getAllCoupons list 
 * @param Company
 * @return list
 */
	@Override
	public Collection<Coupon> getAllcoupons(Company comp) 
	{
		try{
			this.allCoupons = this._companydbda.getCoupons(comp);
		}catch(Exception e)
		{
			e.printStackTrace();
			companyException.CompanyExceptionHandler(e);
		}
		return this.allCoupons ;
	}
/**
 * @getCoupon
 * @param id 
 * find Company Coupon by id 
 * @return Coupon
 */
	@Override
	public Coupon getCoupon(long id) 
	{
		try
		{
			
			this._coupon = this._coupondbda.getCoupon(id);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getClass().getSimpleName());
			CouponException.CouponExceptionHandler(e);
			
		}
		return this._coupon;
	}
/**
 * @getCouponByType-companyCouponlist by type of  coupon
 * search for company coupon by type of  coupon
 * @return allCoupons
 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType ctyp,Company comp)
	{	try
		{
		 this.allCoupons=this._companydbda.getCouponsByType(ctyp,comp);
		}catch(Exception e)
		{
			e.printStackTrace();
			CouponException.CouponExceptionHandler(e);
			
		}
		return this.allCoupons;
	}
/**
 * @login
 * @param-UserName
 * @param-password
 * @param-clientType
 * @return
 * login method  to give  the  ability for company to access the system and  to manage  there page and  database
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
	@Override
	public CouponClientFacadeDAO login(String UserName, String password, clientType type) throws ClassNotFoundException, SQLException
	{	boolean isCompany = false;
		this._companydbda = new CompanyDBDA();
		CompanyFacade compfacade = null;
		if(type.equals(clientType.COMPANY))
		{ 
			try{
				if(this._companydbda.login(UserName, password)==true)
				{
					isCompany= true;
					compfacade =new CompanyFacade();
				}
			}catch(Exception e)
			{
				//CouponException.CouponExceptionHandler(e);
				
			}
		}
		else{
			isCompany = false;
			System.err.println("choose the correct account type you want to access to (admin,Company,Customer)");
		}
		return compfacade;
	}
	/**
	 * @getter
	 * @setter
	 * for class members
	 * @return
	 */
	public Company get_company() {
		return _company;
	}
	public void set_company(Company _company) {
		this._company = _company;
	}
	public Coupon get_coupon() {
		return _coupon;
	}
	public void set_coupon(Coupon _coupon) {
		this._coupon = _coupon;
	}


}
