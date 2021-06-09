package com.springbootjbpmapi.rest;

import com.springbootjbpmapi.dto.UserDTO;
import com.springbootjbpmapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @ApiOperation(value = "Check if user exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Unauthorised"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json")
    public Boolean getUser(@RequestBody UserDTO user) {

        return userService.userExist(user.getEmail());
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
    public String confirmUser(@RequestBody String token) {
        return userService.confirmUser(token); }

}
