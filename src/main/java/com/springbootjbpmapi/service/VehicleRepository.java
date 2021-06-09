package com.springbootjbpmapi.service;

import com.springbootjbpmapi.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
