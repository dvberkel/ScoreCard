package org.effrafax.scorecard.round;

import static org.effrafax.scorecard.round.PartialRound.where;
import static org.junit.Assert.assertEquals;

import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;
import org.junit.Test;

public class RoundTest {

	@Test
	public void testBuildRound() {
		Round round = new Round(
			where("Daan").bid(1).won(0),
			where("Marlies").bid(0).won(0),
			where("Jet").bid(0).won(0),
			where("Peter").bid(1).won(1)
		);

		Result result = round.result();

		assertEquals(-2, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(10, result.pointsFor("Jet"));
		assertEquals(12, result.pointsFor("Peter"));
	}

}
