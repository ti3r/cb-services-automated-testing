# cb-services-reference
A reference project for quickly building micro-service. Fork this project as a starter for another microservice. Features of this service include:

* Injection of the correlation ID into logs through the EnableCorrelationFilter
* Configuration of Undertow access logging, including recording response times through EnableTrackRequestTime and settings in application.yml.
* Swagger documentation available at the root of the service.
* Exposure of health status using spring-boot-actuator

When forking, adjust the following:

* Change the package org.caringbridge.services.reference to org.caringbridge.services.[new-service-name]
* In the application.yml, replace the values in the "app" section. For example, app.name should match the service name, like "campaigns".
* In the build.gradle, replace the appName in the ext section with the proper application name.
* In the logback-spring.xml, replace the app.name property value with the proper application name.
* Review the health status exposure id if necessary to change (By Default is is exposed as /health)
