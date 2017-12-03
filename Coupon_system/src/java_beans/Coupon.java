package java_beans;

import java.io.Serializable;
import java.util.Date;
/*
 * JavaBeans are Java classes which adhere to an extremely simple coding convention. All you have to do is to

Implement java.io.Serializable interface - To save the state of an object
use a public empty argument constructor - To instantiate the object
And provide public getter and setter methods - To get and set the values of private variables (properties ).
 */
public class Coupon  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id =0 ; 
	private String title ; 
	private Date startDate;
	private Date endDate;
	private int amount ; 
	private CouponType ctyp;
	private String message;
	private double price;
	private String image;
	public Coupon(){}
	
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType ctyp, String message,
			double price, String image) {
		super();
		this.id = 0;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.ctyp = ctyp;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount2) {
		this.amount = amount2;
	}
	public CouponType getCtyp() {
		return ctyp;
	}
	public void setCtyp(CouponType ctyp) {
		this.ctyp = ctyp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", ctyp=" + ctyp + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
	
}
