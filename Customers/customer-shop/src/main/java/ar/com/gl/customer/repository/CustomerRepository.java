package ar.com.gl.customer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.gl.customer.model.CustomerDTO;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDTO, Integer> {


}
