package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.lti.platform.ags.ToolLineItemServiceClient;
import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.AgsServiceProvider;
import edu.uoc.elc.spring.lti.tool.NamesRoleServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.IndexBean;
import edu.uoc.lti.ags.LineItem;
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
public class MainController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(User user, Context context, ToolProvider toolProvider) throws URISyntaxException {
		final IndexBean response = generateResponseObject(user, context, toolProvider);
		return new ModelAndView("index.html", "object", response);
	}

	private IndexBean generateResponseObject(User user, Context context, ToolProvider toolProvider) throws URISyntaxException {
		final List<String> roles = user.getRoles();
		final Boolean hasNamesRoleService = hasNamesRoleService(toolProvider);
		final List<Member> members = getMembers(toolProvider);
		final Boolean hasAgsService = hasAgsService(toolProvider);
		final List<LineItem> lineItems = getLineItems(toolProvider);
		return new IndexBean(user, context, roles, hasNamesRoleService, members, hasAgsService, lineItems);
	}

	private Boolean hasAgsService(ToolProvider toolProvider) {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		return agsServiceProvider.hasAgsService();
	}

	private Boolean hasNamesRoleService(ToolProvider toolProvider) {
		final NamesRoleServiceProvider namesRoleServiceProvider = toolProvider.getNamesRoleServiceProvider();
		return namesRoleServiceProvider.hasNameRoleService();
	}

	private List<Member> getMembers(ToolProvider toolProvider) throws URISyntaxException {
		final NamesRoleServiceProvider namesRoleServiceProvider = toolProvider.getNamesRoleServiceProvider();
		return namesRoleServiceProvider.getMembers();
	}

	private List<LineItem> getLineItems(ToolProvider toolProvider) {
		final AgsServiceProvider agsServiceProvider = toolProvider.getAgsServiceProvider();
		final ToolLineItemServiceClient lineItemsServiceClient = agsServiceProvider.getLineItemsServiceClient();
		return lineItemsServiceClient.getLineItems(null, null, null, null);
	}
}
