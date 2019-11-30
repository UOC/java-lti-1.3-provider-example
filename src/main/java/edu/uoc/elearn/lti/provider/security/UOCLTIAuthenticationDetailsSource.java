package edu.uoc.elearn.lti.provider.security;

import edu.uoc.elc.spring.lti.security.LTIAuthenticationDetailsSource;
import edu.uoc.elc.spring.lti.tool.ToolDefinitionBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * AuthenticationDetailsSource from LTI
 *
 * @author xaracil@uoc.edu
 */

@ConfigurationProperties(prefix = "uoc.lti")
public class UOCLTIAuthenticationDetailsSource extends LTIAuthenticationDetailsSource {

	private List<String> adminUsers;
	private List<String> adminDomainCodes;

	public UOCLTIAuthenticationDetailsSource() {
		this(null, null, null);
	}

	public UOCLTIAuthenticationDetailsSource(ToolDefinitionBean toolDefinitionBean, List<String> adminUsers, List<String> adminDomainCodes) {
		super(toolDefinitionBean);
		this.adminUsers = adminUsers;
		this.adminDomainCodes = adminDomainCodes;
	}

	protected Collection<String> getUserRoles(HttpServletRequest request) {
		final Collection<String> userRoles = super.getUserRoles(request);

		// UOC specific stuff
		if (getTool().isLearner() && !getTool().isInstructor()) {
			userRoles.add("STUDENT");
		}
		if (getTool().isInstructor()) {
			String specificRolesParam = (String) getTool().getCustomParameter("specific_role");
			List<String> specificRoles = specificRolesParam != null ? Arrays.asList(specificRolesParam.split(",")) : null;
			if ("TUTORIA".equals(getTool().getCustomParameter("domain_typeid"))) {
				userRoles.add("TUTOR");

				// check if we're assigned as ADMINISTRACIO
				if (specificRoles != null && specificRoles.contains("ADMINISTRACIO")) {
					userRoles.add("TUTOR_ADMIN");
				}
			} else {

				// PRA
				if (specificRoles != null && specificRoles.contains("CREADOR")) {
					userRoles.add("PRA");
				} else {
					userRoles.add("INSTRUCTOR");
				}
			}
		}

		if (isAdmin(getTool().getUser().getId(), (String) getTool().getCustomParameter("username"), (String) getTool().getCustomParameter("domain_code"))) {
				userRoles.add("ADMIN");
		}

		return userRoles;
	}

	private boolean isAdmin(String userName, String customUserName, String domainCode) {
		// super admin case
		if ("admin".equals(userName) || "admin".equals(customUserName)) {
			return true;
		}

		if (this.adminDomainCodes != null) {
			if (this.adminDomainCodes.contains(domainCode)) {
				return true;
			}
			;
		}
		if (this.adminUsers != null) {
			return this.adminUsers.contains(userName) || this.adminUsers.contains(customUserName);
		}

		return false;
	}
}
