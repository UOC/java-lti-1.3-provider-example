package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface DeepLinkResolver {
	Object getMainInfo(ToolProvider toolProvider);
	Object addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider);
	Object removeItemAt(DeepLinkBean deepLinkBean, Integer index, ToolProvider toolProvider);
	Object save(DeepLinkBean deepLinkBean, ToolProvider toolProvider);
}
