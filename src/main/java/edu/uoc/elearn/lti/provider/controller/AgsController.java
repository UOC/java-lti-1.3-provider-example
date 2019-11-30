package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.ags.ToolLineItemServiceClient;
import edu.uoc.elc.spring.lti.tool.AgsServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.domain.LineItemFactory;
import edu.uoc.lti.ags.LineItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/ags")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class AgsController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(ToolProvider toolProvider) {
		final ToolLineItemServiceClient lineItemServiceClient = getLineItemServiceClient(toolProvider);
		final List<LineItem> lineItems = lineItemServiceClient.getLineItems(null, null, "UOC-TEST", null);
		AgsBean agsBean = new AgsBean(lineItems);
		return new ModelAndView("ags/index", "object", agsBean);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createLineItem(String label, Double maxScore, ToolProvider toolProvider) {
		final LineItem lineItem = createObject(label, maxScore);
		final ToolLineItemServiceClient lineItemServiceClient = getLineItemServiceClient(toolProvider);
		saveInPlatform(lineItem, lineItemServiceClient);
		return "redirect:/ags";
	}

	private LineItem createObject(String label, Double maxScore) {
		LineItemFactory lineItemFactory = new LineItemFactory();
		return lineItemFactory.newLineItem(label, maxScore, "UOC-TEST");
	}

	private ToolLineItemServiceClient getLineItemServiceClient(ToolProvider toolProvider) {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		return agsServiceProvider.getLineItemsServiceClient();
	}

	private LineItem saveInPlatform(LineItem lineItem, ToolLineItemServiceClient lineItemServiceClient) {
		return lineItemServiceClient.createLineItem(lineItem);
	}


}
