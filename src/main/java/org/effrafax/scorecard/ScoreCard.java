package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.effrafax.scorecard.exception.BidsTotalsRound;
import org.effrafax.scorecard.exception.WrongPlayerInRound;
import org.effrafax.scorecard.result.CompoundResult;
import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.score.ScoreStrategy;

public class ScoreCard {

	private final ScoreStrategy strategy;
	private final List<Round> rounds;
	private final Set<String> players;

	public ScoreCard(ScoreStrategy strategy, String... names) {
		this.strategy = strategy;
		this.rounds = new ArrayList<Round>();
		this.players = new HashSet<String>();
		players.addAll(Arrays.asList(names));
	}

	public Result result() {
		CompoundResult result = new CompoundResult();
		for (Round round : rounds) {
			result.add(round.result(strategy));
		}
		return result;
	}

	public void add(Round round) {
		verify(round);
		rounds.add(round);
	}

	private void verify(Round round) {
		if (! players.equals(round.players())) {
			throw new WrongPlayerInRound();
		}
		if (rounds.size() + 1 == round.bidTotal()) {
			throw new BidsTotalsRound();
		}
	}

}
