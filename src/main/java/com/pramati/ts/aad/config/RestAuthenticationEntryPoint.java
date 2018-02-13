package com.pramati.ts.aad.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class RestAuthenticationEntryPoint.
 *
 * @author nagarajan
 * @version 1.0
 * @since <pre>7/8/17 10:10 AM</pre>
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private DefaultErrorAttributes errorAttributes;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        request.setAttribute("javax.servlet.error.request_uri", request.getPathInfo());
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable error = this.errorAttributes.getError(requestAttributes);
        if( error == null ){
            error = authException;
        }
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ApiError errorResponse = new ApiError(HttpStatus.UNAUTHORIZED, error.getMessage(), "ABCD");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

}
