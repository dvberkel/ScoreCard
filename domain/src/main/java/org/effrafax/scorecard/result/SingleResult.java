package org.effrafax.scorecard.result;

import java.util.Map;


public class SingleResult implements Result {

	private final Map<String, Integer> scores;

	public SingleResult(Map<String, Integer> scores) {
		this.scores = scores;
	}

	@Override
	public int pointsFor(String name) {
		return scores.get(name);
	}

}
