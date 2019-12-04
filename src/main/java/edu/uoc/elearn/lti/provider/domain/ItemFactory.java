package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.Item;

/**
 * @author xaracil@uoc.edu
 */
public interface ItemFactory {
	Item itemFor(DeepLinkForm deepLinkForm);
}
