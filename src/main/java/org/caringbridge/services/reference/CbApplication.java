package org.caringbridge.services.reference;

import org.caringbridge.common.services.annotations.EnableCorrelationFilter;
import org.caringbridge.common.services.annotations.EnableTrackRequestTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main class to run the application.
 * 
 * @author guanlun.mu
 *
 */

@SpringBootApplication
@ComponentScan("org.caringbridge.services.reference")
@EnableCorrelationFilter
@EnableTrackRequestTime
@EnableSwagger2
public class CbApplication {
	
	/**
	 * Main method to run the Spring Boot Application.
	 * @param args arguments used when running on command line.
	 */
	public static void main(final String[] args) {
		SpringApplication.run(CbApplication.class, args);
	}
}
