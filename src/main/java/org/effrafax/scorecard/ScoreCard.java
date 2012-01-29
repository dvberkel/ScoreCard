package org.effrafax.scorecard;

import java.util.HashMap;
import java.util.Map;

public class ScoreCard {

	public ScoreCard(String... names) {
	}

	public Result result() {
		Map<String, Integer> scores = new HashMap<String, Integer>();
		scores.put("Daan", 0);
		scores.put("Marlies", 0);
		scores.put("Jet", 0);
		scores.put("Peter", 0);
		return new SingleResult(scores);
	}

	public void add(Round round) {

	}

}
