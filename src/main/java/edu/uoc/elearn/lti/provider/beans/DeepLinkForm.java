package edu.uoc.elearn.lti.provider.beans;

import lombok.Data;

/**
 * @author xaracil@uoc.edu
 */
@Data
public class DeepLinkForm {
	private String title;
	private String text;
	private String url;
	private String documentTarget;
	private String type;
	private String mediaType;
}
