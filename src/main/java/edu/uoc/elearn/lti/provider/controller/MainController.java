package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.IndexBean;
import edu.uoc.elearn.lti.provider.beans.NamesRoleBean;
import edu.uoc.elearn.lti.provider.service.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.MemberVisitor;
import edu.uoc.lti.ags.LineItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class MainController {

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView init(User user, Context context, ToolProvider toolProvider) {
		final IndexBean response = generateResponseObject(user, context, toolProvider);
		return new ModelAndView("index.html", "object", response);
	}

	private IndexBean generateResponseObject(User user, Context context, ToolProvider toolProvider) {
		final List<String> roles = user.getRoles();
		final NamesRoleBean namesRolesServiceInfo = createBeanWithNamesRoleServiceInfo(toolProvider);
		final AgsBean assignmentAndGradesServiceInfo = createBeanWithAssignmentAndGradesServiceInfo(toolProvider);
		return new IndexBean(user, context, roles, namesRolesServiceInfo, assignmentAndGradesServiceInfo);
	}

	private NamesRoleBean createBeanWithNamesRoleServiceInfo(ToolProvider toolProvider) {
		final MemberVisitor memberVisitor = new MemberVisitor(toolProvider);
		final Boolean available = memberVisitor.canGet();
		final List<Member> members = memberVisitor.getAll();
		return new NamesRoleBean(available, members);
	}

	private AgsBean createBeanWithAssignmentAndGradesServiceInfo(ToolProvider toolProvider) {
		final LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		final Boolean available = lineItemVisitor.canGet();
		final List<LineItem> lineItems = lineItemVisitor.getAll();
		return new AgsBean(available, lineItems);
	}
}
