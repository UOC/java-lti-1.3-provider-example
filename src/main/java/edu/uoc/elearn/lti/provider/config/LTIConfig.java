package edu.uoc.elearn.lti.provider.config;

import edu.uoc.elc.spring.lti.ags.RestTemplateFactory;
import edu.uoc.elc.spring.lti.tool.BasicToolDefinition;
import edu.uoc.lti.accesstoken.AccessTokenRequestBuilder;
import edu.uoc.lti.accesstoken.UrlEncodedFormAccessTokenRequestBuilderImpl;
import edu.uoc.lti.claims.ClaimAccessor;
import edu.uoc.lti.clientcredentials.ClientCredentialsTokenBuilder;
import edu.uoc.lti.deeplink.DeepLinkingTokenBuilder;
import edu.uoc.lti.jwt.claims.JWSClaimAccessor;
import edu.uoc.lti.jwt.client.JWSClientCredentialsTokenBuilder;
import edu.uoc.lti.jwt.deeplink.JWSTokenBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Configuration
public class LTIConfig {
	@Bean
	public ClaimAccessor claimAccessor(BasicToolDefinition basicToolDefinition) {
		return new JWSClaimAccessor(basicToolDefinition.getKeySetUrl());
	}

	@Bean
	public DeepLinkingTokenBuilder deepLinkingTokenBuilder(BasicToolDefinition basicToolDefinition) {
		return new JWSTokenBuilder(basicToolDefinition.getPublicKey(), basicToolDefinition.getPrivateKey());
	}

	@Bean
	public ClientCredentialsTokenBuilder clientCredentialsTokenBuilder(BasicToolDefinition basicToolDefinition) {
		return new JWSClientCredentialsTokenBuilder(basicToolDefinition.getPublicKey(), basicToolDefinition.getPrivateKey());
	}

	@Bean
	public AccessTokenRequestBuilder accessTokenRequestBuilder() {
		return new UrlEncodedFormAccessTokenRequestBuilderImpl();
	}

	@Bean
	public RestTemplateFactory restTemplateFactory() {
		return new RestTemplateFactory();
	}
}
