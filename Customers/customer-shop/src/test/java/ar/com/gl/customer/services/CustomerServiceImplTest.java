package ar.com.gl.customer.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.gl.customer.in.CustomerInResponseDTO;
import ar.com.gl.customer.model.CustomerDTO;
import ar.com.gl.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerService;

	private static final String NAME = "Vilma";
	private static final String LAST_NAME = "Rojas";
	private static final String STATUS = "Activo";
	private static final String EMAIL = "Vilma@gmial.com";
	private static final Integer ID = 1;
	private List<CustomerDTO> list;
	private CustomerDTO customer;

	@BeforeEach
	private void setUp() {
		customer = CustomerDTO.builder().name(NAME).lastName(LAST_NAME).status(STATUS).email(EMAIL).id(ID).build();

		list = new ArrayList<>();
		list.add(CustomerDTO.builder().name(NAME).lastName(LAST_NAME).status(STATUS).email(EMAIL).id(ID).build());

	}

	@Test
	void getAllCustomerTest() {

		// SetUp
		final Iterable<CustomerDTO> value = list;

		// Given
		when(this.customerRepository.findAll()).thenReturn(value);

		// When
		final CustomerInResponseDTO response = customerService.getAllCustomer();

		// Then
		assertEquals(customer, response.getCustomer().stream().findFirst().get());
	}

	@Test
	void getCustomerByIdTest() {

		// SetUp
		final Optional<CustomerDTO> value = Optional.of(customer);

		// Given
		when(this.customerRepository.findById(ID)).thenReturn(value);

		// When
		final CustomerDTO response = customerService.getCustomerById(ID);

		// Then
		assertEquals(customer, response);
	}
	

	@Test
	void updateCustomerTest() {

		// SetUp
		final Optional<CustomerDTO> value = Optional.of(customer);

		// Given
		when(this.customerRepository.findById(ID)).thenReturn(value);

		// When
		final CustomerDTO response = customerService.updateCustomer(customer);

		// Then
		assertEquals(customer, response);
	}


	@Test
	void addCustomerTest() {

		// When
		final CustomerDTO response = customerService.addCustomer(customer);
		customerService.delateCustomer(ID);

		// Then
		assertEquals(customer, response);
	}

	@Test
	void delateCustomerTest() {

		// SetUp
		final Optional<CustomerDTO> value = Optional.of(customer);
		
		//Given
		when(this.customerRepository.findById(ID)).thenReturn(value);
		
		//Then
		final Boolean response = customerService.delateCustomer(ID);
		
		//Then
		assertTrue(response);
	}
	
	@Test
	void updateStatusTest() {

		// SetUp
		final Optional<CustomerDTO> value = Optional.of(customer);

		// Given
		when(this.customerRepository.findById(ID)).thenReturn(value);

		// When
		final CustomerDTO response = customerService.updateStatus(STATUS, ID);

		// Then
		assertEquals(customer, response);
	}


}
