package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;

/**
 * @author xaracil@uoc.edu
 */
public interface AgsLineItemsResolver {
	Object list(ToolProvider toolProvider, String tag);
	void createLineItem(String label, Double maxScore, ToolProvider toolProvider, String tag);
}
