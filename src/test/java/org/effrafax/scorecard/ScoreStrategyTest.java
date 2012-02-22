package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class ScoreStrategyTest {

	private static final String PLAYER = "Peter";
	private final ScoreStrategy strategy;
	private final int bid;
	private final int won;
	private final Integer expectedScore;

	public ScoreStrategyTest(ScoreStrategy strategy, int bid, int won,
			int expectedScore) {
		this.strategy = strategy;
		this.bid = bid;
		this.won = won;
		this.expectedScore = expectedScore;

	}
	@Test
	public void scorePartialRound() {
		int score = strategy
		.score(PartialRound.where(PLAYER).bid(bid).won(won));

		assertEquals(expectedScore, Integer.valueOf(score));
	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object[]> data = new ArrayList<Object[]>();
		ScoreStrategy opEnNeerStrategy = new OpEnNeerStrategy();
		data.add(new Object[] { opEnNeerStrategy, 0, 0, 10 });
		data.add(new Object[] { opEnNeerStrategy, 1, 1, 12 });
		data.add(new Object[] { opEnNeerStrategy, 2, 2, 14 });
		data.add(new Object[] { opEnNeerStrategy, 0, 1, -2 });
		data.add(new Object[] { opEnNeerStrategy, 0, 2, -4 });
		data.add(new Object[] { opEnNeerStrategy, 1, 0, -2 });
		ScoreStrategy boerenBridgeStrategy = new BoerenBridgeStrategy();
		data.add(new Object[] { boerenBridgeStrategy, 0, 0, 5 });
		data.add(new Object[] { boerenBridgeStrategy, 1, 1, 6 });
		data.add(new Object[] { boerenBridgeStrategy, 2, 2, 7 });
		data.add(new Object[] { boerenBridgeStrategy, 0, 1, -1 });
		data.add(new Object[] { boerenBridgeStrategy, 0, 2, -2 });
		data.add(new Object[] { boerenBridgeStrategy, 1, 0, -1 });
		return data;
	}
}
