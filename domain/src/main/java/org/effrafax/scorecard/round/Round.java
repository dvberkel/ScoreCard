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

	private final List<Bid> bids;
	private final Map<String, Trick> tricks = new HashMap<String, Trick>();
	private boolean finished = false;

	public Round(Bid... bids) {
		this(Arrays.asList(bids));
	}

	private Round(final List<Bid> bids) {
		this.bids = bids;
	}

	public Result result(ScoreStrategy strategy) {
		return new SingleResult(scores(strategy));
	}

	private Map<String, Integer> scores(ScoreStrategy strategy) {
		ScoreCollector collector = new ScoreCollector(strategy);
		collect(collector);
		return collector.getScores();
	}

	private void collect(Collector collector) {
		for (Bid bid : bids) {
			collector.collect(bid);
		}
	}

	public Set<String> players() {
		PlayerCollector collector = new PlayerCollector();
		collect(collector);
		return collector.getPlayers();
	}

	public int totalBids() {
		TotalBidsCollector collector = new TotalBidsCollector();
		collect(collector);
		return collector.getTotalBids();
	}

	public int totalTricks() {
		TotalTricksCollector collector = new TotalTricksCollector();
		collect(collector);
		return collector.getTotalTricks();
	}

	public void tricks(Trick... won) {
		tricks(Arrays.asList(won));

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

	class ScoreCollector implements Collector {

		private final ScoreStrategy strategy;
		private final Map<String, Integer> scores = new HashMap<String, Integer>();

		public ScoreCollector(ScoreStrategy strategy) {
			this.strategy = strategy;
		}

		@Override
		public void collect(Bid bid) {
			scores.put(bid.getPlayer(), strategy.score(bid.getBid(), tricks
					.get(bid.getPlayer()).getWon()));
		}

		public Map<String, Integer> getScores() {
			return scores;
		}
	}

	class TotalTricksCollector implements Collector {
		private int result = 0;

		@Override
		public void collect(Bid bid) {
			result += tricks.get(bid.getPlayer()).getWon();
		}

		public int getTotalTricks() {
			return result;
		}
	}
}

interface Collector {
	void collect(Bid bid);
}

class PlayerCollector implements Collector {
	private final Set<String> players = new HashSet<String>();

	@Override
	public void collect(Bid bid) {
		players.add(bid.getPlayer());
	}

	public Set<String> getPlayers() {
		return players;
	}
}

class TotalBidsCollector implements Collector {
	private int result = 0;

	@Override
	public void collect(Bid bid) {
		result += bid.getBid();
	}

	public int getTotalBids() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}