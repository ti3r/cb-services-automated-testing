package org.caringbridge.services.at;

import spock.lang.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate
import org.caringbridge.services.at.errors.MyErrorHandler
import org.caringbridge.services.at.rules.LoggerRule;
import org.caringbridge.services.at.rules.RestTemplateRule
import org.junit.Rule;
import org.junit.rules.TestName
import com.jayway.jsonpath.JsonPath;

/**
 * The base specification class that provides basic functionality 
 * and common operations for all the specifications in the project.
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
@ContextConfiguration(classes=[Configuration.class])
public class CbBaseSpecification extends Specification{

    /**
     * Provides RestTemplate common objects that could 
     * be used to make rest calls to all the different
     * endpoints for the apis.
     */
    @Rule
    public RestTemplateRule restTemplates = new RestTemplateRule();
    /**
     * provides the name of the test case in case needed during the execution
     * of the test scenario.
     */
    @Rule
    public TestName name = new TestName();
    
    @Rule
    public LoggerRule logger = new LoggerRule();
    
    
    
    def parseStringAsJson(body){
        return JsonPath.parse(body).json()
    }
    
}
