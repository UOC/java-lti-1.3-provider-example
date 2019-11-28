package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.*;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public class LtiResourceItemFactory implements ItemFactory {
	@Override
	public Item itemFor(DeepLinkForm deepLinkForm) {
		LtiResourceItem item = LtiResourceItem.builder()
						.title(deepLinkForm.getTitle())
						.url(deepLinkForm.getUrl())
						.build();

		item = setDocumentTarget(deepLinkForm.getDocumentTarget(), item);

		return item;
	}

	private LtiResourceItem setDocumentTarget(String documentTarget, LtiResourceItem item) {
		final DocumentTargetEnum documentTargetEnum = DocumentTargetEnum.valueOf(documentTarget);
		if (documentTargetEnum == DocumentTargetEnum.iframe) {
			item.setIFrame(IFrame.builder()
							.url(item.getUrl())
							.build());
		}
		if (documentTargetEnum == DocumentTargetEnum.window) {
			item.setWindow(Window.builder().build());
		}
		return item;
	}
}
