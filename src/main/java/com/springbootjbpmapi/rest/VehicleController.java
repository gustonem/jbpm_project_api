package com.springbootjbpmapi.rest;


import com.springbootjbpmapi.dto.UserDTO;
import com.springbootjbpmapi.service.VehicleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;


    @ApiOperation(value = "Check vehicle state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/state", method = RequestMethod.POST, produces = "application/json")
    public Boolean getState(@RequestBody Long id) {

        return vehicleService.checkState(id);
    }


    @ApiOperation(value = "Update vehicle state")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
    public Boolean updateStatus(@RequestBody Long id) {

        return vehicleService.updateStatus(id);
    }
}