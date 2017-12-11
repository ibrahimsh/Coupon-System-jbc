package Tester;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sys.exception.CouponException;
import com.sys.exception.CouponSystemException;
import com.sys.exception.CustomerException;
import com.sys.exception.companyException;

import Coupon.System.Facade.AdminFacade;
import Coupon.System.Facade.CompanyFacade;
import Coupon.System.Facade.CustomerFacade;
import Coupon.System.Facade.clientType;
import CouponSystem.CouponSystem;
import DAO_connection.CompanyDBDA;
import DAO_connection.CouponDBDA;
import DAO_connection.CustomerDBDA;
import ExpiredCouponsThread.DailyCouponExpiredTask;
import java_beans.Company;
import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;

public class MainTest {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException, InterruptedException, CouponSystemException, IllegalArgumentException, CustomerException, companyException, CouponException
	{

		AdminFacade adminfacade = new AdminFacade();
		CompanyFacade companyfacade =new CompanyFacade();
		CustomerFacade customerfacade = new CustomerFacade() ;
		/**
		 * Initialize company's
		 */
		Company comp1 = new Company("bezeq","123","www.",null);
		Company comp2 = new Company("intel","456","www.",null);
		Company comp3 = new Company("karnaf","789","www.",null);
		Company comp4 = new Company("teva","741","www.",null);
		Company comp5 = new Company("Matrix","8888","mat@matrix",null);
		/**
		 * Initialize customers
		 */
		Customer cust = new Customer("enge","1234",null);
		Customer cust1 = new Customer("johne bryce","12547",null);
		Customer cust2 = new Customer("computerSoftware","8784",null);
		Customer cust3 = new Customer("ald","3345",null);
		/**
		 * initialize Coupons
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		final java.sql.Date sqlDate1=  java.sql.Date.valueOf("2017-02-22");
		final java.sql.Date sqlDate2=  java.sql.Date.valueOf("2018-02-22");
		Coupon coup1 = new Coupon("superMaket",sqlDate1, sqlDate2, 5,CouponType.FOOD, "hhh", 25.7,"ttt");
		final java.sql.Date sqlDate3=  java.sql.Date.valueOf("2017-02-22");
		final java.sql.Date sqlDate4=  java.sql.Date.valueOf("2017-09-22");
		Coupon coup2 = new Coupon("hamashber",sqlDate3,sqlDate4, 5,CouponType.FOOD, "hhh", 30.6,"lll");
		final java.sql.Date sqlDate5=  java.sql.Date.valueOf("2017-06-05");
		final java.sql.Date sqlDate6=  java.sql.Date.valueOf("2018-01-22");
		Coupon coup3 = new Coupon("youth camping",sqlDate5,sqlDate6, 5,CouponType.CAMPING, "hhh", 30.6,"nnn");
		
	
		adminfacade = (AdminFacade)CouponSystem.getInstance().login("admin", "1234", clientType.ADMIN);
		if( adminfacade!=null)
		{
			
			
			//adminfacade.createCustomer(cust2);
			//adminfacade.createCustomer(cust1);
			//adminfacade.createCustomer(cust);
			//adminfacade.createCustomer(cust3);
			
			//adminfacade.createCompany(comp4);
			//adminfacade.createCompany(comp3);
			//adminfacade.createCompany(comp1);
			//adminfacade.createCompany(comp2);
			//adminfacade.createCompany(comp5);
			
			
			/*for (Customer c33 :adminfacade.getAllcustomer())
			{
				System.out.println(c33);
			}*/
			//System.out.println(adminfacade.getcustomer(11));
			//System.out.println(adminfacade.getCompany(10));
			//System.out.println("remove  customer");
			//adminfacade.removeCustomer(cust2);
			//adminfacade.removeCompany(comp2);
			//adminfacade.createCompany(comp1);
			//adminfacade.createCompany(comp2);
			//adminfacade.createCompany(comp3);
			//adminfacade.createCompany(comp4);
			//adminfacade.createCustomer(cust);
			//adminfacade.getAllcoupon();
			//adminfacade.removeCompany(comp4);
			//comp1.setCompName("primo099");
			//comp1.setPassword("alqudsnet");
			//comp1.setId(8);
			//adminfacade.updateCompany(comp1);
			//for(Company c :adminfacade.getAllCompanies())
			//{
			//	System.out.println(c);
			//}
			//for(Customer cu : adminfacade.getAllcustomer())
			//{
			//	System.out.println(cu);
			//}
			//Company c1 =adminfacade.getCompany(7);
			//System.out.println("the company"+c1);
		}

		
		//test5
		System.out.println("test5");
		//companyfacade= new CompanyFacade();
		companyfacade = (CompanyFacade)CouponSystem.getInstance().login("teva", "741", clientType.COMPANY);
		//System.out.println(CouponSystem.getInstance().login("primo099", "alqudsnet", clientType.COMPANY));
		if(companyfacade.login("teva", "741", clientType.COMPANY)!=null)
		{
			//CompanyDBDA cdbda = new CompanyDBDA();
			//Company compa = new Company();
			//compa.setCompName("matrix");
			//System.out.println("print company name : "+ compa.getCompName());
			//compa.setId(cdbda.getID(compa.getCompName()));
			System.out.println("in Company");
			companyfacade.createCoupon(coup1, comp5);
			//companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "321", ClientType.COMPANY);
			// Coupon(String title, Date startDate, Date endDate, int amount, CouponType ctyp, String message,
			//double price, String image)
			
			//companyfacade.createCoupon(coup1,compa);
			//companyfacade.createCoupon(coup2,compa);
			//companyfacade.createCoupon(coup3,compa);
			/*for (Coupon c44 :companyfacade.getAllcoupons(compa))
			{
				System.out.println("company coupons :"+ c44);
			}*/
			//System.out.println(companyfacade.getCouponByType(CouponType.FOOD,compa));
			//System.out.println(companyfacade.getCoupon(3));
			/*
			//coup3.setId(3);
			//coup3.setAmount(2);
			//coup3.setMessage("this  coupon just for students bring it to office ");
			//coup3.setPrice(22.5);
			//System.out.println("update coupon information");
		//	companyfacade.updateCoupon(coup3);*/
		}
		//customerfacade =(CustomerFacade)CouponSystem.instance.login("mazen", "google",clientType.CUSTOMER);
		/*
		Customer cust1 = new Customer("johne bryce","12547",null);
		Customer cust2 = new Customer("computerSoftware","8784",null);
		CustomerDBDA custdbda = new CustomerDBDA();
		System.out.println("customer id ::"+custdbda.getCustomerID(cust1));
		System.out.println("login customer is :::"+ custdbda.login("computerSoftware","8784"));
		if(customerfacade.login("johne bryce","12547",clientType.CUSTOMER) !=null)
		{
			System.out.println("customer connected !!");
			final java.sql.Date sqlDate1=  java.sql.Date.valueOf("2017-02-22");
			final java.sql.Date sqlDate2=  java.sql.Date.valueOf("2018-02-22");
			Coupon coup1 = new Coupon("superMaket",sqlDate1, sqlDate2, 5,CouponType.FOOD, "hhh", 25.7,"ttt");
			final java.sql.Date sqlDate3=  java.sql.Date.valueOf("2017-02-22");
			final java.sql.Date sqlDate4=  java.sql.Date.valueOf("2017-09-22");
			Coupon coup2 = new Coupon("hamashber",sqlDate3,sqlDate4, 5,CouponType.FOOD, "hhh", 30.6,"lll");
			final java.sql.Date sqlDate5=  java.sql.Date.valueOf("2017-06-05");
			final java.sql.Date sqlDate6=  java.sql.Date.valueOf("2018-01-22");
			Coupon coup3 = new Coupon("youth camping",sqlDate5,sqlDate6, 5,CouponType.CAMPING, "hhh", 30.6,"nnn");
			System.out.println("customer facade");
			
			for(Coupon c: customerfacade.findCoupons())
			{
				System.out.println(c);
			}
			customerfacade.purchaseCoupon(coup1, cust1);
			//System.out.println(customerfacade.getAllPurchasedCoupons(cust1));
		}*/
		DailyCouponExpiredTask dcet = new DailyCouponExpiredTask();
		//System.out.println("run thread" );
		//dcet.coupons();
		Thread daythread = new Thread(dcet);
		daythread.run();
		dcet.stop();
		
		
	}

	}
