package org.effrafax.scorecard;

public class AbstractCardStrategy {

	private final int bonus;
	private final int cardScore;

	public AbstractCardStrategy(int bonus, int cardScore) {
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