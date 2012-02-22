package org.effrafax.scorecard.round;

public class PartialRound {

	public static PartialRoundBuilder where(String player) {
		return new PartialRoundBuilder(player);
	}

	private final String player;
	private final int bid;
	private final int won;

	private PartialRound(String player, int bid, int won) {
		this.player = player;
		this.bid = bid;
		this.won = won;
	}

	public String player() {
		return player;
	}

	public int getBid() {
		return bid;
	}

	public int getWon() {
		return won;
	}

	public static class PartialRoundBuilder {

		public String player;

		public PartialRoundBuilder(String player) {
			this.player = player;
		}

		public CurriedPartialRoundBuilder bid(int expectedBid) {
			return new CurriedPartialRoundBuilder(player, expectedBid);
		}

	}

	public static class CurriedPartialRoundBuilder {

		public String player;
		public int expectedBid;

		public CurriedPartialRoundBuilder(String player, int expectedBid) {
			this.player = player;
			this.expectedBid = expectedBid;
		}

		public PartialRound won(int expectedWon) {
			return new PartialRound(player, expectedBid, expectedWon);
		}
	}
}

