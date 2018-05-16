package com.coupon.system;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class textControl {

	@RequestMapping("/hitest")
	public String testhello()
	{
		return "hello spring";
	}
	
}
