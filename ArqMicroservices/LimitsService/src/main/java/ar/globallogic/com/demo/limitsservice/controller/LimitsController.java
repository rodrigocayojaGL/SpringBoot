package ar.globallogic.com.demo.limitsservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ar.globallogic.com.demo.limitsservice.model.Limits;

@RestController
public class LimitsController {

	
	@Value("${limits-service.minimun}")
	private int minimun;
	
	@Value("${limits-service.maximun}")
	private int maximun;
	
	@GetMapping("/limits")
	public Limits getLimits() {
		
		return new Limits(maximun, minimun);
		
	}
	
	@HystrixCommand(fallbackMethod="fallbackGetLimitConfiguration")
	@GetMapping("/fault-tolerance-demo")
	public Limits getLimitConfiguration() {
		throw new RuntimeException("Service not available");
		
	}
	
	public Limits fallbackGetLimitConfiguration() {
		return new Limits(999,9);
	}
	
}
