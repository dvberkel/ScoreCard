package org.effrafax.scorecard;

import static org.effrafax.scorecard.round.PartialRound.where;

import org.effrafax.scorecard.exception.BidsTotalsNumberOfCards;
import org.effrafax.scorecard.exception.WrongPlayerInRound;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.score.OpEnNeer;
import org.junit.Test;

public class ScoreCardExceptionTest {

	@Test(expected=WrongPlayerInRound.class)
	public void whenWrongPlayerInRound() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet", "Marlies", "Daan");

		scoreCard.add(new Round(
			where("Peter").bid(0).won(0),
			where("Jet").bid(1).won(0),
			where("Marlies").bid(0).won(0),
			where("Wrong Player").bid(1).won(1)
		));
	}

	@Test(expected=BidsTotalsNumberOfCards.class)
	public void whenBidsTotalRound() {
		ScoreCard scoreCard = new ScoreCard(new OpEnNeer(), "Peter", "Jet", "Marlies", "Daan");

		scoreCard.add(new Round(
			where("Peter").bid(0).won(0),
			where("Jet").bid(1).won(0),
			where("Marlies").bid(0).won(0),
			where("Daan").bid(0).won(1)
		));
	}

}
