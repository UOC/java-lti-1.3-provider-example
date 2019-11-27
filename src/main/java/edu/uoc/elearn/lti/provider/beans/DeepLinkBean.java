package edu.uoc.elearn.lti.provider.beans;

import edu.uoc.elc.lti.tool.deeplinking.Settings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Getter
@RequiredArgsConstructor
public class DeepLinkBean {
	private final Settings settings;
}
