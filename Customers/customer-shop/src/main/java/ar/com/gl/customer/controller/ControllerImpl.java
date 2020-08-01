package ar.com.gl.customer.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.services.CustomerService;

@RestController
public class ControllerImpl implements Controller {

	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<CustomerInResponseDTO> getAllCustomer() {
		final CustomerInResponseDTO response = customerService.getAllCustomer();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable(required = true) final Integer id) {
		final CustomerDTO response = customerService.getCustomerById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody(required = true) final CustomerDTO customer) {
		final CustomerDTO response = customerService.addCustomer(customer);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody(required = true) final CustomerDTO customer) {
		final CustomerDTO response = customerService.updateCustomer(customer);
		if (Objects.isNull(response.getId())) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> delateCustomer(@PathVariable(required = true) final Integer id) {

		final boolean response = customerService.delateCustomer(id);
		if (!response) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerDTO> updateStatus(@PathVariable(required = true) final Integer id,
			@RequestParam(required = true) final String status) {

		final CustomerDTO response = customerService.updateStatus(status, id);
		if (Objects.isNull(response.getId())) {
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
