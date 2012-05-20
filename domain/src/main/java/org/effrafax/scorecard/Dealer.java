package org.effrafax.scorecard;

public class Dealer {
	private static final int NUMBER_OF_CARDS_IN_DECK = 52;
	private final int numberOfPlayers;

	public Dealer(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int numberOfCardsInRound(int roundNumber) {
		int maximumNumberOfCards = NUMBER_OF_CARDS_IN_DECK / numberOfPlayers;
		if (roundNumber > maximumNumberOfCards) {
			return (2 * maximumNumberOfCards) - roundNumber;
		} else {
			return roundNumber;
		}
	}
}