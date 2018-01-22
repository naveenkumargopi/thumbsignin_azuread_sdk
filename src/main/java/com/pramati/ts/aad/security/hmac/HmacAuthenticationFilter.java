package com.pramati.ts.aad.security.hmac;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

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

        final PreAuthenticatedAuthenticationToken authentication =
                new PreAuthenticatedAuthenticationToken(/*apiUser.getUsername()*/authenticationHeader.getApiKey(), null, null);
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
