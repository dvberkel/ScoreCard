package org.effrafax.scorecard.round;


public class Trick {

	public static TrickBuilder where(String player) {
		return new TrickBuilder(player);
	}

	private final String player;
	private final int tricks;

	public Trick(String player, int tricks) {
		this.player = player;
		this.tricks = tricks;
	}

	public String getPlayer() {
		return player;
	}

	public int getWon() {
		return tricks;
	}

	public static class TrickBuilder {

		private final String player;

		public TrickBuilder(String player) {
			this.player = player;
		}

		public Trick won(int tricks) {
			return new Trick(player, tricks);
		}

	}
}
