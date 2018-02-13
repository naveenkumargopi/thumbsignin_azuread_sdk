package com.pramati.ts.aad.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.ts.aad.service.ThumbsigninApiService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/ts-aad/secure")
public class ThumbsigninApiController {

	@Autowired
    private ThumbsigninApiService thumbsigninApiService;
	
	@ApiOperation(value = "Authentication", notes = "Initiate the Thumbsignin authentication and Creates a new transaction.", response = String.class, position = 1)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "New Authentication request successfully created", response = String.class),
            @ApiResponse(code = 400, message = "The request was invalid, often missing a required parameter. More details in the response body"),
            @ApiResponse(code = 401, message = "Authentication credentials were missing or incorrect"),
            @ApiResponse(code = 403, message = "The caller doesn’t have sufficient permission"),
            @ApiResponse(code = 500, message = "There was an error within the ThumbSignIn Server")})
	@RequestMapping(value = { "/authenticate", "/register/{userId}", "/authStatus/{transactionId}", "/regStatus/{transactionId}" }, method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String handleThumbSigninRequest(HttpServletRequest servletRequest) throws IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String thumbsigninResponseStr = "";
		String apiKey = authentication.getPrincipal().toString();
		String apiSecret = authentication.getCredentials().toString();
		
		thumbsigninResponseStr = thumbsigninApiService.processThumbsigninRequest(servletRequest, apiKey, apiSecret);
    	
		return thumbsigninResponseStr;
    	
	}
	
}
