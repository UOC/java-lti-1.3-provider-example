package edu.uoc.elearn.lti.provider.security;

import edu.uoc.elc.spring.lti.security.LTIApplicationSecurity;
import edu.uoc.elc.spring.lti.security.LTIProcessingFilter;
import edu.uoc.elc.spring.lti.tool.ToolDefinitionBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(90)
public class UOCLTIApplicationSecurity extends LTIApplicationSecurity {

	public UOCLTIApplicationSecurity(ToolDefinitionBean toolDefinitionBean) {
		super(toolDefinitionBean);
	}

	@Bean
	@ConfigurationProperties(prefix = "uoc.lti.admin.users")
	public List<String> adminUsers() {
		return new ArrayList<>();
	}

	@Bean
	@ConfigurationProperties(prefix = "uoc.lti.admin.domains")
	public List<String> adminDomainCodes() {
		return new ArrayList<>();
	}

	@Override
	protected LTIProcessingFilter getPreAuthFilter() throws Exception {
		final LTIProcessingFilter preAuthFilter = super.getPreAuthFilter();

		preAuthFilter.setAuthenticationDetailsSource(new UOCLTIAuthenticationDetailsSource(getToolDefinitionBean(), adminUsers(), adminDomainCodes()));

		return preAuthFilter;
	}
}