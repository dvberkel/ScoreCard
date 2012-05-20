package org.effrafax.scorecard.round;

import static org.junit.Assert.assertEquals;

import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.score.OpEnNeer;
import org.junit.Test;

public class RoundTest {

	@Test
	public void createRound() {
		Round round = new Round(Bid.where("Daan").bid(1), Bid.where("Marlies")
				.bid(0), Bid.where("Jet").bid(0), Bid.where("Peter").bid(1));
		round.tricks(Trick.where("Daan").won(0), Trick.where("Marlies").won(0),
				Trick.where("Jet").won(0), Trick.where("Peter")
				.won(1));

		Result result = round.result(new OpEnNeer());

		assertEquals(-2, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(10, result.pointsFor("Jet"));
		assertEquals(12, result.pointsFor("Peter"));

	}

}
