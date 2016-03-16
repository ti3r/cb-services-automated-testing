# cb-services-automated-testing

A project to automatically test the different micro-services applications. This project uses groovy & spock 
to make different rest calls to the microservices located in one environment and test its capabilities.

More information can be found on wiki article [Micro Services Automated Testing](http://wiki.cbeagan.org/display/~ddurham/Micro+Services+automated+testing)

### How to run

Integration test classes can be executed using any IDE using using junit runner. In eclipse is a simple as opening 
one class then right click -> run as -> junit test (other IDEs should provide similar functionality). 


In order to run the project using gradle one can execute the following command
`gradle clean integrationTest`
If only one test class is needed to be executed the following command can be invoked
`gradle clean integrationTest -DintegrationTest.single=<className>`
ex:
`gradle clean integrationTest -DintegrationTest.single=AuditsTests`
 