package org.effrafax.scorecard.round;

import java.util.HashMap;
import java.util.Map;

public class RoundResult {
	private final Map<String, Trick> winnings = new HashMap<String, Trick>();

	public RoundResult(Trick... winnings) {
		for (Trick trick : winnings) {
			this.winnings.put(trick.getPlayer(), trick);
		}
	}

	public Trick tricksBy(String player) {
		return winnings.get(player);
	}

	public int totalTricks() {
		int result = 0;
		for (Trick trick : winnings.values()) {
			result += trick.getWon();
		}
		return result;
	}

}
