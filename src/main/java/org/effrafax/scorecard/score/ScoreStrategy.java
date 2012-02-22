package org.effrafax.scorecard.score;

import org.effrafax.scorecard.PartialRound;

public interface ScoreStrategy {

	int score(PartialRound partialRound);

}
