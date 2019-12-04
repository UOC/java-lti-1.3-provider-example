package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.lti.ags.LineItem;

/**
 * @author xaracil@uoc.edu
 */
public class LineItemFactory {
	public LineItem newLineItem(String label, Double maxScore, String tag) {
		final LineItem lineItem = new LineItem();
		lineItem.setLabel(label);
		lineItem.setScoreMaximum(maxScore);
		lineItem.setTag(tag);
		return lineItem;
	}
}
