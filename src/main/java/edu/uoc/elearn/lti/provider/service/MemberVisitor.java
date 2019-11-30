package edu.uoc.elearn.lti.provider.service;

import edu.uoc.elc.lti.platform.Member;

import java.util.List;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public interface MemberVisitor {
	List<Member> getAll();
	boolean hasMembers();
}
