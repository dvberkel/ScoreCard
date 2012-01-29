package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.List;

public class CompoundResult implements Result {

	private final List<Result> results = new ArrayList<Result>();

	public void add(Result result) {
		results.add(result);
	}

	@Override
	public int pointsFor(String name) {
		int score = 0;
		for (Result result : results) {
			score += result.pointsFor(name);
		}
		return score;
	}

}
