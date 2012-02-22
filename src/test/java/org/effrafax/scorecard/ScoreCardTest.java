package org.effrafax.scorecard;

import static org.effrafax.scorecard.PartialRound.where;
import static org.junit.Assert.assertEquals;

import org.effrafax.scorecard.result.Result;
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

	@Test
	public void calculateScoreAfterRound() {
		ScoreCard scoreCard = new ScoreCard("Daan", "Marlies", "Jet", "Peter");
		scoreCard.add(new Round(
				where("Daan").bid(1).won(1),
				where("Marlies").bid(0).won(0),
				where("Jet").bid(1).won(0),
				where("Peter").bid(0).won(0)));

		Result result = scoreCard.result();

		assertEquals(12, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(-2, result.pointsFor("Jet"));
		assertEquals(10, result.pointsFor("Peter"));
	}
}
