package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class RoundTest {

	@Test
	public void testBuildRound() {
		Round round = new Round(PartialRound.where("Daan").bid(1).won(0),
				PartialRound.where("Marlies").bid(0).won(0), PartialRound
				.where("Jet").bid(0).won(0), PartialRound
				.where("Peter").bid(1).won(1));

		Result result = round.result();

		assertEquals(-2, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(10, result.pointsFor("Jet"));
		assertEquals(12, result.pointsFor("Peter"));
	}

}
