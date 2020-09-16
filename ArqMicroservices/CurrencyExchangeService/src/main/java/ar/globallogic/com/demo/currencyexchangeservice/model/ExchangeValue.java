package ar.globallogic.com.demo.currencyexchangeservice.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValue {
	
	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	
	//solo para fines educativos!!!
	private int port;
}
