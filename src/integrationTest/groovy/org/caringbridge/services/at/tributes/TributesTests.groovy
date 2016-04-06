package org.caringbridge.services.at.tributes;

import org.caringbridge.services.at.CbBaseSpecification;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime
import java.util.List;

/**
 * Automation tests for the sites API of the microservices
 * applications.
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
class TributesTests extends CbBaseSpecification {

    @Test
    def "get an tributes of site"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11002/tributes?siteId=' << '231542'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def rep = parseStringAsJson(result.body)
        then: 
            result.statusCode == HttpStatus.OK
            //println siteRep
            rep.each { t->
                println t
                assert t.id != null
                assert t.siteId != null
                assert t.createdAt != null
                assert t.updatedAt != null
                assert t.isDisplayed != null
                assert t.signature != null
                assert t.isAnonymous != null
                //if (t.isDisplayed == true)
                //    assert t.body != null
            }
    }
    
    @Test
    def "get an tributes of non-existant site"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11002/tributes?siteId=' << '-1232'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def rep = parseStringAsJson(result.body)
        then:
            result.statusCode == HttpStatus.OK
            rep.empty == true
    }
    
    @Test
    def "get an tributes of site with pagination"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11002/tributes?siteId=231542&pageSize=2'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def rep = parseStringAsJson(result.body)
        then:
            result.statusCode == HttpStatus.OK
            rep.size() == 2
    }
    
    @Test
    def "get an tributes of site with bad pagination param"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11002/tributes?siteId=231542&pageSize=0'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def rep = parseStringAsJson(result.body)
        then:
            result.statusCode == HttpStatus.BAD_REQUEST
            rep.timeStamp != null
            rep.errorCode == "400"
            rep.message != null
    }
    
    @Test
    def "get an tributes of site with asc sorting created date"(){
        when:
            def url = 'http://vm30-2.caringbridge.org:11002/tributes?siteId=231542&createdAtSort=asc'
            def result = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
            def rep = parseStringAsJson(result.body)
        then:
            result.statusCode == HttpStatus.OK
            def prevDate = null
            rep.each { r->
                if (prevDate == null)
                    prevDate = LocalDateTime.parse(r.createdAt)
                assert LocalDateTime.parse(r.createdAt) >= prevDate
            }
    }
    
}
