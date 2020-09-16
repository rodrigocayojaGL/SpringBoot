package ar.globallogic.com.demo.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.globallogic.com.demo.currencyconversionservice.client.CurrencyExchangeServiceProxy;
import ar.globallogic.com.demo.currencyconversionservice.model.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy feign;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		CurrencyConversion response = feign.getExchangeValue(from, to);
		
		
		logger.info("{}", response);
		
		return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), 
				quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	
}
