
package com.coupon.system.queries;
/**
 * 
 * @author MatrixComp-ibrahim shweiki
 * @version 1.0v
 * <br>
 *this class include all database queries create, get,insert,update,delete  into database tables that related to customer
 */
public class customerQuerys {
	public static String createTable = "CREATE TABLE IF NOT EXISTS Customers"
			+ "(ID  int(64) NOT NULL AUTO_INCREMENT  "
			+ ", CUST_NAME varchar(100) NOT NULL UNIQUE "
			+ " ,PASSWORD varchar(20) NOT NULL, "
			+ " primary key(ID))".trim();
	public static String searchCustomerByID = "SELECT * FROM Customers WHERE ID='%s'";
	public static String insertIntoCustomer = "INSERT INTO Customers(CUST_NAME,PASSWORD) VALUES (?,?)";
	public static String getCustomer = "SELECT * FROM customers";
	public static String removeCustomerByName = "DELETE FROM Customers WHERE CUST_NAME =?";
	public static String removeCustomerCoupon = "DELETE FROM Customer_Coupon WHERE CUST_ID = ?";
	public static String updateCustomer = "UPDATE customers SET CUST_NAME=?,PASSWORD=? WHERE ID=?";
	//public static String addConstrain = "ALTER TABLE Company ADD UNIQUE(comp_name)";
	//public static String login = "SELECT * customers WHERE CUST_NAME=? AND PASSWORD = ?".trim();
	public static String login = "SELECT CUST_NAME,PASSWORD FROM customers";
	public static String addcouponCustomer = "CREATE TABLE IF NOT EXISTS Customer_Coupon(CUST_ID int(64),COUP_ID int(64),"
			+ "primary key(CUST_ID,COUP_ID))";
	public static String customerCoupon ="INSERT INTO Customer_Coupon(CUST_ID, COUP_ID) VALUES (?, ?)";
	public static String customerId = "SELECT ID FROM Customers WHERE CUST_NAME = ?";
	public static String getCustomerCoupons = "SELECT * FROM customer_coupon WHERE CUST_ID =? " ;
	public static String getCustCoupByType = "SELECT  * FROM coupons where ID=? and type = ?";
	public static String getCustCouponByPrice = "SELECT * FROM coupons WHERE ID = ? and price <= ?";
	public static String CouponAmount = " SELECT amount from coupons WHERE ID=?";

}
