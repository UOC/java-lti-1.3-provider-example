package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.LineItemBean;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface AgsResolver {
	AgsBean list(ToolProvider toolProvider, String tag);
	void createLineItem(String label, Double maxScore, ToolProvider toolProvider, String tag);
	LineItemBean get(String id, ToolProvider toolProvider);
}
