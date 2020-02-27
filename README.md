# link-shortener-gradle-webapp

## Description
Sample link shortener web application, built with Spring Boot and Gradle. This uses Spring WebFlux backed by a Redis backend to store/retrieve keys for shortened links.
This is based on the YouTube tutorial https://www.youtube.com/watch?v=KrxXdnCxiFg  

## Endpoints
### POST /link
#### Request

```json
     {
       "link": "https://someDomain.com"
     }
```
#### Response
```json
     {
        "shortenedLink": "http://localhost:8080/<KEY>"
     }
```
###GET /\<KEY\>
#### Response
Returns 308 (Permanent Redirect) or 404 (Not found) if the submitted KEY has not been mapped to a URL.

## NOTES
* Docker must be installed on the local machine to execute all the the test cases successfully. I have docker-toolbox installed.
* Uses a reactive programming model to avoid request blocking each other.
* Leverages testcontainers-spring-boot project (https://github.com/testcontainers/testcontainers-spring-boot) which is an extension of the general Testcontainers project. (https://www.testcontainers.org/) 
* The project testcontainers-spring-boot needs spring-cloud on the test classpath which is why it has been added as a test dependency.
* The app.baseUrl key in application.properties could be customized using a different Spring profile for each deployable environment. 

