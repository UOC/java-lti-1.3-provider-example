package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elearn.lti.provider.security.UOCContext;
import edu.uoc.elearn.lti.provider.security.UOCUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Getter
@RequiredArgsConstructor
public class IndexBean {
	private final UOCUser user;
	private final UOCContext context;
	private final List<String> roles;
	private final List<Member> members;
}
