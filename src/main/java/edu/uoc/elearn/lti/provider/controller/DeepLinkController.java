package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.tool.deeplinking.Settings;
import edu.uoc.elc.spring.security.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.DeepLinkBean;
import edu.uoc.elearn.lti.provider.beans.DeepLinkForm;
import edu.uoc.elearn.lti.provider.security.UOCContext;
import edu.uoc.elearn.lti.provider.security.UOCUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/deeplink")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class DeepLinkController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(UOCUser user, UOCContext context, ToolProvider toolProvider) {
		final DeepLinkBean deepLinkBean = prepareResponse(toolProvider);
		deepLinkBean.setDeepLinks(Arrays.asList(createDeepLinkForm(toolProvider)));

		return new ModelAndView("deeplink", "object", deepLinkBean);
	}

	@RequestMapping(value = "/new", params = {"add"})
	public ModelAndView addItem(DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		final DeepLinkForm deepLinkForm = createDeepLinkForm(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().add(deepLinkForm);
		return new ModelAndView("deeplink", "object", response);
	}

	@RequestMapping(value = "/new", params = {"remove"})
	public ModelAndView removeItem(DeepLinkBean deepLinkBean, @RequestParam(value = "remove") Integer index, ToolProvider toolProvider) {
		final DeepLinkBean response = prepareResponse(toolProvider);
		response.setDeepLinks(deepLinkBean.getDeepLinks());
		response.getDeepLinks().remove(index.intValue());
		return new ModelAndView("deeplink", "object", response);
	}

	@RequestMapping(value = "/new", params = {"save"})
	public String save(final DeepLinkBean deepLinkBean, ToolProvider toolProvider) {
		// TODO: save!!!
		return null;
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
