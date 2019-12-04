package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.ToolProvider;

/**
 * @author xaracil@uoc.edu
 */
public interface MainResolver {
	Object getRecap(User user, Context context, ToolProvider toolProvider);
}
