package edu.uoc.elearn.lti.provider.controller;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.beans.LineItemBean;
import edu.uoc.elearn.lti.provider.domain.LineItemFactory;
import edu.uoc.elearn.lti.provider.domain.ScoreFactory;
import edu.uoc.elearn.lti.provider.service.LineItemVisitor;
import edu.uoc.elearn.lti.provider.service.MemberVisitor;
import edu.uoc.elearn.lti.provider.service.ResultsVisitor;
import edu.uoc.elearn.lti.provider.service.ScoreVisitor;
import edu.uoc.lti.ags.LineItem;
import edu.uoc.lti.ags.Result;
import edu.uoc.lti.ags.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Controller
@RequestMapping("/ags")
@Slf4j
@PreAuthorize("isAuthenticated()")
public class AgsController {
	private final static String TAG = "UOC-TEST";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list(ToolProvider toolProvider) {
		final List<LineItem> lineItems = getExistingLineItemsForTool(toolProvider);
		AgsBean agsBean = new AgsBean(true, lineItems);
		return new ModelAndView("ags/index", "object", agsBean);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createLineItem(String label, Double maxScore, ToolProvider toolProvider) {
		final LineItem lineItem = createObject(label, maxScore);
		saveInPlatform(lineItem, toolProvider);
		return "redirect:/ags";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/view")
	public ModelAndView index(@RequestParam("id") String id, ToolProvider toolProvider) {
		final LineItem lineItem = getLineItem(id, toolProvider);
		final List<Result> results = getResultsOfLineItem(id, toolProvider);
		final List<Member> members = getMembers(toolProvider);
		final LineItemBean lineItemBean = new LineItemBean(lineItem, members, results);
		return new ModelAndView("ags/view", "object", lineItemBean);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/score")
	public String score(@RequestParam("id") String id, String userId, Double score, String comment, ToolProvider toolProvider) {
		final Score scoreObject = createScoreObject(userId, score, comment);
		saveScoreInPlatform(id, scoreObject, toolProvider);
		return "redirect:/ags/view?id=" + id;
	}

	private LineItem getLineItem(String id, ToolProvider toolProvider) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.get(id);
	}

	private List<Result> getResultsOfLineItem(String id, ToolProvider toolProvider) {
		ResultsVisitor resultsVisitor = new ResultsVisitor(toolProvider);
		return resultsVisitor.getAll(id);
	}

	private List<LineItem> getExistingLineItemsForTool(ToolProvider toolProvider) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.getAll(null, null, TAG, null);
	}

	private LineItem createObject(String label, Double maxScore) {
		LineItemFactory lineItemFactory = new LineItemFactory();
		return lineItemFactory.newLineItem(label, maxScore, TAG);
	}

	private LineItem saveInPlatform(LineItem lineItem, ToolProvider toolProvider) {
		LineItemVisitor lineItemVisitor = new LineItemVisitor(toolProvider);
		return lineItemVisitor.create(lineItem);
	}

	private List<Member> getMembers(ToolProvider toolProvider) {
		final MemberVisitor memberVisitor = new MemberVisitor(toolProvider);
		return memberVisitor.getAll();
	}

	private Score createScoreObject(String userId, Double score, String comment) {
		ScoreFactory scoreFactory = new ScoreFactory();
		return scoreFactory.from(userId, score, comment);
	}

	private void saveScoreInPlatform(String id, Score score, ToolProvider toolProvider) {
		final ScoreVisitor scoreVisitor = new ScoreVisitor(toolProvider);
		final boolean scored = scoreVisitor.score(id, score);
		if (!scored) {
			throw new RuntimeException("Scored failed!!!");
		}
	}
}
