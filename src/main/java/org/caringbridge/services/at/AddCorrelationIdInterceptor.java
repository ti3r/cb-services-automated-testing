package org.caringbridge.services.at;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class AddCorrelationIdInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        //Add the correlation id header to all the requests.
        request.getHeaders().add("X-Request-Id", "regression-testing-agent-on-"+LocalDateTime.now().toString());
        return execution.execute(request, body);
    }

}
