package DAO_connection;


import java.rmi.server.SocketSecurityException;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;
import system_connection.connectionPool;
import com.sys.exception.*;
/**
 * 
 * this  class connect to database via @connectionPool it consider the Engine for  the Customer Account
 * @Customer CustomerDBDA implements  from interface CustomerDAO 
 * @author MatrixComp
 * @developed  by ibrahim shweiki
 * @Sponsor by johne bryce 
 * @teacher itay
 * @data 2017-2018
 * @version 1.0v
 * @see
 * in this  class the  customer  can create table  with table name Customers  if  not  exist in database and  insert  data customer  information in rows 
 * can also update his  information but  he  cannot update  the  id  number  that primary key  and  auto generated
 * the customer  in through this  class  can also purchase  coupon if the  coupon exist in coupons table
 * remove customer
 * search for  customer  by  id  
 * search  for customer by name where  the  name  is  unique not repeated(the data base doesn't  allow  to add customer  with name already exist
 * return customer coupons  as a list
 * find  all customer where  added 
 * login  to customer account with customer  name  and  password that stored  in data base 
 */
public class CustomerDBDA implements CustomerDAO {
	private connectionPool conPool ;//connection pool 
	private long getUserCustomerId;//customer id  setter and  getter 
	private Connection conn = null;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @CustomerDBDA 
	 * Constructor Empty Constructor that initialize the connection POOL to connect to database 
	 * @connectionPool - class connect to database with connection String and  database password
	 */
	public CustomerDBDA() throws ClassNotFoundException, SQLException
	{
		this.conPool =connectionPool.getInstance();	
	}
	/**
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 * 
	 */
	@Override
	public void createCustomer(Customer cust) throws InterruptedException, SQLException, CustomerException,IllegalArgumentException
	{

		conn = conPool.getConnection();
		Statement  createT = conn.createStatement();
		//createT.executeUpdate(sqlQuery.customerQuerys.createTable);
		createT.executeUpdate(sqlQuery.customerQuerys.createTable);
		/*{
			System.out.println("the table Customer is  created successfully ");
		}
		else
		{
			throw new CustomerException("the table  Customer exist");
		}*/
		PreparedStatement createCust = conn.prepareStatement(sqlQuery.customerQuerys.insertIntoCustomer);
		createCust.setString(1,cust.getCustName());
		createCust.setString(2,cust.getPassword());
		int crtcust =createCust.executeUpdate();
		if(crtcust>0)
		{
			System.out.println("the customer " + cust.getCustName() + "added successfully to system enjoy");
		}
		else
		{
			throw new CustomerException(cust.getCustName() +"the Customer is  exist try to add another Customer");
		}
		
		
				//throw new SQLException("cannot create customer try agian");
			
		if(conn != null)
		{
			conPool.returnConnection(conn);
		}
		
	}
		
	/**
	 * @updateCustomer
	 * @param cust -Customer need to update
	 * @throws InterruptedException 
	 * @throws SQLException
	 * @throws CustomerException
	 * <br> 
	 * @see
	 * <b>this method get object type customer and update customer information
	 * inside database</b>
	 *  
	 */
	@Override
	public void updateCustomer(Customer cust) throws InterruptedException, SQLException, CustomerException 
	{
		
		conn = conPool.getConnection();
		
		//String updateCustomer = "UPDATE Customers SET CUST_NAME= ?,PASSWORD= ? WHERE ID= ?";
		PreparedStatement update = conn.prepareStatement(sqlQuery.customerQuerys.updateCustomer);
		
		System.out.println("the  customer id  is :"+this.getUserCustId());
		update.setString(1,cust.getCustName());
		update.setString(2,cust.getPassword());
		update.setLong(3, cust.getId());
		int updateCust =update.executeUpdate();
		if(updateCust>0)
		{
			System.out.println("the Customer updated sucessfully");
		}
		else{
			throw new CustomerException("cannot update customer infromation try agian");
		}
			
		conPool.returnConnection(conn);
	}
	/**
	 * method  to remove the customer for  database and  remove all coupons purchased by customer 
	 * from customer coupons table 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */

	@Override
	public void removeCustomer(Customer cust) throws InterruptedException, SQLException, CustomerException
	{
		
			conn = conPool.getConnection();
			PreparedStatement  remCustCoup = conn.prepareStatement(sqlQuery.customerQuerys.removeCustomerCoupon);
			remCustCoup.setLong(1, this.getCustomerID(cust));
			int res =remCustCoup.executeUpdate();
			if(res>0)
			{
				System.out.println("the customer Coupon delete form customer coupons successfully");
			}
			/*else
			{
				throw new CustomerException("cannot remove Coupon from customer coupon or  the coupon dosent exist");
			}*/
			PreparedStatement rem = conn.prepareStatement(sqlQuery.customerQuerys.removeCustomerByName);
			rem.setString(1, cust.getCustName());
			System.out.println("cust name "+cust.getCustName());
			int r =rem.executeUpdate();
			System.out.println("r"+r);
			if (r>0)
			{
				System.out.println("the customer "+cust.getCustName() + " removed sucessfully from system ");
			}
			else
			{
				throw new CustomerException("the Customer name  "+cust.getCustName()+"  dosent exist ");
				}
	
	//			throw new SQLException("cannot find the customer that you want to remove try again");
	
			conPool.returnConnection(conn);
		
	}

	/**
	 * find customer by id 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public Customer getCustomer(long id) throws InterruptedException, SQLException, CustomerException {
		
		
		Customer ct =null ;
		
		conn = conPool.getConnection();
		Statement getCust = conn.createStatement();
		ResultSet res = getCust.executeQuery(String.format(sqlQuery.customerQuerys.searchCustomerByID,id));
		
	
		while(res.next())
		{
		    ct = new Customer();
			ct.setId(res.getLong("ID"));
			ct.setCustName(res.getString("CUST_NAME"));
			ct.setPassword(res.getString("PASSWORD"));
		}
		if(ct !=null)
		{
			System.out.println("customer id :"+id +" is  found Successfully Enjoy !");
	
		}else{
			throw new CustomerException(" Cannot foud Customer Where the id is "+id +"try agian");
		}
		
				//throw new SQLException("the customer not exist");
	
		
		conPool.returnConnection(conn);
		return ct;
	}

	/**
	 * find all customers in customer  table  return as a list
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws InterruptedException, SQLException, CustomerException {
		
		Collection<Customer> allCust = new ArrayList<Customer>();
		conn = conPool.getConnection();
		Statement st  = conn.createStatement();
		ResultSet re = st.executeQuery(sqlQuery.customerQuerys.getCustomer);
		
		while(re.next())
		{
			Customer cust = new Customer();
			cust.setId(re.getLong("ID"));
			cust.setCustName(re.getString("CUST_NAME"));
			cust.setPassword(re.getString("PASSWORD"));
			allCust.add(cust);
		}
		if(allCust.size()>0)
		{
			System.out.println("the Customer List is found  in system successfully enjoy");
		}
		else
		{
	
			throw new CustomerException("the table empty no customer found  in system try again later ");
		}
		
		conPool.returnConnection(conn);
		return allCust;
	}
	/**
	 * find all coupon inside the system and  show  it  for client  to choose the coupon he  wnat  to purchase
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws CouponException 
	 * @throws InterruptedException 
	 * @throws CustomerException 
	 */
	
	@Override
	public Collection<Coupon>getAllCoupons() throws  SQLException, InterruptedException, CouponException, CustomerException, ClassNotFoundException
	{
		Collection<Coupon> coupons = new ArrayList<Coupon>();
		CouponDBDA allCoup = new CouponDBDA();
		coupons = allCoup.getAllCoupon();
		if(coupons.isEmpty())
		{
			throw  new CustomerException("there is  no coupons in system");
		}
		return coupons;
	}
	
	/**
	 * get all Customer Coupons  that he purchased
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public Collection<Coupon> getCoupons(Customer cust) throws InterruptedException, SQLException, CustomerException
	{
		conn = conPool.getConnection();
		ArrayList<Long>couponsId = new ArrayList<Long>();
		this.setUserCustId(this.getCustomerID(cust));
		Collection<Coupon> cust_coup =new ArrayList<Coupon>();
		
			
		PreparedStatement getcustCoupon = conn.prepareStatement(sqlQuery.customerQuerys.getCustomerCoupons);
		getcustCoupon.setLong(1,this.getUserCustId());	
		ResultSet res = getcustCoupon.executeQuery();
		
		while(res.next())
		{
			couponsId.add(res.getLong("COUP_ID"));
		}
		for (int i = 0; i < couponsId.size(); i++) 
		{
			PreparedStatement  coupons = conn.prepareStatement(sqlQuery.couponQuerys.getCouponById);
			coupons.setLong(1,couponsId.get(i));
			ResultSet fcop = coupons.executeQuery();
			while(fcop.next())
			{
					Coupon coup = new Coupon();
					long idc =fcop.getLong("ID");
					String ti = fcop.getString("title");
					Date sd = fcop.getDate("startDate");
					Date ed= fcop.getDate("endDate");
					int am = fcop.getInt("amount");
					String t= fcop.getString("type");
					String ms = fcop.getString("message");
					double pr = fcop.getDouble("price");
					String img =fcop.getString("image");
					coup.setId(idc);
					coup.setTitle(ti);
					coup.setStartDate(sd);
					coup.setEndDate(ed);
					coup.setAmount(am);
					
					coup.setCtyp(CouponType.findValue(t));
					coup.setMessage(ms);
					coup.setPrice(pr);
					coup.setImage("img");
					cust_coup.add(coup);
			}
		}
		if(couponsId.size()>0 && cust_coup.size()>0)
		{
			System.out.println("customer Coupons list found ");
		}
		else
		{
			throw new CustomerException(" no Coupons  found for  this  customer try agian");
		}
		
	conPool.returnConnection(conn);
	return cust_coup;
	}
	/**
	 * @list getAllCouponByType
	 * @param ctyp - coupon Type 
	 * @param cust - specific  customer 
	 * @return - list of Customer Coupons  
	 * to find customer purchased coupon by type 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public Collection<Coupon> getAllCouponsByType(CouponType ctyp,Customer cust) throws InterruptedException, SQLException, CustomerException
	{
		
		
		ArrayList<Long>couponsId = new ArrayList<Long>();
		this.setUserCustId(this.getCustomerID(cust));
		Collection<Coupon> cust_coup =new ArrayList<Coupon>();
		
		conn = conPool.getConnection();
		PreparedStatement getcustCoupon = conn.prepareStatement(sqlQuery.customerQuerys.getCustomerCoupons);
		getcustCoupon.setLong(1,this.getUserCustId());	
		ResultSet res = getcustCoupon.executeQuery();
		while(res.next())
			{
				couponsId.add(res.getLong("COUP_ID"));
			}
			for (int i = 0; i < couponsId.size(); i++) 
			{
				PreparedStatement  coupons = conn.prepareStatement(sqlQuery.customerQuerys.getCustCoupByType);
				coupons.setLong(1,couponsId.get(i));
				coupons.setString(2, ctyp.getValue());
				ResultSet fcop = coupons.executeQuery();
				while(fcop.next())
				{
						Coupon coup = new Coupon();
						long idc =fcop.getLong("ID");
						String ti = fcop.getString("title");
						Date sd = fcop.getDate("startDate");
						Date ed= fcop.getDate("endDate");
						int am = fcop.getInt("amount");
						String t= fcop.getString("type");
						String ms = fcop.getString("message");
						double pr = fcop.getDouble("price");
						String img =fcop.getString("image");
						coup.setId(idc);
						coup.setTitle(ti);
						coup.setStartDate(sd);
						coup.setEndDate(ed);
						coup.setAmount(am);
						
						coup.setCtyp(CouponType.findValue(t));
						coup.setMessage(ms);
						coup.setPrice(pr);
						coup.setImage("img");
						cust_coup.add(coup);
				}
			}
		if(cust_coup.size()>0 && couponsId.size()>0)
		{
			System.out.println(" list of  coupons for by the Type "+ctyp);
		}
		
		else
		{
			throw new CustomerException("no Coupons found for"+cust.getCustName() +"Type "+ctyp);
		}
		
		conPool.returnConnection(conn);
		return cust_coup;
	
	}
	/**
	 * getAllcustomercouponByPrice 
	 * @param cust -specific customer we want to find his coupon by specific price
	 * @param price - price of coupon we want to find 
	 * @return - list all coupons with specific customer and specific Price 
	 * find all customer purchased coupon By specific price
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public Collection<Coupon>getAllCustomerCouponByPrice(Customer cust , double price) throws InterruptedException, SQLException, CustomerException
	{
		
		ArrayList<Long>couponsId = new ArrayList<Long>();
		this.setUserCustId(this.getCustomerID(cust));
		Collection<Coupon> cust_coup =new ArrayList<Coupon>();
		
		conn = conPool.getConnection();
		PreparedStatement getcustCoupon = conn.prepareStatement(sqlQuery.customerQuerys.getCustomerCoupons);
		getcustCoupon.setLong(1,this.getCustomerID(cust));	
		ResultSet res = getcustCoupon.executeQuery();
		
		while(res.next())
		{
			couponsId.add(res.getLong("COUP_ID"));
		}
		for (int i = 0; i < couponsId.size(); i++) 
		{
			PreparedStatement  coupons = conn.prepareStatement(sqlQuery.customerQuerys.getCustCouponByPrice);
			coupons.setLong(1,couponsId.get(i));
			coupons.setDouble(2,price);
			ResultSet fcop = coupons.executeQuery();
			while(fcop.next())
			{
					Coupon coup = new Coupon();
					long idc =fcop.getLong("ID");
					String ti = fcop.getString("title");
					Date sd = fcop.getDate("startDate");
					Date ed= fcop.getDate("endDate");
					int am = fcop.getInt("amount");
					String t= fcop.getString("type");
					String ms = fcop.getString("message");
					double pr = fcop.getDouble("price");
					String img =fcop.getString("image");
					coup.setId(idc);
					coup.setTitle(ti);
					coup.setStartDate(sd);
					coup.setEndDate(ed);
					coup.setAmount(am);
					
					coup.setCtyp(CouponType.findValue(t));
					coup.setMessage(ms);
					coup.setPrice(pr);
					coup.setImage("img");
					cust_coup.add(coup);
			}
				
		
		}
		if(cust_coup.size()>0 && couponsId.size()>0)
		{
			System.out.println("the customer puchased coupon list is found for price :"+price);
		}
		else{
			throw new CustomerException("Cannot found Customer Coupon purchased at price :"+price);
		}
		
		conPool.returnConnection(conn);
		return cust_coup;
		
	}
	/**
	 * login customer with user name  and  password as boolean
	 * @param cust
	 * @param password
	 * @return boolean
	 * login method  for customer to access the system with 
	 * check  user name and password  validation in database
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public boolean login(String cust, String password) throws InterruptedException, SQLException, CustomerException {
		
		String userName = "";
		String pass = "";
		boolean isfound = false;
		
		conn = conPool.getConnection();
		PreparedStatement st = conn.prepareStatement(sqlQuery.customerQuerys.login);
		ResultSet res = st.executeQuery();
		
		while(res.next())
		{
			System.out.println("inside while customer");
			userName =res.getString(1);
			System.out.println(userName);
			pass = res.getString(2);
			System.out.println(pass);
			if(userName.equals(cust.trim()) && pass.equals(password.trim()) )
			{
				isfound = true;
				System.out.println("the user is founded" + cust +"login successfully Enjoy");
			}
			
		}
	/*	if(isfound == true)
		{
			System.out.println("login successfully ");
		}
		else
		{
			
			//throw new CustomerException(" login Fail Try again later");
			
			throw new CustomerException(" the user name or password you enter is wrong try again");
		}*/

		
		return isfound;
	}
	/**
	 * 
	 * @param cust
	 * @return
	 * find customer @id
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 */
	@Override
	public long getCustomerID(Customer cust) throws InterruptedException, SQLException, CustomerException
	{
	
		long customerId = 0 ;
		
		conn = conPool.getConnection();
		PreparedStatement custid = conn.prepareStatement(sqlQuery.customerQuerys.customerId);
		custid.setString(1,cust.getCustName());
		ResultSet res = custid.executeQuery();
		
		while(res.next())
		{
			customerId = res.getLong(1);
			System.out.println("the  customer  id  by his  name :"+customerId);
			
		}
		
		if(customerId == 0){
			throw new CustomerException("Cannot foud  Customer id or  the customer dosent exist");
		}
			
			
		
		conPool.returnConnection(conn);
	
		return customerId;
	}
	/**
	 * 
	 * @param cust - specific Customer that want to add Coupon or to purchase account
	 * @param coup -the coupon that customer purchased 
	 * when customer purchased coupon well add customer id  and  coupon id  to customerCoupon table in database
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws CustomerException 
	 * @throws CouponException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public void addCouponCustomer(Customer cust , Coupon coup) throws InterruptedException, SQLException, CustomerException, CouponException, ClassNotFoundException
	{
			CouponDBDA update = new CouponDBDA();
			conn= conPool.getConnection();
			PreparedStatement addCCoup = conn.prepareStatement(sqlQuery.customerQuerys.addcouponCustomer);
			addCCoup.executeUpdate();
			PreparedStatement insertCoup = conn.prepareStatement(sqlQuery.customerQuerys.customerCoupon);
			insertCoup.setLong(1,this.getCustomerID(cust));
			//System.out.println("test"+this.getUserCustomerId);
			insertCoup.setLong(2,update.CouponId(coup));
			int insrtcoup = insertCoup.executeUpdate();
	
			PreparedStatement changeAmount = conn.prepareStatement(sqlQuery.customerQuerys.CouponAmount);
			changeAmount.setLong(1,coup.getId());
			ResultSet res = changeAmount.executeQuery();
			int amount =0;
			
			while(res.next())
			{
				amount = res.getInt("amount");
			}
			//CouponDBDA update = new CouponDBDA();
			int nAmount = coup.getAmount()-1;
			coup.setAmount(nAmount);
			update.updatecoupon(coup);
			System.out.println("the Customer"+cust.getCustName() +"purachased coupon"+coup.getTitle() + "successfully and  added to customer list");
			
			if(insrtcoup ==0)
			{
				throw new CustomerException("Cannot add  coupon  "+coup.getTitle() + "try agian later");
			}
				
		
			conPool.returnConnection(conn);
	}
	/**
	 * getter and setter for customer id member
	 * @return recent Customer Id
	 * @param recent Customer Id
	 */
	public long getUserCustId()
	{
		return getUserCustomerId;
	}
	public void setUserCustId(long custUsrId)
	{
		this.getUserCustomerId = custUsrId;
	}
}
