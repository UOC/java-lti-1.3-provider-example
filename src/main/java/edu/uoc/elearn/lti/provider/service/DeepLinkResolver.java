package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkCreationResponseBean;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface DeepLinkResolver {
	DeepLinkBean getMainInfo(ToolProvider toolProvider);
	DeepLinkBean addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider);
	DeepLinkBean removeItemAt(DeepLinkBean deepLinkBean, Integer index, ToolProvider toolProvider);
	DeepLinkCreationResponseBean save(DeepLinkBean deepLinkBean, ToolProvider toolProvider);
}
