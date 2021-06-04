package com.springbootjbpmapi.rest;

import com.springbootjbpmapi.dto.UserDTO;
import com.springbootjbpmapi.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation(value = "Get user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public UserDetails getUser(@RequestParam String name) {

        return userService.loadUserByUsername(name);
    }


    @ApiOperation(value = "Create user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "text/plain")
    public String signUpUser(@RequestBody UserDTO user) {
        return userService.signUpUser(user);
    }

    @ApiOperation(value = "Confirm user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/confirm", method = RequestMethod.POST, produces = "application/json")
    public String confirmUser(@RequestParam String token) {
        return userService.confirmUser(token); }

}
