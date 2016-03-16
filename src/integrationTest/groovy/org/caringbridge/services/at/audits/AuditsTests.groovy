package org.caringbridge.services.at.audits

import org.caringbridge.services.at.CbBaseSpecification;
import org.springframework.http.HttpStatus

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import com.jayway.jsonpath.JsonPath;


class AuditsTests extends CbBaseSpecification{

    @Test
    def "list audit objects and check for list of results"(){
        when: "Making a call to audits api to retrieve objects"
            def (stat, body, json) = getAudits()
        then: "Result code should be OK and results should be an array"   
            stat == HttpStatus.OK
            json instanceof List
    }
    
    @Test
    def "list audits using pageSize as 3"(){
        when: "Make a call to audits with pageSize=3"
            def (stat, body, json) = getAudits("3")
        then: "Return status should be ok and size of page should be 3"
            stat == HttpStatus.OK
            json instanceof ArrayList
            json.size() == 3
    }
    
    def getAudits(String pageSize=null){
        def url = 'http://vm30-2.caringbridge.org:11000/audits'<<'?'
        if (pageSize != null){
            url <<'&pageSize='<<pageSize
        }
        def ent = getRestTemplates().restTemplate.getForEntity(url.toString(),String.class)
        def json = JsonPath.parse(ent.body).read("\$")
        return [ent.statusCode, ent.body, json]
    }
}
