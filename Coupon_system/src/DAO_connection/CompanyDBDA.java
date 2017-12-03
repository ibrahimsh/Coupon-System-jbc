

package DAO_connection;
import system_connection.connectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.sys.exception.companyException;

import java_beans.Company;
import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;
import system_connection.connectionPool;
/**
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * <br>
 * this connect to database via @connectionPool class to connect to database
 * @CompanyDBDA the class contain method  to create company table if  not exist and  insert company data into database
 * @update the update company information
 * @remove remove company form data base and  remove the coupons that belong  to company form company coupons 
 * @companyId find company id  by company name  where  the  name is  unique  cannot repeated 
 * @findCompanyByid search for  company  by id  and  show  company information
 * @getAllcompanies find all company in database
 * @getcompaniesCoupons find  all coupons that belong to specific company
 * @login the company can access the system by using  user  name and  password
 * the class @companyDBDA implement from interface @companyDAO
 */
public class CompanyDBDA implements CompanyDAO{
	/**
	 * connection initialize to getConnection
	 * and  getter /setter for company id 
	 */
	private connectionPool conPool;
	private Connection conn =null;
	public final long id = 0;
	/**
	 * constructor for @companyDBDA with connection pool 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CompanyDBDA() throws ClassNotFoundException, SQLException
	{
		
			conPool = connectionPool.getInstance();
	
		
	}

	/**
	 * and company to database if the company table not exist will create table automatic and insert new company information
	 * @throws InterruptedException 
	 */
	@Override
	public long createCompany(Company comp) throws companyException, SQLException, InterruptedException {
		
			
			 conn = conPool.getConnection();
			 PreparedStatement statment = conn.prepareStatement(sqlQuery.companyQuerys.createCompany);
			 statment.execute();
			 PreparedStatement addconstrain = conn.prepareStatement(sqlQuery.companyQuerys.addConstrain);
			 addconstrain.executeUpdate();
			 PreparedStatement stat1 = conn.prepareStatement(sqlQuery.companyQuerys.insertIntoCompany,Statement.RETURN_GENERATED_KEYS);
			 stat1.setString(1,comp.getCompName());
			 stat1.setString(2,comp.getPassword());
			 stat1.setString(3,comp.getEmail());
			 int row =stat1.executeUpdate();
			 
			 if(row == 0)
			 {
				throw new companyException("creating Company is  failed");
					}
			
			  try (ResultSet generatedKeys = stat1.getGeneratedKeys())
			  {
				  if(generatedKeys.next())
				  {
					 
					  comp.setId(generatedKeys.getLong(1));
				  }
				  else{
					  throw new companyException("generated new id  is  failed");
				  }
			  }

		finally {
			if(conn !=null)
			{
				conPool.returnConnection(conn);
			}
		}
		
		
		return comp.getId();
	}

	/**
	 * @throws InterruptedException 
	 * @throws companyException 
	 * @removeCompany
	 * removing company from database Company table and  remove the all company coupons from company_coupons table in database
	 */
	@Override
	public void removeCompany(Company comp) throws SQLException, InterruptedException, companyException {
		
		Connection conn = null ;
		conn = conPool.getConnection();
		PreparedStatement removeCoupon = conn.prepareStatement(sqlQuery.companyQuerys.removeCouponcompany);
		removeCoupon.setLong(1,this.getID(comp.getCompName()));
		removeCoupon.executeUpdate();
		//PreparedStatement remcouponComp = conn.prepareStatement(sqlQuery.companyQuerys.removeCouponcompany);
		//long id  = this.getID(comp.getCompName());
		//remcouponComp.setLong(1,id);///this.getID(comp.getCompName()));
		//remcouponComp.executeUpdate();
		PreparedStatement statm = conn.prepareStatement(sqlQuery.companyQuerys.removeCompanyByName);
		statm.setString(1,comp.getCompName());
		//statm.executeUpdate();
		if(statm.executeUpdate() >0)
		{
			System.out.println(this.getID(comp.getCompName()));
			System.out.println("the Company is "+comp.getCompName() + " removed successfully ");
		}
		else{
			throw new companyException("Cannot  remove  company or the company dsent exist in system");
		}
		
		
	conPool.returnConnection(conn);
		
		
	}

	/**
	 * update company information except the id  while the id  is  auto increment  in database
	 * @throws SQLException 
	 * @throws companyException 
	 */
	@Override
	public void updateCompany(Company comp) throws ClassNotFoundException, InterruptedException, SQLException, companyException
	{
			conn = conPool.getConnection();
			//System.out.println("connection:"+conn);
			String updat ="update Company SET comp_name=?,password=?,email=? WHERE ID=?";
			long id  = this.getID(comp.getCompName());
			System.out.println("the id" + id);
			PreparedStatement upstat = conn.prepareStatement(sqlQuery.companyQuerys.updateCompany);
			
			upstat.setString(1, comp.getCompName());
			//System.out.println("comName:"+comp.getCompName());
			upstat.setString(2, comp.getPassword());
			upstat.setString(3, comp.getEmail());
			upstat.setLong(4,comp.getId());
			
			System.out.println("id"+comp.getId());
			int up =upstat.executeUpdate();
			if(up ==1)
				{
					System.out.println("update status successfull :"+upstat.executeUpdate());
				}
			else{
				
				throw new companyException("cannot update this  company the company dosent exist");
			}
	
			conPool.returnConnection(conn);
		
	}
	/**
	 * search for company information by using  company ID
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws companyException 
	 */
	@Override
	public Company getCompany(int id) throws InterruptedException, SQLException, companyException {
		
		//String query = "SELECT * FROM company WHERE ID=" +id;
		
		Company comp = null;
		
		conn = conPool.getConnection();
		//Statement st  =  conn.createStatement();
		PreparedStatement st = conn.prepareStatement(sqlQuery.companyQuerys.searchCompanyByID);
		st.setLong(1,id);
		ResultSet get = st.executeQuery();
		
		System.out.println("RESGET"+" "+get);
		if(get !=null)
		{
			
		
			while(get.next())
			{
				comp = new Company();
				long gid = get.getLong("id");
				comp.setId(gid);
				String cname =get.getString("comp_name");
				comp.setCompName(cname);
				System.out.println("name"+""+comp.getCompName());
				String pass = get.getString("password");
				comp.setPassword(pass);
				String email = get.getString("email");
				comp.setEmail(email);
				
				//comp.setCoupons(null);
				
			}
			System.out.println("the Comapny Id"+id +"found  successfully");
		}
		else
		{
			throw new companyException("Cannot  found Comapny in id  "+id+" try with other id ");
		}
		
		
		conPool.returnConnection(conn);
		return comp;
	}
	/**
	 * search for  all company available  in database
	 * @throws companyException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws companyException, InterruptedException, SQLException {
		// TODO Auto-generated method stub
		Collection<Company> company = new ArrayList<Company>();
		Company comp =null;
		
		conn = conPool.getConnection();
		Statement st = conn.createStatement();
		ResultSet res = st.executeQuery(sqlQuery.companyQuerys.getCompany);
		if(res != null)
		{
			while(res.next())
			{
				comp = new Company();
				long gid = res.getLong("id");
				comp.setId(gid);
				String cname =res.getString("comp_name");
				comp.setCompName(cname);
				System.out.println("name"+""+comp.getCompName());
				String pass = res.getString("password");
				comp.setPassword(pass);
				String email = res.getString("email");
				comp.setEmail(email);
				
				comp.setCoupons(null);
				company.add(comp);
				
				
		
			System.out.println("the search for  all company insystem succssfully");}
		}
		else{
			throw new companyException("no companys found  in system ");
		}
			
		conPool.returnConnection(conn);
		return company ;
	}
/**
 * find  all coupons for  specific  company 
 * @throws InterruptedException 
 * @throws SQLException 
 * @throws companyException 
 */
	@Override
	public Collection<Coupon> getCoupons(Company comp) throws InterruptedException, SQLException, companyException
	{
		
		
		//Customer cust = new Customer();
		ArrayList<Long>couponsId = new ArrayList<Long>();
		
		ArrayList<Coupon> comp_coup =new ArrayList<Coupon>();
		
		conn = conPool.getConnection();
		PreparedStatement getcustCoupon = conn.prepareStatement(sqlQuery.companyQuerys.getCompanyCoupons);
		getcustCoupon.setLong(1,this.getID(comp.getCompName()));	
		ResultSet res = getcustCoupon.executeQuery();
		if(res !=null)
		{
			while(res.next())
			{
				couponsId.add(res.getLong("COUP_ID"));
			}
			System.out.println(couponsId  + "size"+couponsId.size());
			for (int i = 0; i < couponsId.size(); i++) 
			{
				PreparedStatement  coupons = conn.prepareStatement(sqlQuery.couponQuerys.getCouponById);
				coupons.setLong(1,couponsId.get(i));
				System.out.println(couponsId.get(i));
				ResultSet fcop = coupons.executeQuery();
				if(fcop !=null)
				{
					while(fcop.next())
					{
							Coupon coup = new Coupon();
							long idc =fcop.getLong("ID");
							System.out.println("couponId="+idc);
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
							comp_coup.add(coup);
					}
					System.out.println("the Coupon found successfully");
				}
				else{
					throw new companyException("error while tring  to add coupon");				}
				
			}
			System.out.println("add coupon to company"+comp.getCompName()+ "Succesfully");
		}
		else{
			throw new companyException("cannot add Coupon to Company");
		}
	

		
		conPool.returnConnection(conn);
		return comp_coup;
	}

	/**
	 * @throws companyException 
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @login
	 * @UserName
	 * @password
	 * the company can get inside the system by using  login with user name  and  password
	 */
	@Override
	public boolean login(String compName, String password) throws companyException, InterruptedException, SQLException {
		boolean exist = false;
		String usr = "" ;
		String pass = "";
		//String login = "SELECT comp_name,password From company";
		
		
		conn = conPool.getConnection();
		//System.out.println(conn);
		//PreparedStatement loginSt = con.prepareStatement(login);
		Statement log = conn.createStatement();
		ResultSet res = log.executeQuery(sqlQuery.companyQuerys.login);
		if(res !=null)
		{
			while(res.next())
			{
				usr= res.getString(1).trim();
				pass = res.getString(2).trim();
				if(compName.equals(usr.trim()) && password.equals(pass.trim()))
				{
					exist = true;
					System.out.println("the user name and  password found in system");
				}
				else{
					exist =false;
					System.out.println("the User name or password Wromg  try again");
					throw new companyException("one of  entries or both is wrong try contact to administrator");
				}
			}
			System.out.println("the Login successfully as Company");
		}
		else
		{
			throw new companyException("Comapny login Fail ");
		}

		conPool.returnConnection(conn);
		return exist;
	}
	/**
	 * find company id  by name while  the  name of  company is  unique  cannot add into system two companies with the same name
	 * @throws InterruptedException 
	 * @throws companyException 
	 */
	@Override
	public long getID(String name) throws SQLException, InterruptedException, companyException
	{
		
		int id = 0 ;
		
		conn = conPool.getConnection();
		
		String sql = "SELECT ID From Company WHERE comp_name LIKE '%" +name +"%'";
		
		PreparedStatement st = conn.prepareStatement(sql);//sqlQuery.companyQuerys.findCompanyID);
		//st.setString(1,name);
		ResultSet res = st.executeQuery();
		
		//if(res!=null)
		//{
		while(res.next())
		{
			id = res.getInt(1);
			System.out.println("the  id  for this  company is  :"+" "+id);
		}
		//System.out.println("the Id found  for Company"+name);
		//}
		//else{
		//	throw new companyException("Cannot found the id  for the Company in name "+name);
		//}
		conPool.returnConnection(conn);
		return id;
			
		
	}
	/**
	 * add coupon into company inside company_coupon table with company id  and  coupon id
	 * @throws InterruptedException 
	 * @throws SQLException 
	 * @throws companyException 
	 */
	@Override
	public  void  addCouponToCompany(Coupon c , Company com) throws InterruptedException, SQLException, companyException
	{
	   
	        conn = conPool.getConnection();
	       // String query = "INSERT INTO Customer_Coupon (CUST_ID, COUPON_ID) VALUES (?, ?)";
	        PreparedStatement pstmt = conn.prepareStatement(sqlQuery.companyQuerys.createCompCouponT);
	        if(pstmt.executeUpdate()>0)
	        {
	        	System.out.println("the company coupon table created");
	        }
	        else{
	        	throw new companyException("the company Coupon table exist in system");
	        }
	        PreparedStatement insertCC = conn.prepareStatement(sqlQuery.companyQuerys.compCoupon);
	        com.setId(this.getID(com.getCompName()));
	        insertCC.setLong(1, com.getId());
	        //c.setId(id);
	        insertCC.setLong(2, c.getId());
	        if(insertCC.executeUpdate()>0)
	        {
	        	System.out.println("the company id "+com.getId() +","+ "coupon id  "+c.getId() + "added successfully");
	        }
	        else{
	        	throw new companyException("Cannot  add coupon company table");
	        }
	        conPool.returnConnection(conn);
	}

}
