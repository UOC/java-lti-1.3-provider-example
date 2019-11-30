package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface AgsLineItemDetailResolver {
	Object get(String id, ToolProvider toolProvider);
}
