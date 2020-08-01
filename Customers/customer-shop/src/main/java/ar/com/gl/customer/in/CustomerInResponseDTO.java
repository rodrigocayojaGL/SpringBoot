package ar.com.gl.customer.in;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.gl.customer.model.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerInResponseDTO {

	private Collection<CustomerDTO> customer;

	public CustomerInResponseDTO() {
		this.customer = new ArrayList<>();
	}
}
