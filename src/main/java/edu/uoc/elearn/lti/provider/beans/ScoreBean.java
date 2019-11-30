package edu.uoc.elearn.lti.provider.beans;

import lombok.Data;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
@Data
public class ScoreBean {
	private String userId;
	private Double score;
	private String comment;
}
