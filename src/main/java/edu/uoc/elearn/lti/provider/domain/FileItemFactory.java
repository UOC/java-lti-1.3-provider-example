package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.FileItem;
import edu.uoc.lti.deeplink.content.Item;

/**
 * @author xaracil@uoc.edu
 */
public class FileItemFactory implements ItemFactory {
	@Override
	public Item itemFor(DeepLinkForm deepLinkForm) {
		final FileItem item = FileItem.builder()
						.title(deepLinkForm.getTitle())
						.url(deepLinkForm.getUrl())
						.mediaType(deepLinkForm.getMediaType())
						.build();

		item.setText(deepLinkForm.getText());
		return item;
	}
}
