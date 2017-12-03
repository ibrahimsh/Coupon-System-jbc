package java_beans;

import java.io.Serializable;
import java.util.Collection;
/**
 * @author MatrixComp - ibrahim shweiki
 * @version 1.0v
 * <br>
 * Customer Class include all Customer information need as member
 * @see
 * JavaBeans are Java classes which adhere to an extremely simple coding convention. All you have to do is to
 * Implement java.io.Serializable interface - To save the state of an object
 * use a public empty argument constructor - To instantiate the object
 * And provide public getter and setter methods - To get and set the values of private variables (properties ).
 */
public class Customer implements Serializable {
	
	/**
	 * Customer information members @id
	 * @customerName
	 * @customerPassowrd
	 * @CustomerCoupon - list
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String custName;
	private String password;
	private Collection<Coupon> coupons;
	/**
	 * Constructor - empty
	 * 
	 */
	public Customer(){}
	/**
	 * Constructor  -initialize Customer class member
	 * @param custName
	 * @param password
	 * @param coupons
	 */
	public Customer(String custName, String password, Collection<Coupon> coupons) {
		super();
		this.id = 0;
		this.custName = custName;
		this.password = password;
		this.coupons = coupons;
	}
	/**
	 * getter id
	 * @return -id (Customer id )
	 */
	public long getId() {
		return id;
	}
	/**
	 * setter for Customer id 
	 * @param id -customer  id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * getter  customer  name 
	 * @return - customer  name 
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * setter Customer  name
	 * @param custName - customer  name 
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * getter customer password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * setter Customer  password 
	 * @param password - customer  password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Customer  Coupons  list
	 * @return -list of  Customer  coupons
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}
	/**
	 * setter - for  Customer  purchased coupons
	 * @param coupons -lis  of  coupons
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}
	/**
	 * string format  to show  customer  informations
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", coupons=" + coupons
				+ "]";
	}
	
}
