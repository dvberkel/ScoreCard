package org.effrafax.scorecard;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.effrafax.scorecard.Dealer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class NumberOfCardsPerRoundTest {
	private final int numberOfPlayers;
	private final int expectedNumberOfCards;
	private final int roundNumber;
	private Dealer dealer;

	public NumberOfCardsPerRoundTest(int numberOfPlayers,
			int expectedNumberOfCards, int roundNumber) {
		this.numberOfPlayers = numberOfPlayers;
		this.expectedNumberOfCards = expectedNumberOfCards;
		this.roundNumber = roundNumber;
	}

	@Before
	public void createDealer() {
		this.dealer = new Dealer(numberOfPlayers);
	}

	@Test
	public void numberOfCardsInAnRound() {
		assertEquals(expectedNumberOfCards,
				dealer.numberOfCardsInRound(roundNumber));
	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object[]> data = new ArrayList<Object[]>();
		data.add(new Object[] { 4, 1, 1 });
		data.add(new Object[] { 4, 2, 2 });
		data.add(new Object[] { 4, 3, 3 });
		data.add(new Object[] { 4, 13, 13 });
		data.add(new Object[] { 4, 12, 14 });
		data.add(new Object[] { 4, 11, 15 });
		data.add(new Object[] { 4, 1, 25 });
		data.add(new Object[] { 2, 1, 1 });
		data.add(new Object[] { 2, 2, 2 });
		data.add(new Object[] { 2, 26, 26 });
		data.add(new Object[] { 2, 25, 27 });
		data.add(new Object[] { 2, 1, 51 });
		data.add(new Object[] { 5, 1, 1 });
		data.add(new Object[] { 5, 1, 1 });
		data.add(new Object[] { 5, 10, 10 });
		data.add(new Object[] { 5, 9, 11 });
		data.add(new Object[] { 5, 1, 19 });
		return data;
	}
}
