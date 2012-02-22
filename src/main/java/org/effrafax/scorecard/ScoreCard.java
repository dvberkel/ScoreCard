package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.effrafax.scorecard.exception.BidsTotalsNumberOfCards;
import org.effrafax.scorecard.exception.WinningsNotNumberOfCards;
import org.effrafax.scorecard.exception.WrongPlayerInRound;
import org.effrafax.scorecard.result.CompoundResult;
import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.score.ScoreStrategy;

public class ScoreCard {

	private final ScoreStrategy strategy;
	private final List<Round> rounds;
	private final Set<String> players;

	public ScoreCard(ScoreStrategy strategy, String... names) {
		this.strategy = strategy;
		this.rounds = new ArrayList<Round>();
		this.players = new HashSet<String>();
		players.addAll(Arrays.asList(names));
	}

	public Result result() {
		CompoundResult result = new CompoundResult();
		for (Round round : rounds) {
			result.add(round.result(strategy));
		}
		return result;
	}

	public void add(Round round) {
		verify(round);
		rounds.add(round);
	}

	private void verify(Round round) {
		for (RoundVerifier verifier : verifiers()) {
			verifier.verify(round);
		}
	}

	private List<RoundVerifier> verifiers() {
		List<RoundVerifier> verifiers = new ArrayList<RoundVerifier>();
		verifiers.add(new PlayersVerifier(players));
		verifiers.add(new BitTotalVerifier(numberOfCardsThisRound()));
		verifiers.add(new WinningsVerifier(numberOfCardsThisRound()));
		return verifiers;
	}

	private int numberOfCardsThisRound() {
		return rounds.size() + 1;
	}

}

interface RoundVerifier {
	public void verify(Round round);
}

abstract class ConditionedRoundVerifier implements RoundVerifier {
	@Override
	public void verify(Round round) {
		if (conditionOn(round)) {
			throw exceptionForCondition();
		}
	}

	public abstract boolean conditionOn(Round round);

	public abstract RuntimeException exceptionForCondition();
}

class PlayersVerifier extends ConditionedRoundVerifier {

	private final Set<String> players;

	public PlayersVerifier(Set<String> players) {
		this.players = players;
	}

	@Override
	public boolean conditionOn(Round round) {
		return ! players.equals(round.players());
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new WrongPlayerInRound();
	}

}

class BitTotalVerifier extends ConditionedRoundVerifier {

	private final int numberOfCardsThisRound;

	public BitTotalVerifier(int numberOfCardsThisRound) {
		this.numberOfCardsThisRound = numberOfCardsThisRound;
	}

	@Override
	public boolean conditionOn(Round round) {
		return numberOfCardsThisRound == round.bidTotal();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new BidsTotalsNumberOfCards();
	}

}

class WinningsVerifier extends ConditionedRoundVerifier {

	private final int numberOfCardsThisRound;

	public WinningsVerifier(int numberOfCardsThisRound) {
		this.numberOfCardsThisRound = numberOfCardsThisRound;
	}

	@Override
	public boolean conditionOn(Round round) {
		return numberOfCardsThisRound != round.winnings();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new WinningsNotNumberOfCards();
	}

}