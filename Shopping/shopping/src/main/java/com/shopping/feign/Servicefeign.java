package com.shopping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("PAYMENT-SERVICE")
public interface Servicefeign {

	@GetMapping(value = "/payment/")
	public String payNow();
}
