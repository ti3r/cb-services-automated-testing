package org.caringbridge.services.at.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that will provide a Logger object for the test scenarios
 * 
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
public class LoggerRule implements TestRule {
   
    static {
        //Initiate the logger properties before the logger instance is created
        System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
    }
    
    private Logger logger;

    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                logger = LoggerFactory.getLogger(description.getTestClass().getName());
                
                try {
                    base.evaluate();
                } finally {
                    logger = null;
                }
            }
        };
    }

}
