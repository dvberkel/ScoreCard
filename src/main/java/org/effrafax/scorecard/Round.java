package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {

	private final List<PartialRound> partialRounds;

	public Round(PartialRound partialRoundPlayer1, PartialRound partialRoundPlayer2, PartialRound partialRoundPlayer3,
			PartialRound partialRoundPlayer4) {
		partialRounds = new ArrayList<PartialRound>();
		partialRounds.add(partialRoundPlayer1);
		partialRounds.add(partialRoundPlayer2);
		partialRounds.add(partialRoundPlayer3);
		partialRounds.add(partialRoundPlayer4);
	}

	public Result result() {
		ScoreStrategy strategy = new OpEnNeerStrategy();
		Map<String, Integer> scores = new HashMap<String, Integer>();
		for (PartialRound partialRound : partialRounds) {
			scores.put(partialRound.player(), strategy.score(partialRound));
		}
		return new SingleResult(scores);
	}
}
