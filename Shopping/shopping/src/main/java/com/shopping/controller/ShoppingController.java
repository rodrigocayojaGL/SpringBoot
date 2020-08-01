package com.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shopping.feign.Servicefeign;

@RestController
public class ShoppingController {

	private static final String FALLBACK = "FALLBACK";
	private Servicefeign service;

	public ShoppingController(Servicefeign service) {
		this.service = service;
	}

	@HystrixCommand(fallbackMethod = "testFallbackMethod")
	@GetMapping("/payment")
	public String test() {
		return service.payNow();
	}

	public String testFallbackMethod() {
		return FALLBACK;
	}

}
