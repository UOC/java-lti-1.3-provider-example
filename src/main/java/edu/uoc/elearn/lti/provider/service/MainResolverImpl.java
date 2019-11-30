package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.security.Context;
import edu.uoc.elc.spring.lti.security.User;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.IndexBean;
import edu.uoc.elearn.lti.provider.beans.NamesRoleBean;
import edu.uoc.elearn.lti.provider.service.ags.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.ags.MemberVisitor;
import edu.uoc.lti.ags.LineItem;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public class MainResolverImpl implements MainResolver {
	@Override
	public IndexBean getRecap(User user, Context context, ToolProvider toolProvider) {
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
