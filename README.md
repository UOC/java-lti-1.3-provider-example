# Java LTI 1.3 Example

This is an working example webapp for a LTI Advantage Tool using the LTI related libraries of UOC.

It has a main controller, which displays general info about the LTI launch:

* User
* Context
* Which services are available
* List of names and roles of the context if the NameRoleService is available
* Access to Assignment and Grade service (see below)

Also has a DeepLink controller (`edu.uoc.elearn.lti.provider.controller.DeepLinkController`) for managing
DeepLinking launches.

Last but not least, it has an Assignment and Grade Service client for managing LineItems, viewing LineItems 
results and Score users to line items. 

## About

The application is very simple. Controllers are in the `edu.uoc.elearn.lti.provider.controller` package.
Configuration of the LTI Tool is made as follows:

* Beans with tool's implementations are defined in the `edu.uoc.elearn.lti.provider.config.LTIConfig` 
class (See https://github.com/UOC/java-lti-1.3-core and https://github.com/UOC/java-lti-1.3-jwt for more info)

* Tool definition is made using parameters from `application.properties` and `application-profile.yml` (for each
spring profile). 

    You'll notice that these files has values. It has values for [IMS reference implementation](https://lti-ri.imsglobal.org)
    and [IMS Certification](https://ltiadvantagevalidator.imsglobal.org/ltiadv/index.html).
 
    Keys defined in `application.properties` refers to the UOC's tool and platform in [IMS reference implementation](https://lti-ri.imsglobal.org) 
    and can be used. 

## Install

You'll need to set your maven installation to work with Github packages, following https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-apache-maven-for-use-with-github-packages#authenticating-to-github-packages

  Repository info:
  
  ```xml
        <repository>
          <id>github-uoc</id>
          <name>GitHub UOC Apache Maven Packages</name>
          <url>https://maven.pkg.github.com/uoc</url>
        </repository>				
  ```

Once set, install it using maven:
      
```bash
./mvnw install
```

Or run using:

```bash
./mvnw spring-boot:run
```

## See also
* https://github.com/UOC/java-lti-1.3-core
* https://github.com/UOC/java-lti-1.3-jwt
* https://github.com/UOC/java-lti-1.3
* https://github.com/UOC/spring-boot-lti-advantage

