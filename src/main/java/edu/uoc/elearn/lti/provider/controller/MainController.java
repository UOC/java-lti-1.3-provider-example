package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.service.MainResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static edu.uoc.elearn.lti.provider.controller.Paths.MAIN;

@Controller
@RequestMapping(MAIN)
@Slf4j
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class MainController {
	private final MainResolver mainResolver;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(User user, Context context, ToolProvider toolProvider) {
		final Object response = mainResolver.getRecap(user, context, toolProvider);
		return new ModelAndView("index.html", "object", response);
	}
}
