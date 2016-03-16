package org.caringbridge.services.at.rules;

import java.util.Arrays;

import org.caringbridge.services.at.AddCorrelationIdInterceptor;
import org.caringbridge.services.at.errors.MyErrorHandler;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.web.client.RestTemplate;

public class RestTemplateRule implements TestRule{

    private RestTemplate template;
    
    public RestTemplate getRestTemplate(){
        return this.template;
    }
    
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            
            @Override
            public void evaluate() throws Throwable {
                template = new RestTemplate();
                template.setErrorHandler(new MyErrorHandler());
                template.setInterceptors(Arrays.asList(new AddCorrelationIdInterceptor()));
                base.evaluate();
            }
        };
    }

    
    
}
