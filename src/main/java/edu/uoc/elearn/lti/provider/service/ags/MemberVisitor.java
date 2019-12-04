package edu.uoc.elearn.lti.provider.service.ags;

import edu.uoc.elc.lti.platform.Member;
import edu.uoc.elc.spring.lti.tool.NamesRoleServiceProvider;
import edu.uoc.elc.spring.lti.tool.ToolProvider;
import lombok.RequiredArgsConstructor;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

/**
 * @author xaracil@uoc.edu
 */
@RequiredArgsConstructor
public class MemberVisitor {
	private final ToolProvider toolProvider;

	public List<Member> getAll() {
		final NamesRoleServiceProvider namesRoleServiceProvider = toolProvider.getNamesRoleServiceProvider();
		return getFromNameServiceProvider(namesRoleServiceProvider);
	}

	public boolean canGet() {
		final NamesRoleServiceProvider namesRoleServiceProvider = toolProvider.getNamesRoleServiceProvider();
		return namesRoleServiceProvider.hasNameRoleService();
	}

	private List<Member> getFromNameServiceProvider(NamesRoleServiceProvider namesRoleServiceProvider) {
		try {
			return namesRoleServiceProvider.getMembers();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
}
