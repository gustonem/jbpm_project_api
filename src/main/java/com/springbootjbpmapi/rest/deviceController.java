package com.springbootjbpmapi.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class deviceController {


    @ApiOperation(value = "Notify server")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/notify", method = RequestMethod.GET, produces = "application/json")
    public String notifyServer(@RequestParam Integer battery) {

        return "Battery status updated";
    }
}
