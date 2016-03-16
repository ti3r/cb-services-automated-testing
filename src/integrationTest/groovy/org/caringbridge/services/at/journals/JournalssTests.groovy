package org.caringbridge.services.at.journals;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.ReadContext;
import com.jayway.jsonpath.Option;

import java.io.IOException;

import org.caringbridge.services.at.errors.MyErrorHandler;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import spock.lang.Specification;
import com.jayway.jsonpath.JsonPath;
import static java.util.UUID.randomUUID;

import org.caringbridge.services.at.CbBaseSpecification;
import org.caringbridge.services.at.Configuration;

class JournalssTests extends CbBaseSpecification{
    
    @Test
    public void "Test Get Journals for site 25"(){
        when: "Get Journals for site 25"
            def (status, body) = getJournals("25")    
            ReadContext ctx = JsonPath.parse(body)
        then: "Check OK status and journal body"
            status == HttpStatus.OK
            List journals = ctx.read("\$")
            journals.each { j->
                assertJournalBody(j)
            }
    }
   
    @Test
    def "Test Pagination of journals"(){
       when: "Get journals for site 25 and page size 2"
           def (status, body) = getJournals("25","2")
       then: "Parse the response using jsonpath"
           ReadContext ctx = JsonPath.parse(body)
           List journals = ctx.read("\$")
       expect: "OK status and 2 journals"
           status == HttpStatus.OK
           journals.size() == 2
           journals.each { j->
               assertJournalBody(j)
           }
    } 
    
    @Test
    def "Test status filter of journals"(){
        when: "Get journals for site 25 and draft status"
            def (status, body) = getJournals("25",null,"draft")
        then: "Parse the result of the body"
            //println body
            List journals = JsonPath.parse(body).read("\$")
        expect:
            status == HttpStatus.OK
            journals.size() == 0
    }
   
    @Test
    def "Test get journal"(){
        when: "I get the journal with id 51f91598f294d4f14195373b"
            def(status, body) = getJournal("51f91598f294d4f14195373b")
        then: "Parse the array result and test first element"
            Object j = JsonPath.parse(body).json();
            //println j
            status == HttpStatus.OK
            j != null
            assertJournalBody(j)
    }
    
    @Test
    def "Test get non existant journal"(){
        when: "I get a journal with non existant id"
            def uuid = randomUUID() as String
            def(status, body) = getJournal(uuid)
       then: "Check no found code"
           status == HttpStatus.NOT_FOUND
    }
    
    def getJournal(String journalId){
        def url = 'http://vm30-2.caringbridge.org:11001/journals/'<<journalId
        ResponseEntity ent = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
        String body = ent.body
        HttpStatus status = ent.statusCode
        return [status, body]
    }
    
   def getJournals(String siteId, String pageSize = null, String stateFilter = null){
       def url = 'http://vm30-2.caringbridge.org:11001/journals?siteId='<<siteId
       if (pageSize != null)
           url <<"&pageSize="<<pageSize
       if (stateFilter != null)
           url <<"&state="<<stateFilter
               
       ResponseEntity ent = restTemplates.getRestTemplate().getForEntity(url.toString(), String.class)
       String body = ent.body
       HttpStatus status = ent.statusCode
       return [status, body]
   }
    
   def assertJournalBody(journal){
       println journal
       journal.journalId != null
       journal.title != null
       journal.body != null
       journal.userId != null
       journal.siteId != null
       //journal.deleted != null
       //journal.draft != null
   }
   
}
