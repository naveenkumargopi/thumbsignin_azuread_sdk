package com.pramati.ts.aad.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-15T01:38:48.627Z")

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig{
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.ant("/ts-aad/secure/**"))                          
          .build()
          .apiInfo(apiInfo());
    }
	
	/*@Bean
    public Docket webIntegrationApiDocs() {
        return new Docket(DocumentationType.SWAGGER_2).host("https://api.thumbsignin.com").select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/ts/secure/**"))
                .build()
                .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }*/

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ThumbSignin Connector/Gateway API")
                .description(
                        "This is the documentation for ThumbSignin Connector/Gateway API.  The Gateway API uses api keys to secure access to the services.  To access these services please ensure you have a valid api-key.  ")
                //.license("Commercial license")
                //.licenseUrl("http://www.thumbsignin.com/licenses/LICENSE-2.0.html")
                //.termsOfServiceUrl("http://www.thumbsignin.com/licenses/terms.html")
                //.version("1.0.0")
                //.contact(new Contact("Thumbsignin", "https://www.thumbsignin.com", "api@thumbsignin.com"))
                .build();
    }
    
}
