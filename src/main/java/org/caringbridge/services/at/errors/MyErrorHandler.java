package org.caringbridge.services.at.errors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;


public class MyErrorHandler implements org.springframework.web.client.ResponseErrorHandler {
        
        Logger log = LoggerFactory.getLogger(MyErrorHandler.class);

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            return false;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            //Just print the error
            if (log.isDebugEnabled() ){
                StringWriter w = new StringWriter();
                BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody()));
                br.lines().forEach(line->{w.write(line);});
                
                log.debug("Body of error captured by error handler: {}",w.toString());
            }
        }
           
}
