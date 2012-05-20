package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Bid;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.round.Trick;
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
		Round round = new Round(Bid.where("Daan").bid(1), Bid.where("Marlies")
				.bid(0), Bid.where("Jet").bid(1), Bid.where("Peter").bid(
						0));
		round.tricks(Trick.where("Daan").won(1), Trick.where("Marlies").won(0),
				Trick.where("Jet").won(0), Trick.where("Peter").won(0));
		scoreCard.add(round);

		Result result = scoreCard.result();

		assertEquals(12, result.pointsFor("Daan"));
		assertEquals(10, result.pointsFor("Marlies"));
		assertEquals(-2, result.pointsFor("Jet"));
		assertEquals(10, result.pointsFor("Peter"));
	}
}
