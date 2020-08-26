package com.graphql.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graphql.entity.Vehicle;
import com.graphql.repository.VehicleRepository;

@Service
public class VehicleService {

	private final VehicleRepository vehicleRepository;

	public VehicleService(final VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Transactional
	public Vehicle createVehicle(final String type, final String model) {
		final Vehicle vehicle = new Vehicle();
		vehicle.setType(type);
		vehicle.setModel(model);

		return this.vehicleRepository.save(vehicle);
	}

	@Transactional(readOnly = true)
	public List<Vehicle> getAllVehicles(final int count) {
		return this.vehicleRepository.findAll().stream().limit(count).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Optional<Vehicle> getVehicle(final int id) {
		return this.vehicleRepository.findById(id);
	}
}
