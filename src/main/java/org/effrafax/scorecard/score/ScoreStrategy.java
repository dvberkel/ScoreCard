package org.effrafax.scorecard.score;

import org.effrafax.scorecard.round.PartialRound;

public interface ScoreStrategy {

	int score(PartialRound partialRound);

}
