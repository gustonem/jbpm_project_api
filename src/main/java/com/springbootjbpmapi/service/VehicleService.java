package com.springbootjbpmapi.service;

import com.springbootjbpmapi.entity.State;
import com.springbootjbpmapi.entity.User;
import com.springbootjbpmapi.entity.Vehicle;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;



    public Boolean checkState(Long vehicleId) {

        final Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);


        Vehicle vehicle = optionalVehicle.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle does not exist"));

        return vehicle.getState() == State.REPAIRABLE;
    }
}
