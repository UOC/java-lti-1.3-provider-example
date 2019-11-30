package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.lti.platform.ags.ToolLineItemServiceClient;
import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.AgsServiceProvider;
import edu.uoc.elc.spring.lti.tool.NamesRoleServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.IndexBean;
import edu.uoc.elearn.lti.provider.service.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.MemberVisitor;
import edu.uoc.lti.ags.LineItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class MainController {
	private final MemberVisitor memberVisitor;
	private final LineItemVisitor lineItemVisitor;

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(User user, Context context, ToolProvider toolProvider) throws URISyntaxException {
		final IndexBean response = generateResponseObject(user, context, toolProvider);
		return new ModelAndView("index.html", "object", response);
	}

	private IndexBean generateResponseObject(User user, Context context, ToolProvider toolProvider) throws URISyntaxException {
		final List<String> roles = user.getRoles();
		final Boolean hasNamesRoleService = memberVisitor.canGet();
		final List<Member> members = memberVisitor.getAll();
		final Boolean hasAgsService = lineItemVisitor.canGet();
		final List<LineItem> lineItems = lineItemVisitor.getAll();
		return new IndexBean(user, context, roles, hasNamesRoleService, members, hasAgsService, lineItems);
	}
}
