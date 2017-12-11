package DAO_connection;

import java.sql.SQLException;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.companyException;

import java_beans.*;
/**
 * 
 * @author MatrixComp - ibrahim shweiki
 * @version 1.0v <br>
 * <b>
 *Company interface contains all method that should use to interact with company information
 *and Database queries  to store or remove or update or get information from system
 *</b>
 */
public interface CompanyDAO
{
	/**
	 * <b> this  method  give the administrator to create company table in database and  to add company to database system</b>
	 * @param comp - passing  company object as parameter that include company information to store into system
	 * @return return Company id  that newly added
	 * @throws companyException - send  error message related to Company only
	 * @throws SQLException
	 * @throws InterruptedException
	 */
 public  long createCompany(Company comp) throws companyException, SQLException, InterruptedException;
 /**
  * <b> Remove  Company form system by passing Company information that we want to remove form system
  * @param comp -Company object include company information
  * @throws SQLException
 * @throws InterruptedException 
 * @throws companyException 
  */
 public void removeCompany(Company comp) throws SQLException, InterruptedException, companyException;
 /**
  * <b>update specific company information 
  * </b>
  *  <br>
  * @param comp - parameter in type Conpany that include all new comapny inforamtion need to update in system
  * @throws ClassNotFoundException
  * @throws InterruptedException
 * @throws SQLException 
 * @throws companyException 
  */
 public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, companyException;
 /**
  * <b> find  company by specific ID where the ID is  auto generated  in database</b>
  * <br>
  * @param id - Company id that we want to find  company by id 
  * @return - Company information that founded 
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws companyException 
  */
 public Company getCompany(int id) throws InterruptedException, SQLException, companyException;
 /**
  * <b> get All Companies in system </b>
  * <br>
  * @return list  of  Companies that founded
 * @throws companyException 
 * @throws InterruptedException 
 * @throws SQLException 
  */
 public Collection<Company> getAllCompanies() throws companyException, InterruptedException, SQLException;
 //public Collection<Coupon> getCoupons();
 /**
  * <b>Login :</b> required  user  name  and  password and  the  method  will check  them via database
  * if  exist or  not and  return true  or  false
  * @param compName - company name
  * @param password - company password
  * @return - true or  false
 * @throws companyException 
 * @throws InterruptedException 
 * @throws SQLException 
  */
 public boolean login(String compName,String password) throws companyException, InterruptedException, SQLException;
 /**
  * <b>get  Company Id </b>  by checking  company where  the  company name  is  unique  cannot store
  *   two  company with the  same  name and  return back  company id 
  * @param company - the company  to find its  id  -company name 
  * @return - return company Id 
  * @throws SQLException
 * @throws InterruptedException 
 * @throws companyException 
  */
 public  long getID(String company) throws SQLException, InterruptedException, companyException;
 /**
  * <b>Company Create Coupon </b> and this  method  will add coupon to company coupon table 
  * @param c - coupon that company created
  * @param com - Company parameter
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws companyException 
 * @throws ClassNotFoundException 
 * @throws CouponException 
  */
 public  void  addCouponToCompany(Coupon c , Company com) throws InterruptedException, SQLException, companyException, ClassNotFoundException, CouponException;
 /**
  * <b> get Company Coupons </b> 
  * @param comp - Company that we want  to find  own coupon
  * @return - list of  coupons 
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws companyException 
  */
 public Collection<Coupon> getCoupons(Company comp) throws InterruptedException, SQLException, companyException;
/**
 * <b> find Company coupon by type </b>
 * @param typ
 * @param comp
 * @return
 * @throws InterruptedException
 * @throws SQLException
 * @throws companyException
 */
Collection<Coupon> getCouponsByType(CouponType typ, Company comp)
		throws InterruptedException, SQLException, companyException;
 
 
}
