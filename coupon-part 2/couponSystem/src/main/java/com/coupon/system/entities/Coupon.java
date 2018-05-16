package com.coupon.system.entities;
/*
 * name - ibrahim shweiki
 *  creating  entity jpa coupon that will generate and create table in name all_coupon in mysql server in automatic 
 *  and  using  company member as one to many annotation ,that give  a relation between company table and  coupons table 
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name="allCoupons")
public class Coupon implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id ;
	@Column(name = "TITLE")
	private String title ;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "AMOUNT")
	private int amount ; 
	@Column(name = "CTYPE")
	private CouponType ctyp;
	@Column(name = "MESSAGE")
	private String message;
	@Column(name = "PRICE")
	private double price;
	@Column(name = "IMAGE")
	private String image;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="company_id" ,referencedColumnName="ID")
	private Company company;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id" ,referencedColumnName="ID")
	private Customer customer;
	public Coupon(){}
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType ctyp, String message,
			double price, String image)
	{
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
	public void setId(int id) {
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
