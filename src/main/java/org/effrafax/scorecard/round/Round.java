package org.effrafax.scorecard.round;

import java.util.ArrayList;
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
	private final List<Bid> bids;
	private final Map<String, Trick> tricks = new HashMap<String, Trick>();
	private boolean finished = false;

	public Round(Bid bid, Bid bid2, Bid bid3, Bid bid4) {
		this(Arrays.asList(new Bid[] { bid, bid2, bid3, bid4 }));
	}

	private Round(final List<Bid> bids) {
		partialRounds = new ArrayList<PartialRound>();
		this.bids = bids;
	}

	public Result result(ScoreStrategy strategy) {
		return new SingleResult(scores(strategy));
	}

	private Map<String, Integer> scores(ScoreStrategy strategy) {
		Map<String, Integer> scores = new HashMap<String, Integer>();
		for (Bid bid : this.bids) {
			scores.put(bid.getPlayer(), strategy.score(bid.getBid(), tricks
					.get(bid.getPlayer()).getWon()));
		}
		return scores;
	}

	private void collectWith(Collector collector) {
		for (PartialRound partialRound : partialRounds) {
			collector.collect(partialRound);
		}
	}

	public Set<String> players() {
		Set<String> players = new HashSet<String>();
		for (Bid bid : bids) {
			players.add(bid.getPlayer());
		}
		return players;
	}

	public int bidTotal() {
		int result = 0;
		for (Bid bid : bids) {
			result += bid.getBid();
		}
		return result;
	}

	public int tricks() {
		int result = 0;
		for (Bid bid : bids) {
			result += tricks.get(bid.getPlayer()).getWon();
		}
		return result;
	}

	public void tricks(Trick won, Trick won2, Trick won3, Trick won4) {
		tricks(Arrays.asList(new Trick[] { won, won2, won3, won4 }));

	}

	private void tricks(List<Trick> asList) {
		for (Trick trick : asList) {
			tricks.put(trick.getPlayer(), trick);
		}
		finished = true;
	}

	public boolean isFinished() {
		return finished;
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
		scores.put(partialRound.player(),
				strategy.score(partialRound.getBid(), partialRound.getWon()));
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

abstract class CountCollector implements Collector {
	private int count = 0;

	@Override
	public void collect(PartialRound partialRound) {
		count += incrementFrom(partialRound);
	}

	protected abstract int incrementFrom(PartialRound partialRound);

	public int count() {
		return count;
	}
}

class BidTotalCollector extends CountCollector {
	@Override
	protected int incrementFrom(PartialRound partialRound) {
		return partialRound.getBid();
	}

	public int bidTotal() {
		return count();
	}
}

class WinningsCollector extends CountCollector {
	public int winnings() {
		return count();
	}

	@Override
	protected int incrementFrom(PartialRound partialRound) {
		return partialRound.getWon();
	}

}
