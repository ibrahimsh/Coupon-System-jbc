package DAO_connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.print.attribute.standard.Severity;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Util;
import com.sys.exception.CouponException;
import com.sys.exception.CustomerException;

import java_beans.Coupon;
import java_beans.CouponType;
import system_connection.connectionPool;
/**
 * this  class  can connect to database by using ConnectionPool class
 * @CouponDBDA
 * @author MatrixComp
 *this  class  implement the  method that should  use from @CouponDAO interface
 *@createCoupon @Coupon 
 *@removeCoupon @coupon
 *@updateCoupon @Coupon
 *@getCoupon by @coupon Id 
 *@getAllCoupon return all coupons from database
 *@getCouponId by coupon name 
 */
public class CouponDBDA  implements CouponDAO{
	private connectionPool conPool;//Connection pool
	Connection conn =null ;
	/**
	 * CouponDBDA constructor  initialize  the  database connection
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CouponDBDA() throws ClassNotFoundException, SQLException
	{
		
		this.conPool = connectionPool.getInstance();
		
	
	}

	/**
	 * create coupon and  added to coupon table if  table  doesn't exist will create coupon table and  insert coupon information into table
	 * except the id  while  the  coupon id  is  auto increment 
	 * @param coup
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	@Override
	public void createCoupon(Coupon coup) throws InterruptedException, SQLException, CouponException {
		
			conn = conPool.getConnection();
			PreparedStatement createCouponT = conn.prepareStatement(sqlQuery.couponQuerys.createCouponT);
			createCouponT.executeUpdate();
			PreparedStatement uniqueTitle = conn.prepareStatement(sqlQuery.couponQuerys.uniqueTitle);
			uniqueTitle.executeUpdate();
			PreparedStatement cst = conn.prepareStatement(sqlQuery.couponQuerys.insert,Statement.RETURN_GENERATED_KEYS);
			cst.setString(1,coup.getTitle());
			cst.setDate(2,(Date) coup.getStartDate());
			cst.setDate(3,(Date) coup.getEndDate());
			cst.setInt(4, coup.getAmount());
			cst.setString(5,coup.getCtyp().getValue());
			cst.setString(6, coup.getMessage());
			cst.setDouble(7,coup.getPrice());
			cst.setString(8,coup.getImage());
			int var = cst.executeUpdate();
			if(var == 1){
				System.out.println("the coupon is created thank you ver much");
				try( ResultSet getId = cst.getGeneratedKeys()){
					if(getId.next())
					{
						coup.setId(getId.getLong(1));
						System.out.println("the id for this coupon is  = "+coup.getId());
					}
				}
				
			}
			else
			{
				throw new CouponException("Cannot create coupon or the coupon exist");
				//System.out.println("try agian with other coupon type ");
			}
		
			conPool.returnConnection(conn);
		
	}
	/**
	 * remove coupon from coupon table  Customer Coupons table and company Coupons table 
	 * @param coup
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	@Override
	public void removeCoupon(Coupon coup) throws InterruptedException, SQLException, CouponException {
		
		
		
			conn = conPool.getConnection();
			PreparedStatement removeCouponCustomer = conn.prepareStatement(sqlQuery.couponQuerys.removeCustcoupon);
			removeCouponCustomer.setLong(1, this.CouponId(coup));
			if(removeCouponCustomer.executeUpdate()>0)
			{
				System.out.println("the Coupon"+coup.getTitle() + "removed Successfully from Customer Coupon table");
			}
			else{
				System.out.println("the coupon dosent exist in customer Coupons");
				throw new CouponException("the coupon dosent exist in customer Coupons");
			}
			PreparedStatement removeCouponCompany = conn.prepareStatement(sqlQuery.couponQuerys.removeCompcoupon);
			removeCouponCompany.setLong(1, this.CouponId(coup));
			if(removeCouponCompany.executeUpdate()>0)
			{
				System.out.println("the coupon"+coup.getTitle()+ "removed Successfully from Company coupons table");
			}
			else{
				System.out.println("the coupon dosent exist in Company Coupons");
				throw new CouponException("the coupon dosent exist in Company Coupons");
			}
			PreparedStatement rem = conn.prepareStatement(sqlQuery.couponQuerys.removeCoupon);
			rem.setLong(1,this.CouponId(coup));
			if(rem.executeUpdate()>0){
				System.out.println("the Coupon"+coup.getTitle() + "removed Successfully from Coupon system");
			}
			else{
				System.out.println("the coupon dosent exist in system");
				throw new CouponException("the coupon dosent exist in  Coupons");
			}
		
			conPool.returnConnection(conn);
		
	}
	/**
	 * update specific Coupon information 
	 * @param coup
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	@Override
	public void updatecoupon(Coupon coup) throws InterruptedException, SQLException, CouponException {
		
		
		
			conn = conPool.getConnection();
			PreparedStatement update = conn.prepareStatement(sqlQuery.couponQuerys.updateCoupon);
			if(coup.getTitle().length()>=1)
			{
				update.setString(1,coup.getTitle());
			}
			update.setDate(2, (Date)coup.getStartDate());
			update.setDate(3, (Date)coup.getEndDate());
			if(coup.getAmount()>0)
			{
				update.setInt(4, coup.getAmount());
			}
			if(coup.getCtyp().getValue().length()>0)
			{
				update.setString(5, coup.getCtyp().getValue());
			}
			if(coup.getMessage().length()>0)
			{
				update.setString(6, coup.getMessage());
			}
			if(coup.getPrice()>0)
			{
				update.setDouble(7, coup.getPrice());
			}
			if(coup.getImage().length()>0)
			{
				update.setString(8, coup.getImage());
			}
			update.setLong(9, coup.getId());
			
			 int d =update.executeUpdate();
			
			if( d == 1)
			{
				System.out.println("the update is  successfull enjoy");
			}
			else{
				System.out.println("the coupon dosent exist in customer Coupons and cannot update it");
				throw new CouponException("the coupon dosent exist in customer Coupons cannot update it try with another Coupon or contact the admin");
			}
		
		    conPool.returnConnection(conn);
		
	}
	/**
	 * find coupon by coupon id 
	 * @param id
	 * @return Coupon
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	@Override
	public Coupon getCoupon(long id) throws InterruptedException, SQLException, CouponException 
	{
		//Connection conn  = null ;
			Coupon coup =null;
			//String getCouponById =  "SELECT * FROM Coupons WHERE ID='%1s'";
		
		
			conn= conPool.getConnection();
			Statement gco = (Statement) conn.createStatement();
			ResultSet rs   = gco.executeQuery(String.format(sqlQuery.couponQuerys.getCouponById,id));
			
			if(rs != null)
			{
				while(rs.next())
				{
					coup = new Coupon();
					long idc =rs.getLong("ID");
					String ti = rs.getString("title");
					Date sd = rs.getDate("startDate");
					Date ed= rs.getDate("endDate");
					int am = rs.getInt("amount");
					String t= rs.getString("type");
					String ms = rs.getString("message");
					double pr = rs.getDouble("price");
					String img =rs.getString("image");
					coup.setId(idc);
					coup.setTitle(ti);
					coup.setStartDate(sd);
					coup.setEndDate(ed);
					coup.setAmount(am);
					
					coup.setCtyp(CouponType.findValue(t));
					coup.setMessage(ms);
					coup.setPrice(pr);
					coup.setImage("img");
					
					
				}
				System.out.println("the coupon found  by id ");
			}
			else{
		
				throw new CouponException("the coupon dosent exist try again thank you");
			}
	
			
			if(conn == null)
			{
				conPool.returnConnection(conn);
			}
		
		
		return coup;
	}
/**
 * find  all coupons in database
 * @return List 
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws CouponException 
 */
	@Override
	public Collection<Coupon> getAllCoupon() throws InterruptedException, SQLException, CouponException 
	{
		
		//Connection conn = null ; 
		Coupon coup = null ;
		Statement allc = null;
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		conn = conPool.getConnection();
		allc = (Statement) conn.createStatement();
		ResultSet rs = allc.executeQuery(sqlQuery.couponQuerys.getAllcoupons);
		if(rs != null)
		{
			 while(rs.next())
			 {
				    coup = new Coupon();
					long idc =rs.getLong("ID");
					String ti = rs.getString("title");
					Date sd = rs.getDate("startDate");
					Date ed= rs.getDate("endDate");
					int am = rs.getInt("amount");
					String t= rs.getString("type");
					String ms = rs.getString("message");
					double pr = rs.getDouble("price");
					String img =rs.getString("image");
					coup.setId(idc);
					coup.setTitle(ti);
					coup.setStartDate(sd);
					coup.setEndDate(ed);
					coup.setAmount(am);
					
					coup.setCtyp(CouponType.findValue(t));
					coup.setMessage(ms);
					coup.setPrice(pr);
					coup.setImage("img");
					coupons.add(coup);
			 }
			 System.out.println("list of  coupon is  found  successfully ");
		}else{
		
				throw new CouponException("the table is  empty no coupons are found ");
		}
			
		
		
		conPool.returnConnection(conn);
		return coupons;
	}
/**
 * find coupons  by coupon type 
 * @param coupt
 * @return List
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws CouponException 
 */
	@Override
	public Collection<Coupon> getCouponByType(CouponType coupt) throws InterruptedException, SQLException, CouponException
	{
		
		//Connection conn = null ; 
		Coupon coup = null ;
		Statement allc = null;
		String sql = sqlQuery.couponQuerys.getCouponByType.concat("'"+coupt+"'");
		String getCouponByType = "SELECT * From Coupons Where type = '"+coupt +"'";
		System.out.println("coupon  type  is  "+ coupt);
		System.out.println(getCouponByType);
		Collection<Coupon> coupons = new ArrayList<Coupon>();
	    conn = conPool.getConnection();
	    allc = (Statement) conn.createStatement();
	    ResultSet rs = allc.executeQuery(sqlQuery.couponQuerys.getCouponByType.concat("'"+coupt.getValue()+"'"));
	    if(rs != null)
	    {
			 while(rs.next())
			 {
				    coup = new Coupon();
					long idc =rs.getLong("ID");
					String ti = rs.getString("title");
					Date sd = rs.getDate("startDate");
					Date ed= rs.getDate("endDate");
					int am = rs.getInt("amount");
					String t= rs.getString("type");
					String ms = rs.getString("message");
					double pr = rs.getDouble("price");
					String img =rs.getString("image");
					coup.setId(idc);
					coup.setTitle(ti);
					coup.setStartDate(sd);
					coup.setEndDate(ed);
					coup.setAmount(am);
					//coup.setCtyp(coupt);
					coup.setCtyp(coupt);
					coup.setMessage(ms);
					coup.setPrice(pr);
					coup.setImage("img");
					coupons.add(coup);
			 }
			 System.out.println("the coupons  list found  successfully by type");
		}else{
				throw new CouponException("the table is  empty no coupons are found ");
		}
	    conPool.returnConnection(conn);
		return coupons;
		
	}
	/**
	 * find coupon id  by coupon name where the coupon name  is  unique 
	 * @param coup
	 * @return
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CouponException 
	 */
	@Override
	public long CouponId(Coupon coup) throws InterruptedException, SQLException, CouponException
	{
		//Connection conn  = null ;
		long id  = 0;
	
			conn = conPool.getConnection();
			PreparedStatement st = conn.prepareStatement(sqlQuery.couponQuerys.getCouponId);
			st.setString(1,coup.getTitle());
			ResultSet res = st.executeQuery();
		if(res !=null)
		{
			while(res.next())
			{
				id = res.getInt(1);
				
			}
			System.out.println("the coupn id  is  found");
		}
		else{
			throw new CouponException("No id  found or the the coupon dosent not exist ");
		}
		conPool.returnConnection(conn);
		return id;
		
	}

}
