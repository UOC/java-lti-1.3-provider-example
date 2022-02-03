package edu.uoc.elearn.lti.provider.config;

import edu.uoc.elc.spring.lti.security.LTIApplicationSecurity;
import edu.uoc.elc.spring.lti.tool.ToolDefinitionBean;
import edu.uoc.elc.spring.lti.tool.registration.RegistrationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Order(80)
@ComponentScan(value = {"edu.uoc.elc.spring.lti"})
public class ApplicationSecurity extends LTIApplicationSecurity {

	public ApplicationSecurity(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") RegistrationService registrationService,
														 ToolDefinitionBean toolDefinitionBean) {
		super(registrationService, toolDefinitionBean);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		http
						.servletApi()
						.and()

						.authorizeRequests()
						.antMatchers("/css/**").permitAll()
						.antMatchers("/fonts/**").permitAll()
						.antMatchers("/img/**").permitAll()
						.antMatchers("/js/**").permitAll()
						.antMatchers("/login/**").permitAll()
						.antMatchers("/error/**").permitAll()
						.antMatchers("/error").permitAll()
						.antMatchers("/session-expired/**").permitAll()
						.anyRequest().fullyAuthenticated()

						.and()

						.csrf().disable();

	}
}