package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.lti.ags.LineItem;
import edu.uoc.lti.ags.Result;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author xaracil@uoc.edu
 */
@RequiredArgsConstructor
@Getter
public class LineItemBean {
	private final LineItem lineItem;
	private final List<Member> members;
	private final List<Result> results;
}
