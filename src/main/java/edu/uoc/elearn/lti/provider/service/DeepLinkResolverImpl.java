package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.deeplinking.DeepLinkingClient;
import edu.uoc.elc.lti.tool.deeplinking.Settings;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkCreationResponseBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.elearn.lti.provider.domain.ItemListFactory;
import edu.uoc.lti.deeplink.content.Item;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Service
public class DeepLinkResolverImpl implements DeepLinkResolver {
	@Override
	public DeepLinkBean getMainInfo(ToolProvider toolProvider) {
		final DeepLinkBean deepLinkBean = prepareResponse(toolProvider);
		deepLinkBean.setDeepLinks(Arrays.asList(createDeepLinkForm(toolProvider)));
		return deepLinkBean;
	}

	private DeepLinkBean prepareResponse(ToolProvider toolProvider) {
		final Settings settings = toolProvider.getDeepLinkingSettings();
		final DeepLinkBean deepLinkBean = new DeepLinkBean();
		deepLinkBean.setSettings(settings);
		return deepLinkBean;
	}

	private DeepLinkForm createDeepLinkForm(ToolProvider toolProvider) {
		final Settings settings = toolProvider.getDeepLinkingSettings();
		final DeepLinkForm deepLinkForm = new DeepLinkForm();
		deepLinkForm.setTitle(settings.getTitle());
		deepLinkForm.setText(settings.getText());
		return deepLinkForm;
	}

	@Override
	public DeepLinkBean addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		final DeepLinkForm deepLinkForm = createDeepLinkForm(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().add(deepLinkForm);
		return response;
	}

	@Override
	public DeepLinkBean removeItemAt(DeepLinkBean deepLinkBean, Integer index, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().remove(index.intValue());
		return response;
	}

	@Override
	public DeepLinkCreationResponseBean save(DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final List<Item> items = createItemList(deepLinkBean);
		final DeepLinkingClient deepLinkingClient = getDeepLinkingClient(toolProvider);
		return createItems(items, deepLinkingClient);
	}

	private List<Item> createItemList(final DeepLinkBean deepLinkBean) {
		ItemListFactory itemListFactory = new ItemListFactory();
		return itemListFactory.itemsFor(deepLinkBean.getDeepLinks());
	}

	private DeepLinkingClient getDeepLinkingClient(ToolProvider toolProvider) {
		return toolProvider.getDeepLinkingClient();
	}

	private DeepLinkCreationResponseBean createItems(List<Item> items, DeepLinkingClient deepLinkingClient) {
		addItems(items, deepLinkingClient);
		final String jwt = deepLinkingClient.buildJWT();
		return new DeepLinkCreationResponseBean(jwt, deepLinkingClient.getReturnUrl());
	}

	private void addItems(List<Item> items, DeepLinkingClient deepLinkingClient) {
		items.stream().forEach(item -> deepLinkingClient.addItem(item));
	}
}
