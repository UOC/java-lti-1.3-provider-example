package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.service.AgsLineItemsResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static edu.uoc.elearn.lti.provider.controller.Paths.AGS;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping(AGS)
@Slf4j
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class AgsLineItemsController {
	private final static String TAG = "UOC-TEST";
	private final AgsLineItemsResolver agsLineItemsResolver;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(ToolProvider toolProvider) {
		Object resolvedObject = agsLineItemsResolver.list(toolProvider, TAG);
		return new ModelAndView("ags/index", "object", resolvedObject);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createLineItem(String label, Double maxScore, ToolProvider toolProvider) {
		agsLineItemsResolver.createLineItem(label, maxScore, toolProvider, TAG);
		return "redirect:/ags";
	}
}
