package org.effrafax.scorecard.score;

import org.effrafax.scorecard.PartialRound;

public class AbstractScoreStrategy {

	private final int bonus;
	private final int cardScore;

	public AbstractScoreStrategy(int bonus, int cardScore) {
		this.bonus = bonus;
		this.cardScore = cardScore;
	}

	public int score(PartialRound partialRound) {
		if (partialRound.getBid() == partialRound.getWon()) {
			return bonus + cardScore * partialRound.getWon();
		} else {
			return -cardScore * Math.abs(partialRound.getBid() - partialRound.getWon());
		}
	}

}