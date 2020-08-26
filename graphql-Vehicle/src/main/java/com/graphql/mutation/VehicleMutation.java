package com.graphql.mutation;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.entity.Vehicle;
import com.graphql.service.VehicleService;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

	private VehicleService vehicleService;

	public VehicleMutation(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}

	public Vehicle createVehicle(final String type, final String model) {
		return this.vehicleService.createVehicle(type, model);
	}
}
