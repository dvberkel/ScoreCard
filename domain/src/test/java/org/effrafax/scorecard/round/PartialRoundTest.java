package org.effrafax.scorecard.round;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.effrafax.scorecard.round.PartialRound;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PartialRoundTest {

	private final String expectedPlayer;
	private final int expectedBid;
	private final int expectedWon;

	public PartialRoundTest(String expectedPlayer, int expectedBid, int expectedWon) {
		this.expectedPlayer = expectedPlayer;
		this.expectedBid = expectedBid;
		this.expectedWon = expectedWon;
	}

	@Test
	public void createRound2() {
		PartialRound partialRound = PartialRound.where(expectedPlayer)
		.bid(expectedBid).won(expectedWon);

		assertEquals(expectedPlayer, partialRound.player());
		assertEquals(expectedBid, partialRound.getBid());
		assertEquals(expectedWon, partialRound.getWon());
	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { "Peter", 1, 1 });
		data.add(new Object[] { "Daan", 0, 1 });
		return data;
	}
}
