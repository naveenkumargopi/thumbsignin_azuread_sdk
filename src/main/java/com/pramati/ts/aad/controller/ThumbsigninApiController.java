package com.pramati.ts.aad.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.ts.aad.service.ThumbsigninApiService;

@RestController
@RequestMapping(value = "/ts-aad/secure")
public class ThumbsigninApiController {

	@Autowired
    private ThumbsigninApiService thumbsigninApiService;
	
	@RequestMapping(value = { "/authenticate", "/register/{userId}", "/authStatus/{transactionId}", "/regStatus/{transactionId}" }, method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String handleThumbSigninRequest(HttpServletRequest servletRequest) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String thumbsigninResponseStr = "";
		try {
			thumbsigninResponseStr = thumbsigninApiService.processThumbsigninRequest(servletRequest);
		} catch (Exception e) {
			thumbsigninResponseStr = "Unexpected error while handling thumbsignin request";
		}
    	return thumbsigninResponseStr;
    	
	}
	
}
