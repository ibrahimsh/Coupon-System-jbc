package java_beans;

import java.io.Serializable;
import java.util.Collection;

	  

/**
 * @author MatrixComp - ibrahim shweiki
 * @version 1.0 v
 * @see
 * this class create company that contain members and constructor , setter and getter for members and other method 
 * 
 * JavaBeans are Java classes which adhere to an extremely simple coding convention. All you have to do is to
 * Implement java.io.Serializable interface - To save the state of an object
 * use a public empty argument constructor - To instantiate the object
 * And provide public getter and setter methods - To get and set the values of private variables (properties )
 */
	
public class Company  implements Serializable {
	
	/**
	 * initializing the class Company member
	 * @Id - identification number for company that the system store the company information
	 * @compName - the variable that contain name of  company
	 * @password - company password to access the system
	 * @email  - E-mail for  company as contact
	 * @listOfCoupon - list of  coupons  that company create in system 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String compName;
	private String password;
	private String Email;
	private  Collection<Coupon> coupons;
	/**
	 * Constructor 
	 * empty Constructor
	 */
	public Company(){}
	/**
	 * Constructor 
	 * @param compName - to initialize company name 
	 * @param password - initialize company password 
	 * @param Email -  to initialize Company Email  
	 * @param coupons - initialize Company coupon list 
	 */
	public Company(String compName, String password,String Email, Collection<Coupon> coupons) {
		super();
		this.id=0 ;
		this.compName = compName;
		this.password = password;
		this.Email = Email;
		this.coupons = coupons;
	}
	/**
	 * getEmail value
	 * @return - company email 
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * Company Email setter
	 * @param email - set company email value
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * get company id inside the system 
	 * @return - Company id
	 */
	public long getId() {
		return id;
	}
	/**
	 * set company id  with value
	 * @param id - id  as parameter to set 
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * get Company name 
	 * @return - company name 
	 */
	public String getCompName() {
		return compName;
	}
	/**
	 * set Company value if you intend to change  company name 
	 * @param compName - get the name we want to change  as  parameter
	 */
	public void setCompName(String compName) {
		this.compName = compName;
	}
	/**
	 * get  Company password 
	 * @return - return company password 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set new value  for company password
	 * @param password - as parameter new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * show company coupon list 
	 * @return - list of  coupons that company owne
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * set company coupon list  
	 * @param coupons -  passing the coupon list that company add to account 
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * toString method  that print company information in formula we want to show  to user 
	 * @return - string formula include company information 
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password +",Email="+ Email + ", coupons=" + coupons + "]";
	}
	
	
	

}
