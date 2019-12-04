package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.*;

/**
 * @author xaracil@uoc.edu
 */
public class LinkItemFactory implements ItemFactory {

	@Override
	public Item itemFor(DeepLinkForm deepLinkForm) {
		LinkItem item = LinkItem.builder()
						.url(deepLinkForm.getUrl())
						.title(deepLinkForm.getTitle())
						.text(deepLinkForm.getText())
						.build();

		item = setDocumentTarget(deepLinkForm.getDocumentTarget(), item);

		return item;
	}

	private LinkItem setDocumentTarget(String documentTarget, LinkItem item) {
		try {
			final DocumentTargetEnum documentTargetEnum = DocumentTargetEnum.valueOf(documentTarget);
			item = setDocumentTarget(documentTargetEnum, item);
		} catch (IllegalArgumentException e) {
			// nothing here
		}
		return item;
	}

	private LinkItem setDocumentTarget(DocumentTargetEnum documentTargetEnum, LinkItem item) {
		if (documentTargetEnum == DocumentTargetEnum.iframe) {
			item.setIframe(IFrame.builder()
							.url(item.getUrl())
							.build());
		}
		if (documentTargetEnum == DocumentTargetEnum.window) {
			item.setWindow(Window.builder().build());
		}
		return item;
	}
}
