package DAO_connection;

import java.sql.SQLException;
import java.util.Collection;

import com.sys.exception.CouponException;
import com.sys.exception.CustomerException;

import java_beans.*;
/**
 * 
 * @author MatrixComp - ibrahim shweiki
 * 
 * @version 1.0v
 * @see
 * <b>Customer interface that contain all method  that should  the Customer use 
 * and  it consider as Data Access Object </b>
 *
 */
public interface CustomerDAO {
	/**
	 * <b>Create Customer - this method give the ability to the System Administrator </b>
	 * @param cust - passing parameter  customer type to created in database
	 * @return void - no data return
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	public void createCustomer(Customer cust) throws InterruptedException, SQLException, CustomerException;
	/**
	 * Method  to give the System administrator the  ability to remove Customer  form the system 
	 * @param cust - passing parameter Customer Type (the customer we want to remove)
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	public void removeCustomer(Customer cust) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @getCustomer  method that find  specific Customer by id  
	 * @param id - customer id 
	 * @return - customer information
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	public Customer getCustomer(long id) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @getAllCustomer : method that find all customers  in system  
	 * @return - all customer as list 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	public Collection<Customer> getAllCustomers() throws InterruptedException, SQLException, CustomerException;
	/**
	 * @getCoupons - method  that find all coupon in system and  show  it  to customer  as list then the customer
	 * can choose his coupon 
	 * 
	 * @return  -list of All coupons that stored in system database
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws CouponException 
	 * @throws InterruptedException 
	 * @throws CustomerException 
	 */
	/*
	public Collection<Coupon> getAllCoupons() throws ClassNotFoundException, SQLException, InterruptedException, CouponException, CustomerException;
	/**
	 * @LOGIN - method  that verified if  the Customer user name and Password 
	 *  stored in database if its  stored in system then the customer can access his account   
	 * @param cust - Customer User Name 
	 * @param password - Customer Password
	 * @return - return boolean if the userName and password exist in system (true) else (false)
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	public boolean login(String cust , String password) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @getCoupons method that find all Customer purchased Coupon 
	 * @param cust - specific customer purchased coupon
	 * @return - list of all coupons that Customer purchased
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	Collection<Coupon> getCoupons(Customer cust) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @update - method that give  the  ability to update specific Customer information and change in database
	 * where customer information store
	 * @param cust - specific customer object as parameter need to update  
	 * @throws SQLException
	 * @throws InterruptedException 
	 * @throws CustomerException 
	 */
	void updateCustomer(Customer cust) throws SQLException, InterruptedException, CustomerException;
	/**
	 * @getAllCustomerCouponByPrice  - find all customer purchased Coupon by specific Price
	 * @param cust - the customer we want to find for him
	 * @param price - the specific price as condition to search for coupons
	 * @return  - list of  coupon with specific price ,for specific Customer
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	Collection<Coupon> getAllCustomerCouponByPrice(Customer cust, double price) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @getCustomerId - find specific Customer id thought the system while the id is  auto increment in database 
	 * @param cust - specific Customer need to find his specific ID in database
	 * @return - ID of  the Customer from DataBase
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	long getCustomerID(Customer cust) throws InterruptedException, SQLException, CustomerException;
	/**
	 * @addCouponToCustomer  - method that add purchased Coupon to Customer Coupon table in data base 
	 * @param cust - specific Customer purchased Coupon 
	 * @param coup - Coupon that Customer  purchased
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 * @throws CouponException 
	 * @throws ClassNotFoundException 
	 */
	void addCouponCustomer(Customer cust, Coupon coup) throws InterruptedException, SQLException, CustomerException, CouponException, ClassNotFoundException;
	/**
	 * @getAllCouponsByType  - find  all Customer purchase Coupon  by coupon Type 
	 * @param ctyp - Coupon Type 
	 * @param cust - the Customer that we want to find the coupons
	 * @return - Coupons as List
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	Collection<Coupon> getAllCouponsByType(CouponType ctyp, Customer cust) throws InterruptedException, SQLException, CustomerException;
	Collection<Coupon> getAllCoupons() throws SQLException, InterruptedException, CouponException, CustomerException, ClassNotFoundException;
	//Collection<Coupon> getCouponById(long id);
	

}
