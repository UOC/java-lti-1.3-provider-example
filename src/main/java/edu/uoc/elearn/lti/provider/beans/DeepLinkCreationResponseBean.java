package edu.uoc.elearn.lti.provider.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.net.URL;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@RequiredArgsConstructor
@Getter
public class DeepLinkCreationResponseBean {
	private final String JWT;
	private final URL url;
}
