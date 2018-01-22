package com.pramati.ts.aad.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.pramati.ts.aad.security.hmac.HmacAuthenticationFilter;
import com.pramati.ts.aad.security.hmac.HmacVerifier;

/**
 * Class ApiSecurityConfigurationAdapter.
 *
 * @author nagarajan
 * @version 1.0
 * @since <pre>8/8/17 12:26 PM</pre>
 */
@Configuration
@Order(1)
public class ApiSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private ApiUserService apiUserService;

    @Autowired
    private CommonsRequestLoggingFilter requestLoggingFilter;*/
	
	@Autowired
	private HmacVerifier hmacVerifier;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.addFilterBefore(requestLoggingFilter, SecurityContextPersistenceFilter.class);
        http.exceptionHandling()
                .and()
                .csrf()
                .disable()
                .antMatcher("/ts-aad/secure/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new HmacAuthenticationFilter(/*apiUserService*/hmacVerifier), BasicAuthenticationFilter.class);

        http.headers().disable();
    }
}
