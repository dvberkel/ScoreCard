package org.effrafax.scorecard;

import static org.effrafax.scorecard.round.PartialRound.where;
import static org.junit.Assert.assertEquals;

import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.score.OpEnNeer;
import org.junit.Before;
import org.junit.Test;


public class ScoreCardTest {

	private ScoreCard scoreCard;

	@Before
	public void createScoreCard() {
		this.scoreCard = new ScoreCard(new OpEnNeer(), "Daan", "Marlies", "Jet", "Peter");
	}

	@Test
	public void calculateInitialScore() {
		Result result = scoreCard.result();

		assertEquals(0, result.pointsFor("Daan"));
		assertEquals(0, result.pointsFor("Marlies"));
		assertEquals(0, result.pointsFor("Jet"));
		assertEquals(0, result.pointsFor("Peter"));
	}

	@Test
	public void calculateScoreAfterRound() {
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
