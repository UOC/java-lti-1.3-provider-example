package edu.uoc.elearn.lti.provider.config;

import edu.uoc.elc.spring.lti.ags.RestTemplateFactory;
import edu.uoc.lti.accesstoken.AccessTokenRequestBuilder;
import edu.uoc.lti.accesstoken.UrlEncodedFormAccessTokenRequestBuilderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xaracil@uoc.edu
 */
@Configuration
@ComponentScan(value = {"edu.uoc.elc.spring.lti.tool.builders"})
public class LTIConfig {
	@Bean
	public AccessTokenRequestBuilder accessTokenRequestBuilder() {
		return new UrlEncodedFormAccessTokenRequestBuilderImpl();
	}

	@Bean
	public RestTemplateFactory restTemplateFactory() {
		return new RestTemplateFactory();
	}
}
