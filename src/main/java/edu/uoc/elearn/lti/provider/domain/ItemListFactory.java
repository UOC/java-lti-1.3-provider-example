package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.lti.deeplink.content.Item;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xaracil@uoc.edu
 */
@RequiredArgsConstructor
public class ItemListFactory {

	public List<Item> itemsFor(List<DeepLinkForm> deepLinkForms) {
		return deepLinkForms.stream().map(this::itemFor).collect(Collectors.toList());
	}

	private Item itemFor(DeepLinkForm deepLinkForm) {
		ItemFactory itemFactory = getItemFactory(deepLinkForm.getType());
		return itemFactory.itemFor(deepLinkForm);
	}

	private ItemFactory getItemFactory(String type) {
		if ("link".equals(type)) {
			return new LinkItemFactory();
		}

		if ("file".equals(type)) {
			return new FileItemFactory();
		}

		if ("html".equals(type)) {
			return new HtmlItemFactory();
		}

		if ("ltiResourceLink".equals(type)) {
			return new LtiResourceItemFactory();
		}

		return null;
	}

}
