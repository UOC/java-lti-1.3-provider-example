package edu.uoc.elearn.lti.provider.domain;

import edu.uoc.lti.ags.ActivityProgressEnum;
import edu.uoc.lti.ags.GradingProgressEnum;
import edu.uoc.lti.ags.Score;

import java.time.Instant;

/**
 * @author Xavi Aracil <xaracil@uoc.edu>
 */
public class ScoreFactory {
	public Score from(String userId, Double score, String comment) {
		return Score.builder()
						.userId(userId)
						.scoreGiven(score)
						.comment(comment)
						.timeStamp(Instant.now())
						.activityProgress(ActivityProgressEnum.COMPLETED)
						.gradingProgress(GradingProgressEnum.FULLY_GRADED)
						.build();
	}
}
