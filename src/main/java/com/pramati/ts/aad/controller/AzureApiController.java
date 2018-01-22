package com.pramati.ts.aad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.ts.aad.domain.Membership;
import com.pramati.ts.aad.service.AzureApiService;


@RestController
@RequestMapping(value = "/ts-aad/secure")
public class AzureApiController {
	
	@Autowired
    private AzureApiService azureApiService;

	@RequestMapping(value = "/getUserRolesFromAzureAD/{userId}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Membership> getUserMembershipInfoFromGraph(@PathVariable String userId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return azureApiService.getUserMembershipInfoFromGraph(userId);
    	
	}
	
	@RequestMapping(value = "/getUserNameFromAzureAD/{userId}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String getUserNameFromAzureAD(@PathVariable String userId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return azureApiService.getUserNameByIdFromGraph(userId);
    	
	}
	
}
