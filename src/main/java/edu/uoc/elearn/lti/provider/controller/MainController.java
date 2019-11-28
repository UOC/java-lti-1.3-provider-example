package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.security.lti.tool.NamesRoleServiceProvider;
import edu.uoc.elc.spring.security.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.IndexBean;
import edu.uoc.elearn.lti.provider.security.UOCContext;
import edu.uoc.elearn.lti.provider.security.UOCUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class MainController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(UOCUser user, UOCContext context, ToolProvider toolProvider) {
		final IndexBean response = generateResponseObject(user, context, toolProvider);
		return new ModelAndView("index.html", "object", response);
	}

	private IndexBean generateResponseObject(UOCUser user, UOCContext context, ToolProvider toolProvider) {
		final List<String> roles = user.getRoles();
		final List<Member> members = getMembers(toolProvider);
		return new IndexBean(user, context, roles, members);
	}

	private List<Member> getMembers(ToolProvider toolProvider) {
		final NamesRoleServiceProvider namesRoleServiceProvider = toolProvider.getNamesRoleServiceProvider();
		return namesRoleServiceProvider.getMembers();
	}
	/**
	 * Renders a home with requested data
	 *
	 * @return
	 */
	private String render_home(UOCUser user, UOCContext context, ToolProvider toolProvider) throws IOException {

		StringBuilder ret = new StringBuilder("<h1>Parameters:</h1>");

		// add line items
		/*
		ret.append("<h2>Line Items</h2>");
		final AgsClient assignmentAndGradeService = toolProvider.getAssignmentAndGradeService();
		if (!assignmentAndGradeService.canReadLineItems()) {
			ret.append("<p><strong>Can't read Line Items</p>");
		} else {
			final List<LineItem> lineItems = assignmentAndGradeService.getLineItems(null, null, null, null, null);
			ret.append("<ul>");
			for (LineItem lineItem : lineItems) {
				ret.append("<li>" + lineItem.getId() + ":" + lineItem.toString() +"></li>");
			}
			ret.append("</ul>");
		}
		 */

		return ret.toString();
	}
}
