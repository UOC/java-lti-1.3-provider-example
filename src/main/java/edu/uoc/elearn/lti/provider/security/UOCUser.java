package edu.uoc.elearn.lti.provider.security;

import edu.uoc.elc.spring.lti.security.User;
import lombok.extern.slf4j.Slf4j;

/**
 * UserDetails from LTI context data. Includes Course information
 *
 * @author xaracil@uoc.edu
 */
@Slf4j
public class UOCUser extends User {

	public UOCUser(User user) {
		super(user.getUsername(), "N.A.", user.getTool(), user.getAuthorities());
	}

	// UOC specific methods
	public String getUserId() {
		return (String) getCustomParameter("id");
	}

	public String getUserNumber() {
		return (String) getCustomParameter("usernumber");
	}

	public String getUserName() {
		return (String) getCustomParameter("username");
	}

	public String getCampusSessionId() {
		return (String) getCustomParameter("sessionid");

	}
}
