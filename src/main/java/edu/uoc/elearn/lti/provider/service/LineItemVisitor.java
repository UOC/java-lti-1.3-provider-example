package edu.uoc.elearn.lti.provider.service;

import edu.uoc.lti.ags.LineItem;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface LineItemVisitor {
	boolean canGet();
	List<LineItem> getAll();
}
