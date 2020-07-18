package ar.com.gl.customer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.services.CustomerService;

@ExtendWith(MockitoExtension.class)
class ControllerImplTest {

	@Mock
	private CustomerService service;

	@InjectMocks
	private ControllerImpl controller;

	private CustomerDTO customer;

	private CustomerInResponseDTO inResponse;

	private static final String NAME = "Vilma";
	private static final String LAST_NAME = "Rojas";
	private static final String STATUS = "Activo";
	private static final String EMAIL = "Vilma@gmial.com";
	private static final Integer ID = 1;

	@BeforeEach
	private void setUp() {
		customer = CustomerDTO.builder().name(NAME).lastName(LAST_NAME).status(STATUS).email(EMAIL).id(ID).build();
		inResponse = new CustomerInResponseDTO();
		inResponse.getCustomer().add(customer);

	}

	@Test
	void getAllCustomerTest() {

		// Given
		when(this.service.getAllCustomer()).thenReturn(inResponse);

		// When
		final ResponseEntity<CustomerInResponseDTO> repoonse = controller.getAllCustomer();

		// Then
		assertEquals(customer, repoonse.getBody().getCustomer().stream().findFirst().get());
	}

	@Test
	void getCustomerByIdTest() {

		// Given
		when(this.service.getCustomerById(ID)).thenReturn(customer);

		// When
		final ResponseEntity<CustomerDTO> repoonse = controller.getCustomerById(ID);

		// Then
		assertEquals(customer, repoonse.getBody());
	}

	@Test
	void addCustomerTest() {

		// Given
		when(this.service.addCustomer(customer)).thenReturn(customer);

		// When
		final ResponseEntity<CustomerDTO> repoonse = controller.addCustomer(customer);

		// Then
		assertEquals(customer, repoonse.getBody());

	}

	@Test
	void updateCustomerTest() {

		// Given
		when(this.service.updateCustomer(customer)).thenReturn(customer);

		// When
		final ResponseEntity<CustomerDTO> repoonse = controller.updateCustomer(customer);

		// Then
		assertEquals(customer, repoonse.getBody());
	}

	@ParameterizedTest
	@ValueSource(strings = { "true", "false" })
	void delateCustomerrTest(boolean status) {

		// Given
		when(this.service.delateCustomer(ID)).thenReturn(status);

		// When
		final ResponseEntity<Void> response = controller.delateCustomer(ID);

		// Then
		assertNotNull(response);
	}

	@Test
	void updateStatusTest() {

		// Given
		when(this.service.updateStatus(STATUS, ID)).thenReturn(customer);

		// When
		final ResponseEntity<CustomerDTO> repoonse = controller.updateStatus(ID, STATUS);

		// Then
		assertEquals(customer, repoonse.getBody());
	}

}
