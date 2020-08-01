package com.javatechie.spring.eureaka.client.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ShoppingController {
	@Autowired
	private RestTemplate template;

	@GetMapping("/amazon")
	public String invokePaymentService() {
		return template.getForObject("http://PAYMENT-SERVICE/payment/", String.class);
	}

	@GetMapping("/test")
	public String test() {
		return "working....";
	}

}
