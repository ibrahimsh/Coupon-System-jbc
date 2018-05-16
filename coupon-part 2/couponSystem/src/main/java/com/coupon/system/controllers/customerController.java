package com.coupon.system.controllers;
/**
 * @author MatrixComp
 * @name ibrahim shweiki
 * customer controller - method  used post ,delete ,put,get , this  class to interact with database facade and  show  the result
 *   on html file  and  using  postman to show  result  as json format
 * 
 */
import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.Customer;
import com.coupon.system.services.CustomerServices.customerService;
import com.coupon.system.services.CustomerServices.customerServiceInterface;

@RestController
@RequestMapping("customer")
public class customerController implements customerServiceInterface {
	@Autowired
	customerService custService ;
	
	@PutMapping("purchaseCoupon")
	@Override
	public void purchaseCoupon(@RequestBody Coupon coup,@RequestBody Customer cust) {
		
		custService.purchaseCoupon(coup, cust);
	}
	@GetMapping("findCustomerCoupon")
	@Override
	public Collection<Coupon> getAllPurchasedCoupons(@RequestBody Customer cust) {
		
		return custService.getAllPurchasedCoupons(cust);
	}
	@GetMapping("couponByType")
	@Override
	public Collection<Coupon> getAllpurcheasedCouponsbyType(@RequestBody CouponType ctype) {
		
		return custService.getAllpurcheasedCouponsbyType(ctype);
	}
	@GetMapping("getCouponByPrice/{price}")
	@Override
	public Collection<Coupon> getAllPurchasedCouponsByPrice(@PathVariable("price")double price) {
		
		return custService.getAllPurchasedCouponsByPrice(price);
	}
	@GetMapping("allCoupons")
	@Override
	public Collection<Coupon> findCoupons() {
		// TODO Auto-generated method stub
		return custService.findCoupons();
	}

}
