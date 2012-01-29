package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ScoreCardTest {

	@Test
	public void prepareScoreCard() {
		ScoreCard scoreCard = new ScoreCard("Daan", "Marlies", "Jet", "Peter");

		Result result = scoreCard.result();

		assertEquals(0, result.pointsFor("Daan"));
		assertEquals(0, result.pointsFor("Marlies"));
		assertEquals(0, result.pointsFor("Jet"));
		assertEquals(0, result.pointsFor("Peter"));
	}
}
