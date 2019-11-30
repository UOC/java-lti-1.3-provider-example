package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.LineItemBean;
import edu.uoc.elearn.lti.provider.service.AgsResolver;
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
@RequestMapping("/ags")
@Slf4j
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class AgsController {
	private final static String TAG = "UOC-TEST";
	private final AgsResolver agsResolver;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(ToolProvider toolProvider) {
		AgsBean agsBean = agsResolver.list(toolProvider, TAG);
		return new ModelAndView("ags/index", "object", agsBean);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createLineItem(String label, Double maxScore, ToolProvider toolProvider) {
		agsResolver.createLineItem(label, maxScore, toolProvider, TAG);
		return "redirect:/ags";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/view")
	public ModelAndView index(@RequestParam("id") String id, ToolProvider toolProvider) {
		final LineItemBean lineItemBean = agsResolver.get(id, toolProvider);
		return new ModelAndView("ags/view", "object", lineItemBean);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/score")
	public String score(@RequestParam("id") String id, String userId, Double score, String comment, ToolProvider toolProvider) {
		final boolean scored = agsResolver.score(id, userId, score, comment, toolProvider);
		if (!scored) {
			throw new RuntimeException("Scored failed!!!");
		}
		return "redirect:/ags/view?id=" + id;
	}
}
