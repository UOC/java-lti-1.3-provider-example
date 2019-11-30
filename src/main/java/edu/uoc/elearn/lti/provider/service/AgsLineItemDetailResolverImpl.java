package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.LineItemBean;
import edu.uoc.elearn.lti.provider.domain.LineItemFactory;
import edu.uoc.elearn.lti.provider.service.ags.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.ags.MemberVisitor;
import edu.uoc.elearn.lti.provider.service.ags.ResultsVisitor;
import edu.uoc.lti.ags.LineItem;
import edu.uoc.lti.ags.Result;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Service
public class AgsLineItemDetailResolverImpl implements AgsLineItemDetailResolver {
	@Override
	public LineItemBean get(String id, ToolProvider toolProvider) {
		final LineItem lineItem = getLineItem(id, toolProvider);
		final List<Result> results = getResultsOfLineItem(id, toolProvider);
		final List<Member> members = getMembers(toolProvider);
		return new LineItemBean(lineItem, members, results);
	}

	private LineItem getLineItem(String id, ToolProvider toolProvider) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.get(id);
	}

	private List<Result> getResultsOfLineItem(String id, ToolProvider toolProvider) {
		ResultsVisitor resultsVisitor = new ResultsVisitor(toolProvider);
		return resultsVisitor.getAll(id);
	}

	private List<Member> getMembers(ToolProvider toolProvider) {
		final MemberVisitor memberVisitor = new MemberVisitor(toolProvider);
		return memberVisitor.getAll();
	}
}
