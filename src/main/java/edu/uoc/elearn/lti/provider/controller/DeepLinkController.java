package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkCreationResponseBean;
import edu.uoc.elearn.lti.provider.service.DeepLinkResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/deeplink")
@Slf4j
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class DeepLinkController {

	private static final String VIEW = "deeplink/index";
	private static final String MODEL_NAME = "object";

	private final DeepLinkResolver deepLinkResolver;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(ToolProvider toolProvider) {
		final DeepLinkBean deepLinkBean = deepLinkResolver.getMainInfo(toolProvider);
		return new ModelAndView(VIEW, MODEL_NAME, deepLinkBean);
	}

	@RequestMapping(value = "/new", params = {"add"})
	public ModelAndView addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final DeepLinkBean response = deepLinkResolver.addItem(deepLinkBean, toolProvider);
		return new ModelAndView(VIEW, MODEL_NAME, response);
	}

	@RequestMapping(value = "/new", params = {"remove"})
	public ModelAndView removeItem(DeepLinkBean deepLinkBean, @RequestParam(value = "remove") Integer index, ToolProvider toolProvider) {
		final DeepLinkBean response = deepLinkResolver.removeItemAt(deepLinkBean, index, toolProvider);
		return new ModelAndView(VIEW, MODEL_NAME, response);
	}

	@RequestMapping(value = "/new", params = {"save"})
	public ModelAndView save(final DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final DeepLinkCreationResponseBean responseBean = deepLinkResolver.save(deepLinkBean, toolProvider);
		return new ModelAndView("deeplink/response", MODEL_NAME, responseBean);
	}
}
