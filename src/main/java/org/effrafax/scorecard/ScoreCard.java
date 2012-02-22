package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.List;

import org.effrafax.scorecard.result.CompoundResult;
import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;

public class ScoreCard {

	private final List<Round> rounds;

	public ScoreCard(String... names) {
		this.rounds = new ArrayList<Round>();
	}

	public Result result() {
		CompoundResult result = new CompoundResult();
		for (Round round : rounds) {
			result.add(round.result());
		}
		return result;
	}

	public void add(Round round) {
		rounds.add(round);
	}

}
