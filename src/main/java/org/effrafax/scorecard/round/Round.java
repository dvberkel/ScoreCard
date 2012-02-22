package org.effrafax.scorecard.round;

import java.util.Arrays;
import java.util.Collections;
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
		ScoresCollector collector = new ScoresCollector(strategy);
		collectWith(collector);
		return collector.scores();
	}

	private void collectWith(Collector collector) {
		for (PartialRound partialRound : partialRounds) {
			collector.collect(partialRound);
		}
	}

	public Set<String> players() {
		PlayersCollector collector = new PlayersCollector();
		collectWith(collector);
		return collector.players();
	}

	public int bidTotal() {
		BidTotalCollector collector = new BidTotalCollector();
		collectWith(collector);
		return collector.bidTotal();
	}

	public int winnings() {
		WinningsCollector collector = new WinningsCollector();
		collectWith(collector);
		return collector.winnings();
	}
}

interface Collector {
	public void collect(PartialRound partialRound);
}

class ScoresCollector implements Collector {
	private final Map<String, Integer> scores = new HashMap<String, Integer>();
	private final ScoreStrategy strategy;

	public ScoresCollector(ScoreStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void collect(PartialRound partialRound) {
		scores.put(partialRound.player(), strategy.score(partialRound));
	}

	public Map<String, Integer> scores() {
		return Collections.unmodifiableMap(this.scores);
	}
}

class PlayersCollector implements Collector {
	private final Set<String> players = new HashSet<String>();

	@Override
	public void collect(PartialRound partialRound) {
		players.add(partialRound.player());
	}

	public Set<String> players() {
		return Collections.unmodifiableSet(players);
	}
}

class BidTotalCollector implements Collector {
	private int bidTotal = 0;

	@Override
	public void collect(PartialRound partialRound) {
		bidTotal += partialRound.getBid();
	}

	public int bidTotal() {
		return bidTotal;
	}

}

class WinningsCollector implements Collector {
	private int winnings = 0;

	@Override
	public void collect(PartialRound partialRound) {
		winnings += partialRound.getWon();
	}

	public int winnings() {
		return winnings;
	}

}
