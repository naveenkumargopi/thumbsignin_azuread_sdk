package com.pramati.ts.aad.security.hmac;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pramati.ts.aad.config.ThumbSigninProperties;
import com.pramati.ts.aad.config.ThumbSigninProperties.ApiUser;

public class HmacAuthenticationFilter extends OncePerRequestFilter {

    /*@Autowired
    private ApiUserService apiUserService;

    public HmacAuthenticationFilter(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }*/
	
	private HmacVerifier hmacVerifier;
	
	public HmacAuthenticationFilter(HmacVerifier hmacVerifier) {
        this.hmacVerifier = hmacVerifier;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        CachingRequestWrapper requestWrapper = new CachingRequestWrapper(request);       

        AuthenticationHeader authenticationHeader = hmacVerifier.verify(requestWrapper/*, apiUserService*/);
        
        //ApiUser apiUser = apiUserService.loadUserByUsername(authenticationHeader.getApiKey());
        
        String apiSecret = "";
        for (ApiUser apiUser : hmacVerifier.getThumbSigninProperties().getApiUsers()) {
        	if (apiUser.getKey().equals(authenticationHeader.getApiKey())) {
        		apiSecret = apiUser.getSecret();
        		break;
        	}
        }

        final PreAuthenticatedAuthenticationToken authentication =
                new PreAuthenticatedAuthenticationToken(/*apiUser.getUsername()*/authenticationHeader.getApiKey(), apiSecret, null);
        authentication.setDetails(/*apiUser*/authenticationHeader.getApiKey());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            filterChain.doFilter(requestWrapper, response);
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /*@Required
    public void setApiUserService(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }*/
}
