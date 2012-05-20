package org.effrafax.scorecard.round;


public class Bid {

	public static BidBuilder where(String player) {
		return new BidBuilder(player);
	}

	private final String player;
	private final int bid;

	public Bid(String player, int bid) {
		this.player = player;
		this.bid = bid;
	}

	public String getPlayer() {
		return player;
	}

	public int getBid() {
		return bid;
	}

	public static class BidBuilder {

		private final String player;

		public BidBuilder(String player) {
			this.player = player;
		}

		public Bid bid(int bid) {
			return new Bid(player, bid);
		}

	}
}