package org.caringbridge.services.at.site;

import org.caringbridge.services.at.CbBaseSpecification;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * Automation tests for the sites API of the microservices
 * applications.
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
class SitesTests extends CbBaseSpecification {

    @Test
    def "get an indidivual site"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11003/labs/sites/' << '390'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def siteRep = parseStringAsJson(result.body)
        then: 
            result.statusCode == HttpStatus.OK
            println siteRep
            siteRep.createdAt != null
            siteRep.updatedAt != null
            siteRep.siteId != null
            siteRep.name != null
            siteRep.primaryOrganizerId != null
            siteRep.clientMetrics != null
            siteRep.deleted != null
    }
    
    @Test
    def "get a non-existant site"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11003/labs/sites/' << '-1'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            //def siteRep = parseStringAsJson(result.body)
        then:
            result.statusCode == HttpStatus.NOT_FOUND
    }
    
    @Test
    def "get list of sites since date"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11003/labs/sites?since=' << "2016-01-01T00:00:00"
            def resp = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
        then:
            resp.statusCode == HttpStatus.OK
            def respRep = parseStringAsJson(resp.body)
            respRep instanceof List
            respRep.empty == false
    }
    
    @Test
    def "get list of sites with bad since date format"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11003/labs/sites?since=' << "01-01-2016"
            def resp = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
        then:
            resp.statusCode == HttpStatus.BAD_REQUEST
            //println resp.body
    }
    
    
    
}
