package org.effrafax.scorecard.round;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.result.SingleResult;
import org.effrafax.scorecard.score.ScoreStrategy;

public class Round {

	private final List<PartialRound> partialRounds;

	public Round(PartialRound partialRoundPlayer1, PartialRound partialRoundPlayer2, PartialRound partialRoundPlayer3, PartialRound partialRoundPlayer4) {
		this(Arrays.asList(new PartialRound[]{partialRoundPlayer1, partialRoundPlayer2, partialRoundPlayer3, partialRoundPlayer4}));
	}

	private Round(final List<PartialRound> partialRounds) {
		this.partialRounds = partialRounds;
	}

	public Result result(ScoreStrategy strategy) {
		return new SingleResult(scores(strategy));
	}

	private Map<String, Integer> scores(ScoreStrategy strategy) {
		Map<String, Integer> scores = new HashMap<String, Integer>();
		for (PartialRound partialRound : partialRounds) {
			scores.put(partialRound.player(), strategy.score(partialRound));
		}
		return scores;
	}

	public Set<String> players() {
		HashSet<String> players = new HashSet<String>();
		for (PartialRound partialRound : partialRounds) {
			players.add(partialRound.player());
		}
		return players;
	}
}
