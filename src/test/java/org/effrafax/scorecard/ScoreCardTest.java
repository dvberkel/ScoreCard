package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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

	@Ignore
	@Test
	public void calculateScoreAfterRound() {
		ScoreCard scoreCard = new ScoreCard("Daan", "Marlies", "Jet", "Peter");

		Result result = scoreCard.result();

		assertEquals(12, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(-2, result.pointsFor("Jet"));
		assertEquals(10, result.pointsFor("Peter"));
	}
}
