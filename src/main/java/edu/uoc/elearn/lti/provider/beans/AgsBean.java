package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.lti.ags.LineItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author xaracil@uoc.edu
 */
@RequiredArgsConstructor
@Getter
public class AgsBean {
	private final Boolean available;
	private final List<LineItem> lineItems;
}
