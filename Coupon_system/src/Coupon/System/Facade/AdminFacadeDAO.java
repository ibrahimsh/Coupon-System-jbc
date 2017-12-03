package Coupon.System.Facade;
import java.sql.SQLException;
import java.util.Collection;

import com.sys.exception.companyException;

import java_beans.Company;
import java_beans.Customer;
/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * @see
 * 
 * <b> System administrator Facade interface </b>
 * this  interface  include  all method that administrator can use to control system
 */
public interface AdminFacadeDAO {
	
	/**
	 * the admin Can create Company 
	 * @param comp - the Company information need to add to system
	 */
	public void createCompany(Company comp) ;
	/**
	 * remove specific company from system
	 * @param comp -the company need to remove 
	 */
	public void removeCompany(Company comp);
	/**
	 * the administrator can update company information
	 * @param comp -the company need to update information
	 * @throws companyException 
	 * @throws SQLException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, companyException ;
	/**
	 * find Company by specific id
	 * @param id - id of company we looking  for 
	 * @return - company information if  found
	 */
	public Company getCompany(int id);
	/**
	 * get all company in system 
	 * @return - list of  company with each company information
	 */
	public Collection<Company> getAllCompanies();
	/**
	 * the administrator can add Customer to system
	 * @param cust - the  customer  need to add
	 */
	public void createCustomer(Customer cust);
	/**
	 * removing  Customer from system 
	 * @param cust -the customer  need to remove
	 */
	public void removeCustomer(Customer cust);
	/**
	 * update Customer  information
	 * @param cust -ther customer need to update his information
	 */
	public void updateCustomer(Customer cust);
	/**
	 * find  specific customer with his  id 
	 * @param id  -id  of  customer  we want to find 
	 * @return
	 */
	public Customer getcustomer(long id);
	/**
	 * find all customer in system 
	 * @return - list of  customers  with information for  each customer  exist 
	 */
	public Collection<Customer>getAllcustomer();

	

}
