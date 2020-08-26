package com.graphql.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphql.entity.Vehicle;
import com.graphql.service.VehicleService;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

	public VehicleQuery(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}

	private VehicleService vehicleService;

	public List<Vehicle> findAllVehicle(final int count) {
		return this.vehicleService.getAllVehicles(count);
	}

	public Optional<Vehicle> findByIdVehicle(final int id) {
		return this.vehicleService.getVehicle(id);
	}
}
