package org.caringbridge.services.at;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties(locations="/application.yml")
public class Configuration {

    @Value(value="${app.name}")
    private String appName;

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }
    
}
