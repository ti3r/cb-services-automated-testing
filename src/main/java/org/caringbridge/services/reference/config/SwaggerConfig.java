package org.caringbridge.services.reference.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration for Swagger Doc Api.
 * 
 * @author guanlun.mu
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * The configurable property that will contain the Title Of The Swagger Api
     * Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.title}")
    private String swaggerApiTitle;
    /**
     * The configurable property that will contain the Description Of The
     * Swagger Api Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.description}")
    private String swaggerApiDescription;
    /**
     * The configurable property that will contain the Terms of Service Location
     * Of The Swagger Api Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.termslocation}")
    private String swaggerApiTermsLocation;
    /**
     * The configurable property that will contain the contact email Of The
     * Swagger Api Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.contact}")
    private String swaggerApiContact;
    /**
     * The configurable property that will contain the license type Of The
     * Swagger Api Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.license.type}")
    private String swaggerApiLicenseName;
    /**
     * The configurable property that will contain the license url Of The
     * Swagger Api Documentation.
     */
    @Value(value = "${org.caringbridge.services.swagger.license.url}")
    private String swaggerApiLicenseUrl;

    @Value(value = "${org.caringbridge.services.version}")
    private String swaggerApiVersion;
    
    @Value(value = "${app.name}")
    private String appName;
    
    @Value(value = "${org.caringbridge.services.swagger.uri-base}")
    private String swaggerUriBase;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select() 
            .apis(RequestHandlerSelectors.any()) 
            .paths(regex(swaggerUriBase + ".*"))
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(swaggerApiTitle, swaggerApiDescription, swaggerApiVersion, swaggerApiTermsLocation, swaggerApiContact, swaggerApiLicenseName, swaggerApiLicenseUrl);
        return apiInfo;
    }

}
