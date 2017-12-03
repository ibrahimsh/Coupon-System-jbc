package sqlQuery;

import java.sql.Connection;

import java_beans.Company;

/**
 * 
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * @see
 *<b>this class include all database queries create, get,insert,update,delete 
 * into database tables that related to company</b>
 */
public class companyQuerys {
	
	/**
	 * my SQL Queries to interact with system database - company table
	 */
	public static String createCompany = "CREATE TABLE IF NOT EXISTS Company "
			+ "(ID  int(64) NOT NULL AUTO_INCREMENT  "
			+ ", comp_name varchar(100) NOT NULL ,"
			+ " password varchar(20) NOT NULL "
			+ ", email varchar(15),"
			+ " primary key(ID))";
	public static String searchCompanyByID = "SELECT * FROM company WHERE ID= ?";
	public static String insertIntoCompany = "INSERT INTO Company(comp_name,password,email) VALUES (?,?,?)";
	public static String getCompany = "SELECT * FROM Company";
	public static String removeCompanyByName = "DELETE FROM Company " +" where comp_name = ?";
	public static String removeCouponcompany = "DELETE FROM Company_Coupon where COMP_ID = ?";
	public static String updateCompany = "UPDATE Company SET comp_name=?,password=?,email=? WHERE ID=?";
	public static String addConstrain = "ALTER TABLE Company ADD UNIQUE(comp_name)";
	public static String login = "SELECT comp_name,password From company";
    public static String createCompCouponT = "CREATE TABLE IF NOT EXISTS Company_Coupon "
                + "(COMP_ID int(64) NOT NULL ,"
                + "COUP_ID int(64) NOT NULL ,"
                + "primary key (COMP_ID,COUP_ID)"
                + ")";
       
    public static String compCoupon = "INSERT INTO Company_Coupon (COMP_ID, COUP_ID) VALUES (?, ?)";
    public static String getCompanyCoupons = "SELECT * FROM company_coupon WHERE COMP_ID =? " ;
    public static String findCompanyID = "SELECT ID From Company WHERE comp_name=?";
	


}
