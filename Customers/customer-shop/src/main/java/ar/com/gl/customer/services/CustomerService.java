package ar.com.gl.customer.services;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;

public interface CustomerService {

	CustomerInResponseDTO getAllCustomer();

	CustomerDTO getCustomerById(Integer id);

	CustomerDTO addCustomer(CustomerDTO customer);

	CustomerDTO updateCustomer(CustomerDTO customer);
	
	CustomerDTO updateStatus(String status, Integer id);

	Boolean delateCustomer(Integer id);
}
