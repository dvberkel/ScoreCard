package org.effrafax.scorecard;

import org.effrafax.scorecard.exception.BidsEqualsNumberOfCardsInRound;
import org.effrafax.scorecard.exception.RoundAddedBeforeRoundResult;
import org.effrafax.scorecard.exception.RoundResultAlreadyAdded;
import org.effrafax.scorecard.exception.TricksWonDoesNotEqualNumberOfCardsInRound;
import org.effrafax.scorecard.exception.WrongPlayerInRound;
import org.effrafax.scorecard.round.Bid;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.round.RoundResult;
import org.effrafax.scorecard.round.Trick;
import org.effrafax.scorecard.score.OpEnNeer;
import org.junit.Test;

public class ScoreCardExceptionTest {

	@Test(expected = WrongPlayerInRound.class)
	public void whenWrongPlayerInRound() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet",
				"Marlies", "Daan");

		scoreCard.add(new Round(Bid.where("Peter").bid(0), Bid.where("Jet")
				.bid(1), Bid.where("Marlies").bid(0), Bid.where("Wrong Player")
				.bid(1)));
	}

	@Test(expected = BidsEqualsNumberOfCardsInRound.class)
	public void whenBidsTotalRound() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet",
				"Marlies", "Daan");

		scoreCard
		.add(new Round(Bid.where("Peter").bid(1), Bid.where("Jet").bid(
				0), Bid.where("Marlies").bid(0), Bid.where("Daan").bid(
						0)));
	}

	@Test(expected = TricksWonDoesNotEqualNumberOfCardsInRound.class)
	public void whenWinningsNotNumberOfCards() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet",
				"Marlies", "Daan");

		scoreCard
		.add(new Round(Bid.where("Peter").bid(0), Bid.where("Jet").bid(
				1), Bid.where("Marlies").bid(0), Bid.where("Daan").bid(
						1)));

		scoreCard.add(new RoundResult(Trick.where("Peter").won(0), Trick.where(
		"Jet").won(0), Trick.where("Marlies").won(0), Trick.where(
		"Daan").won(0)));
	}

	@Test(expected = RoundAddedBeforeRoundResult.class)
	public void whenAnRoundIsAddedWithoutFirstARoundResultIsAdded() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet",
				"Marlies", "Daan");

		scoreCard
		.add(new Round(Bid.where("Peter").bid(0), Bid.where("Jet").bid(
				1), Bid.where("Marlies").bid(0), Bid.where("Daan").bid(
						1)));

		scoreCard
		.add(new Round(Bid.where("Peter").bid(0), Bid.where("Jet").bid(
				2), Bid.where("Marlies").bid(0), Bid.where("Daan").bid(
						1)));
	}

	@Test(expected = RoundResultAlreadyAdded.class)
	public void whenARoundResultIsAddedTwice() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet",
				"Marlies", "Daan");

		scoreCard
		.add(new Round(Bid.where("Peter").bid(0), Bid.where("Jet").bid(
				1), Bid.where("Marlies").bid(0), Bid.where("Daan").bid(
						1)));

		scoreCard.add(new RoundResult(Trick.where("Peter").won(1), Trick.where(
		"Jet").won(0), Trick.where("Marlies").won(0), Trick.where(
		"Daan").won(0)));

		scoreCard.add(new RoundResult(Trick.where("Peter").won(1), Trick.where(
				"Jet").won(0), Trick.where("Marlies").won(0), Trick.where(
				"Daan").won(0)));
	}

}
