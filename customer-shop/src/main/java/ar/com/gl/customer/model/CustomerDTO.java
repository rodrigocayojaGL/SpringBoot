package ar.com.gl.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "CUSTOMER")
@JsonInclude(value = Include.NON_ABSENT)
public class CustomerDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CU_ID")
	private Integer id;

	@Column(name = "CU_LASTNAME")
	private String lastName;

	@Column(name = "CU_FIRSTNAME")
	private String name;

	@Column(name = "CU_STATUS")
	private String status;

	@Column(name = "CU_EMAIL")
	private String email;

}
