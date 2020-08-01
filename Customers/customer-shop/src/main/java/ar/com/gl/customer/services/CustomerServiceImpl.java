package ar.com.gl.customer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerInResponseDTO getAllCustomer() {
		final CustomerInResponseDTO response = new CustomerInResponseDTO();
		final Iterable<CustomerDTO> customers = customerRepository.findAll();
		customers.iterator().forEachRemaining(response.getCustomer()::add);
		return response;
	}

	@Override
	public CustomerDTO getCustomerById(Integer id) {
		CustomerDTO response = new CustomerDTO();
		final Optional<CustomerDTO> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			response = customer.get();
		}
		return response;
	}

	@Override
	public CustomerDTO updateCustomer(CustomerDTO customer) {
		CustomerDTO response = new CustomerDTO();
		final Optional<CustomerDTO> customers = customerRepository.findById(customer.getId());
		if (customers.isPresent()) {
			customerRepository.save(customer);
			response = customer;
		}
		return response;
	}

	@Override
	public CustomerDTO addCustomer(CustomerDTO customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Boolean delateCustomer(Integer id) {
		Boolean status = false;
		final Optional<CustomerDTO> customers = customerRepository.findById(id);
		if (customers.isPresent()) {
			customerRepository.deleteById(id);
			status = true;
		}
		return status;
	}

	@Override
	public CustomerDTO updateStatus(String status, Integer id) {

		CustomerDTO response = new CustomerDTO();
		final Optional<CustomerDTO> customers = customerRepository.findById(id);
		if (customers.isPresent()) {
			response = customers.get();
			response.setStatus(status);
			customerRepository.save(response);
		}
		return response;
	}

}
