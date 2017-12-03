package Tester;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sys.exception.CouponSystemException;
import com.sys.exception.CustomerException;
import com.sys.exception.companyException;

import Coupon.System.Facade.AdminFacade;
import Coupon.System.Facade.CompanyFacade;
import Coupon.System.Facade.CustomerFacade;
import Coupon.System.Facade.clientType;
import CouponSystem.CouponSystem;
import DAO_connection.CustomerDBDA;
import java_beans.Company;
import java_beans.Coupon;
import java_beans.CouponType;
import java_beans.Customer;

public class MainTest {

	public static void main(String[] args) throws ParseException, ClassNotFoundException, SQLException, InterruptedException, CouponSystemException, IllegalArgumentException, CustomerException, companyException
	{

		AdminFacade adminfacade = new AdminFacade();
		CompanyFacade companyfacade =new CompanyFacade();
		CustomerFacade customerfacade =new CustomerFacade();;
		//Customer cust = new Customer("ibrahim","1234",null);
		//CustomerDBDA cdbd = new CustomerDBDA();
		//cdbd.createCustomer(cust);
		//System.out.println(CouponSystem.getInstance().login("admin", "1234", clientType.ADMIN));
		if(CouponSystem.getInstance().login("admin", "1234", clientType.ADMIN))
		{
			Company comp1 = new Company("bezeq","123","www.",null);
			Company comp2 = new Company("intel","456","www.",null);
			Company comp3 = new Company("karnaf","789","www.",null);
			Company comp4 = new Company("teva","741","www.",null);
			Customer cust = new Customer("enge","1234",null);
			
			//adminfacade.createCompany(comp1);
			//adminfacade.createCompany(comp2);
			//adminfacade.createCompany(comp3);
			//adminfacade.createCompany(comp4);
			//adminfacade.createCustomer(cust);
			//adminfacade.getAllcoupon();
			adminfacade.removeCompany(comp4);
			comp1.setCompName("primo099");
			comp1.setPassword("alqudsnet");
			comp1.setId(8);
			adminfacade.updateCompany(comp1);
			for(Company c :adminfacade.getAllCompanies())
			{
				System.out.println(c);
			}
			for(Customer cu : adminfacade.getAllcustomer())
			{
				System.out.println(cu);
			}
			Company c1 =adminfacade.getCompany(7);
			System.out.println("the company"+c1);
		}

		// test 2
		/*Customer cust1 = new Customer(0,"jhon","963");
		Customer cust2 = new Customer(0,"nick","852");
		Customer cust3 = new Customer(0,"shelly","753");
		Customer cust4 = new Customer(0,"maya","951");
		adminfacade.createCustomer(cust1);
		adminfacade.createCustomer(cust2);
		adminfacade.createCustomer(cust3);
		adminfacade.createCustomer(cust4);*/

		//test 3
		/*System.out.println(adminfacade.getAllCompanies());
		Company comp1 = new Company(1,"bezeq","321","www.aaa");
		adminfacade.updateCompany(comp1);
		System.out.println(adminfacade.getCompany(1));*/

		//test4
		/*System.out.println(adminfacade.getAllCustomer());
		Customer cust1 = new Customer(1,"jhon","369");
		adminfacade.updateCustomer(cust1);
		System.out.println(adminfacade.getCustomer(1));*/

		//test5
		System.out.println("test5");
		System.out.println(CouponSystem.getInstance().login("primo099", "alqudsnet", clientType.COMPANY));
		if(CouponSystem.getInstance().login("matrix", "newPass", clientType.COMPANY))
		{
			companyfacade= new CompanyFacade();
			System.out.println("in comapny");
			//companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "321", ClientType.COMPANY);
			// Coupon(String title, Date startDate, Date endDate, int amount, CouponType ctyp, String message,
			//double price, String image)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

			Calendar calendar = new GregorianCalendar(2017,1,17);
			String d2 =calendar.toString();
			final java.sql.Date sqlDate2=  java.sql.Date.valueOf(d2);
			Calendar calendar2 = new GregorianCalendar(2018,1,17);
			String d1 =calendar.toString();
			final java.sql.Date sqlDate1=  java.sql.Date.valueOf(d1);
			Coupon coup1 = new Coupon("aaa",sqlDate1, sqlDate2, 5,CouponType.CAMPING, "hhh", 25.7,"ttt");
			//Coupon coup2 = new Coupon("bbb", "16-12-2017", "17-02-2018", 5,CouponType.FOOD, "hhh", 30.6,"lll");
			//Coupon coup3 = new Coupon("mmm", "17-01-2017", "17-03-2018", 5,CouponType.CAMPING, "hhh", 30.6,"nnn");
			companyfacade.createCoupon(coup1);
			//companyfacade.createCoupon(coup2);
			//companyfacade.createCoupon(coup3);
			//System.out.println(companyfacade.getCouponByType(CouponType.food));
			//System.out.println(companyfacade.getCouponByPrice(40));
			/*java.util.Date test = DateTranslate.stringToDate("17-02-25");
			System.out.println(companyfacade.getCouponByDate(test));*/
		}

		//test6
		/*System.out.println(companyfacade.getAllCoupon());
		System.out.println(companyfacade.getCoupon(3));
		Coupon coup1 = new Coupon(3, "aaa", "17-01-01", "17-03-30", 5,CouponType.camping, "hhh", 25.8,"ttt");
		companyfacade.updateCoupon(coup1);
		System.out.println(companyfacade.getCoupon(3));*/

		//test7
		/*companyfacade = (CompanyFacade) CouponSystem.getInstance().login("intel", "456", ClientType.COMPANY);
		Coupon coup4 = new Coupon(0, "ccc", "17-01-05", "17-03-13", 5,CouponType.camping, "hhh", 25.7,"ttt");
		Coupon coup5 = new Coupon(0, "ddd", "16-11-20", "17-02-20", 5,CouponType.camping, "hhh", 25.7,"ttt");
		companyfacade.createCoupon(coup4);
		companyfacade.createCoupon(coup5);
		System.out.println(companyfacade.getAllCoupon());*/

		//test8
		//customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","963",ClientType.CUSTOMER);
		/*customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","369",ClientType.CUSTOMER);
		System.out.println(customerfacade.getAllCoupons());
		Coupon coupon = customerfacade.getCoupon(3);
		customerfacade.purchaseCoupon(coupon);*/
		//System.out.println(customerfacade.getAllPurchasedCoupons());
		//System.out.println(customerfacade.getAllPurchasedCouponsByType(CouponType.food));
		//System.out.println(customerfacade.getAllPurchasedCouponsByPrice(35));

		//test 9
		/*companyfacade = (CompanyFacade) CouponSystem.getInstance().login("bezeq", "321", ClientType.COMPANY);
		Coupon coup = companyfacade.getCoupon(3);
		companyfacade.removeCoupon(coup);*/

		//test 10
		/*customerfacade = (CustomerFacade) CouponSystem.getInstance().login("jhon","369",ClientType.CUSTOMER);
		System.out.println(customerfacade.getAllCoupons());
		customerfacade.purchaseCoupon(customerfacade.getCoupon(2));
		customerfacade.purchaseCoupon(customerfacade.getCoupon(5));*/
		//adminfacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMIN);
		//System.out.println(adminfacade.getAllCompanies());
		//System.out.println(adminfacade.getAllCustomer());
		//adminfacade.removeCompany(adminfacade.getCompany(2));
		//adminfacade.removeCustomer(adminfacade.getCustomer(2));
	}

	}
