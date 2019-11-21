# LTI 1.3

Branch `lti1.3` has a working example of a webapp which is LTI 1.3 provider
 
## Docker

create and deploy docker image with 

```
docker build -t devel.elearnlab.org:4567/elearn/springbootltiprovider/lti-13-provider-test-webapp .
docker push devel.elearnlab.org:4567/elearn/springbootltiprovider/lti-13-provider-test-webapp
```