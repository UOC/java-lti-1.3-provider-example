package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.deeplinking.DeepLinkingClient;
import edu.uoc.elc.lti.tool.deeplinking.Settings;
import edu.uoc.elc.spring.security.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkCreationResponseBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.elearn.lti.provider.domain.ItemListFactory;
import edu.uoc.elearn.lti.provider.security.UOCContext;
import edu.uoc.elearn.lti.provider.security.UOCUser;
import edu.uoc.lti.deeplink.content.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/deeplink")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class DeepLinkController {

	private static final String VIEW = "deeplink/index";
	private static final String MODEL_NAME = "object";

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(ToolProvider toolProvider) {
		final DeepLinkBean deepLinkBean = prepareResponse(toolProvider);
		deepLinkBean.setDeepLinks(Arrays.asList(createDeepLinkForm(toolProvider)));

		return new ModelAndView(VIEW, MODEL_NAME, deepLinkBean);
	}

	@RequestMapping(value = "/new", params = {"add"})
	public ModelAndView addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		final DeepLinkForm deepLinkForm = createDeepLinkForm(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().add(deepLinkForm);
		return new ModelAndView(VIEW, MODEL_NAME, response);
	}

	@RequestMapping(value = "/new", params = {"remove"})
	public ModelAndView removeItem(DeepLinkBean deepLinkBean, @RequestParam(value = "remove") Integer index, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().remove(index.intValue());
		return new ModelAndView(VIEW, MODEL_NAME, response);
	}

	@RequestMapping(value = "/new", params = {"save"})
	public ModelAndView save(final DeepLinkBean deepLinkBean, ToolProvider toolProvider) throws IOException {
		final List<Item> items = createItemList(deepLinkBean);
		final DeepLinkingClient deepLinkingClient = getDeepLinkingClient(toolProvider);
		final DeepLinkCreationResponseBean responseBean = createItems(items, deepLinkingClient);
		return new ModelAndView("deeplink/response", MODEL_NAME, responseBean);
	}

	private List<Item> createItemList(final DeepLinkBean deepLinkBean) {
		ItemListFactory itemListFactory = new ItemListFactory();
		return itemListFactory.itemsFor(deepLinkBean.getDeepLinks());
	}

	private DeepLinkingClient getDeepLinkingClient(ToolProvider toolProvider) {
		return toolProvider.getDeepLinkingClient();
	}

	private DeepLinkCreationResponseBean createItems(List<Item> items, DeepLinkingClient deepLinkingClient) throws IOException {
		addItems(items, deepLinkingClient);
		final String jwt = deepLinkingClient.buildJWT();
		return new DeepLinkCreationResponseBean(jwt, deepLinkingClient.getReturnUrl());
	}

	private void addItems(List<Item> items, DeepLinkingClient deepLinkingClient) {
		for (Item item : items) {
			deepLinkingClient.addItem(item);
		}
	}

	private DeepLinkForm createDeepLinkForm(ToolProvider toolProvider) {
		final Settings settings = toolProvider.getDeepLinkingSettings();
		final DeepLinkForm deepLinkForm = new DeepLinkForm();
		deepLinkForm.setTitle(settings.getTitle());
		deepLinkForm.setText(settings.getText());
		return deepLinkForm;
	}

	private DeepLinkBean prepareResponse(ToolProvider toolProvider) {
		final Settings settings = toolProvider.getDeepLinkingSettings();
		final DeepLinkBean deepLinkBean = new DeepLinkBean();
		deepLinkBean.setSettings(settings);
		return deepLinkBean;
	}
}
