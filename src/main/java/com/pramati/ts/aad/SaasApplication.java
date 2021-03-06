package com.pramati.ts.aad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.pramati.ts.aad.config.ThumbSigninProperties;

@SpringBootApplication
@EnableConfigurationProperties(ThumbSigninProperties.class)
public class SaasApplication extends SpringBootServletInitializer{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SaasApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SaasApplication.class, args);
	}
}