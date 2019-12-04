package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.HtmlItem;
import edu.uoc.lti.deeplink.content.Item;

/**
 * @author xaracil@uoc.edu
 */
public class HtmlItemFactory implements ItemFactory {
	@Override
	public Item itemFor(DeepLinkForm deepLinkForm) {
		return HtmlItem.builder()
						.html(deepLinkForm.getUrl())
						.text(deepLinkForm.getText())
						.title(deepLinkForm.getTitle())
						.build();
	}
}
