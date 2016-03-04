package org.caringbridge.services.reference.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * Controller for handling the request by mapping url.
 * 
 * @author guanlun.mu
 *
 */
@RestController
@Api(basePath = "/references", description = "The references resource is a dummy api for demonstration purposes.", value = "references")
@RequestMapping(path = "/references")
public class PingController {

	/**
	 * The log.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PingController.class);

	/**
	 * @return the log
	 */
	public Logger getLog() {
		return LOG;
	}

	/**
	 * A Ping controller to check the health of the controller.
	 * 
	 * @return Response entity that only display a message.
	 */
	@RequestMapping(path="/ping", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "ping", httpMethod = "GET", notes = "Responds with a success result if the service is healthy.", produces = "application/json")
	@ApiResponse(code = 404, message = "No Information Found for this Provider")
	public ResponseEntity<PingResult> ping() {
		getLog().info("Getting the ping info......");
		return ResponseEntity.ok(new PingResult("success"));

	}

}

// This is just here to provide an example of how to return an object and have it automatically converted to json. Normally, there would be a
// model class to represent the resource being requested. A service call would retrieve or build the model object and the controller would return it.
class PingResult {
    private String result;
    public PingResult(String result) { this.result = result; }
    public String getResult() { return result; }
}
