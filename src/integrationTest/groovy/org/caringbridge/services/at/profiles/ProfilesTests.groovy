package org.caringbridge.services.at.profiles

import org.caringbridge.services.at.CbBaseSpecification;
import org.springframework.http.HttpStatus;
/**
 * Integration tests for the different functionality of the
 * profiles api in the micro-services platform.
 * @author Alexandro Blanco <ablanco@caringbridge.org>
 *
 */
class ProfilesTests extends CbBaseSpecification {

    def "get a non-existant profile"(){
        when:
            def resp = sendGetRequestToProfiles('-1')
        then:
            resp.statusCode == HttpStatus.NOT_FOUND
    }
    
    def "get a profile"(){
        when:
            def resp = sendGetRequestToProfiles('2763')
        then:
            resp.statusCode == HttpStatus.OK
            def body = parseStringAsJson(resp.body)
            body.createdAt != null
            body.updatedAt != null
            body.profileId == 2763
            body.firstName == "Dash"
            body.lastName == "Tester"
            body.siteCount == 1
            body.email != null
    }
    
    def "get a profile with bad parameters"(){
        when:
            def resp = sendGetRequestToProfiles('alex')
        then:    
            resp.statusCode == HttpStatus.BAD_REQUEST
    }
    
    def sendGetRequestToProfiles(String profId){
        def url = 'http://vm30-2.caringbridge.org:11003/labs/profiles/'<<profId
        def resp = restTemplates.getRestTemplate().getForEntity(url.toString(),String.class)
        return resp
    }
    
}
