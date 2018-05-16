package com.coupon.system.controllers;
/**
 * @author MatrixComp
 * @name ibrahim shweiki
 *company controller - method  used post ,delete ,put,get , this  class to interact with database facade and  show  the result
 *   on html file  and  using  postman to show  result  as json format
 * 
 */
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.services.companyServices.companyService;
import com.coupon.system.services.companyServices.companyServiceInterface;

@RestController
@RequestMapping("company")
public class companyController implements companyServiceInterface{
	@Autowired
	companyService compService ;
	@PostMapping("createCoupon")
	@Override
	public void createCoupon(@RequestBody Coupon coup) {
	
	compService.createCoupon(coup);
		
	}
	@DeleteMapping("removeCoupon")
	@Override
	public void removeCoupon(@RequestBody Coupon coup) {
		compService.removeCoupon(coup);
		
	}

	@PutMapping("updateCoupon")
	@Override
	public void updateCoupon(@RequestBody Coupon coup) {
		compService.updateCoupon(coup);
		
	}
	@GetMapping
	@Override
	public Collection<Coupon> getAllcoupons(Company comp) {
		
		return compService.getAllcoupons(comp);
	}
	@GetMapping("findCouponId/{id}")
	@Override
	public Coupon getCoupon(@PathVariable("id")int id) {
		
		return compService.getCoupon(id);
	}
	@GetMapping("couponByType/{type}")
	@Override
	public Collection<Coupon> getCouponByType(@PathVariable("ctype")CouponType ctyp) {
		
		return compService.getCouponByType(ctyp);
	}

}
