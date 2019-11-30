package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.LineItemBean;
import edu.uoc.elearn.lti.provider.domain.LineItemFactory;
import edu.uoc.elearn.lti.provider.domain.ScoreFactory;
import edu.uoc.elearn.lti.provider.service.ags.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.ags.MemberVisitor;
import edu.uoc.elearn.lti.provider.service.ags.ResultsVisitor;
import edu.uoc.elearn.lti.provider.service.ags.ScoreVisitor;
import edu.uoc.lti.ags.LineItem;
import edu.uoc.lti.ags.Result;
import edu.uoc.lti.ags.Score;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Service
public class AgsResolverImpl implements AgsResolver {

	@Override
	public AgsBean list(ToolProvider toolProvider, String tag) {
		final List<LineItem> lineItems = getExistingLineItemsForTool(toolProvider, tag);
		return new AgsBean(true, lineItems);
	}

	private List<LineItem> getExistingLineItemsForTool(ToolProvider toolProvider, String tag) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.getAll(null, null, tag, null);
	}

	@Override
	public void createLineItem(String label, Double maxScore, ToolProvider toolProvider, String tag) {
		final LineItem lineItem = createObject(label, maxScore, tag);
		saveInPlatform(lineItem, toolProvider);
	}

	private LineItem createObject(String label, Double maxScore, String tag) {
		LineItemFactory lineItemFactory = new LineItemFactory();
		return lineItemFactory.newLineItem(label, maxScore, tag);
	}

	private LineItem saveInPlatform(LineItem lineItem, ToolProvider toolProvider) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.create(lineItem);
	}

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

	@Override
	public boolean score(String id, String userId, Double score, String comment, ToolProvider toolProvider) {
		final Score scoreObject = createScoreObject(userId, score, comment);
		saveScoreInPlatform(id, scoreObject, toolProvider);
		return false;
	}

	private Score createScoreObject(String userId, Double score, String comment) {
		ScoreFactory scoreFactory = new ScoreFactory();
		return scoreFactory.from(userId, score, comment);
	}

	private void saveScoreInPlatform(String id, Score score, ToolProvider toolProvider) {
		final ScoreVisitor scoreVisitor = new ScoreVisitor(toolProvider);
		final boolean scored = scoreVisitor.score(id, score);
	}
}
