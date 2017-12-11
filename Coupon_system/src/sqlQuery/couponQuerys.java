package sqlQuery;
/**
 * 
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * @see
 *<b>this class include all database queries create, get,insert,update,delete 
 * into database tables that related to Coupon</b>
 */
public class couponQuerys {
	/**
	 * my SQL Queries to interact with system database - Coupon table
	 */
	public static String createCouponT = " CREATE TABLE IF NOT EXISTS coupons (ID int(20) AUTO_INCREMENT , "
			+ "title varchar(100) ,"
			+ "startDate DATE,"
			+ "endDate DATE ,"
			+ "amount int(150),"
			+ "type enum('Resturants','Electricity','Food','Health','Sport','Camping','Travel'),"
			+ "message varchar(500),"
			+ "price float,"
			+ "image varchar(250),"
			+ "primary key(ID)) ";
	public static String uniqueTitle = "ALTER TABLE coupons ADD UNIQUE(title)";
	public static String insert ="INSERT INTO coupons (title,startDate,endDate,amount,type,message,price,image) VALUES (?,?,?,?,?,?,?,?) ";
	public static String removeCoupon = "DELETE FROM Coupons WHERE ID = ?";
	public static String updateCoupon =  "UPDATE Coupons SET title=?,startDate=?,endDate=?,amount =?,type=?,"
			+ "message=?, price=?,image=? WHERE ID=? ";
	public static String getCouponById =  "SELECT * FROM Coupons WHERE ID='%s'";
	public static String getAllcoupons = "SELECT * From Coupons ";
	public static String getCouponByType = "SELECT * From Coupons Where type =?";
	public static String getCouponId = "SELECT * FROM Coupons WHERE title = ?" ;
	public static String removeCustcoupon = "DELETE FROM customer_coupon where COUP_ID = ? ";
	public static String removeCompcoupon = "DELETE FROM company_coupon where COUP_ID = ? ";
	
	
}
