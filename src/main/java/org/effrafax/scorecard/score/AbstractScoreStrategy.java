package org.effrafax.scorecard.score;


public class AbstractScoreStrategy {

	private final int bonus;
	private final int cardScore;

	public AbstractScoreStrategy(int bonus, int cardScore) {
		this.bonus = bonus;
		this.cardScore = cardScore;
	}

	public int score(int tricksBid, int tricksWon) {
		if (tricksBid == tricksWon) {
			return bonus + cardScore * tricksWon;
		} else {
			return -cardScore * Math.abs(tricksBid - tricksWon);
		}
	}

}