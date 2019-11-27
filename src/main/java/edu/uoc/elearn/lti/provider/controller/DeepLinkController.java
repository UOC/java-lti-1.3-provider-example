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
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/deeplink")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class DeepLinkController {
	public DeepLinkForm setDeepLinkForm() {
		return new DeepLinkForm();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(UOCUser user, UOCContext context, ToolProvider toolProvider) {
		final DeepLinkBean deepLinkBean = prepateFormWithLaunch(toolProvider);
		return new ModelAndView("deeplink", "object", deepLinkBean);
	}

	private DeepLinkBean prepateFormWithLaunch(ToolProvider toolProvider) {
		final Settings settings = toolProvider.getDeepLinkingSettings();
		final DeepLinkForm deepLinkForm = setDeepLinkForm();
		deepLinkForm.setTitle(settings.getTitle());
		deepLinkForm.setText(settings.getText());
		return new DeepLinkBean(settings, deepLinkForm);
	}
}
