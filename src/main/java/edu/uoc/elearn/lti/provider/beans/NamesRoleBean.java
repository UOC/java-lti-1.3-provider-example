package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.elc.lti.platform.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@RequiredArgsConstructor
@Getter
public class NamesRoleBean {
	private final Boolean available;
	private final List<Member> members;
}
