package ar.com.gl.customer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;

public interface Controller {

	@GetMapping(path = "/customers")
	public ResponseEntity<CustomerInResponseDTO> getAllCustomer();

	@GetMapping(path = "/customers/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(Integer id);

	@PostMapping(path = "/customers")
	public ResponseEntity<CustomerDTO> addCustomer(CustomerDTO customer);

	@PutMapping(path = "/customers")
	public ResponseEntity<CustomerDTO> updateCustomer(CustomerDTO customer);

	@DeleteMapping(path = "/customers/{id}")
	public ResponseEntity<Void> delateCustomer(Integer id);
	
	@PatchMapping(path = "/customers/{id}")
	public ResponseEntity<CustomerDTO> updateStatus(Integer id, String status);
}
