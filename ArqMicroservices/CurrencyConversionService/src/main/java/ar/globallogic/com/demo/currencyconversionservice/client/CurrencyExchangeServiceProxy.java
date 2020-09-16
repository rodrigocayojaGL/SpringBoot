package ar.globallogic.com.demo.currencyconversionservice.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ar.globallogic.com.demo.currencyconversionservice.model.CurrencyConversion;

//@FeignClient(name="currency-exchange-service", url="http://localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="api-gateway-zuul")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	CurrencyConversion getExchangeValue(@PathVariable String from, @PathVariable String to);
}
