package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.spring.lti.tool.ToolProvider;
import edu.uoc.elearn.lti.provider.beans.AgsBean;
import edu.uoc.elearn.lti.provider.domain.LineItemFactory;
import edu.uoc.elearn.lti.provider.service.ags.LineItemVisitor;
import edu.uoc.lti.ags.LineItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Service
public class AgsLineItemsResolverImpl implements AgsLineItemsResolver {

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
}
