package org.effrafax.scorecard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.effrafax.scorecard.exception.BidsEqualsNumberOfCardsInRound;
import org.effrafax.scorecard.exception.RoundAddedBeforeRoundResult;
import org.effrafax.scorecard.exception.RoundResultAlreadyAdded;
import org.effrafax.scorecard.exception.TricksWonDoesNotEqualNumberOfCardsInRound;
import org.effrafax.scorecard.exception.WrongPlayerInRound;
import org.effrafax.scorecard.result.CompoundResult;
import org.effrafax.scorecard.result.Result;
import org.effrafax.scorecard.round.Round;
import org.effrafax.scorecard.round.RoundResult;
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
		for (Verifier<Round> verifier : verifiers()) {
			verifier.verify(round);
		}
	}

	private List<Verifier<Round>> verifiers() {
		List<Verifier<Round>> verifiers = new ArrayList<Verifier<Round>>();
		verifiers.add(new PlayersVerifier(players));
		verifiers.add(new LastRoundFinishedVerifier(rounds));
		verifiers.add(new BidTotalVerifier(numberOfCardsThisRound()));
		return verifiers;
	}

	private int numberOfCardsThisRound() {
		return new Dealer(players.size())
		.numberOfCardsInRound(rounds.size() + 1);
	}

	public void add(RoundResult roundResult) {
		verify(roundResult);
		rounds.get(rounds.size() - 1).add(roundResult);
	}

	private void verify(RoundResult roundResult) {
		for (Verifier<RoundResult> verifier : roundResultVerifiers()) {
			verifier.verify(roundResult);
		}

	}

	private List<Verifier<RoundResult>> roundResultVerifiers() {
		List<Verifier<RoundResult>> verifiers = new ArrayList<Verifier<RoundResult>>();
		// TODO Fix numberOfCardsThisRound()
		verifiers.add(new WinningsVerifier(numberOfCardsThisRound() - 1));
		verifiers.add(new LastRoundNotFinished(rounds));
		return verifiers;
	}
}

interface Verifier<T> {
	public void verify(T element);
}

interface RoundVerifier {
	public void verify(Round round);
}

abstract class ConditionedVerifier<T> implements Verifier<T> {
	@Override
	public void verify(T element) {
		if (conditionOn(element)) {
			throw exceptionForCondition();
		}
	}

	public abstract boolean conditionOn(T element);

	public abstract RuntimeException exceptionForCondition();
}

class PlayersVerifier extends ConditionedVerifier<Round> {

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

class BidTotalVerifier extends ConditionedVerifier<Round> {

	private final int numberOfCardsThisRound;

	public BidTotalVerifier(int numberOfCardsThisRound) {
		this.numberOfCardsThisRound = numberOfCardsThisRound;
	}

	@Override
	public boolean conditionOn(Round round) {
		return numberOfCardsThisRound == round.totalBids();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new BidsEqualsNumberOfCardsInRound();
	}

}

class LastRoundFinishedVerifier extends ConditionedVerifier<Round> {
	private final List<Round> rounds;

	public LastRoundFinishedVerifier(List<Round> rounds) {
		this.rounds = rounds;
	}

	@Override
	public boolean conditionOn(Round element) {
		return rounds.size() > 0 && !rounds.get(rounds.size() - 1).isFinished();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new RoundAddedBeforeRoundResult();
	}

}

class WinningsVerifier extends ConditionedVerifier<RoundResult> {

	private final int numberOfCardsThisRound;

	public WinningsVerifier(int numberOfCardsThisRound) {
		this.numberOfCardsThisRound = numberOfCardsThisRound;
	}

	@Override
	public boolean conditionOn(RoundResult roundResult) {
		return numberOfCardsThisRound != roundResult.totalTricks();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new TricksWonDoesNotEqualNumberOfCardsInRound();
	}

}

class LastRoundNotFinished extends ConditionedVerifier<RoundResult> {

	private final List<Round> rounds;

	public LastRoundNotFinished(List<Round> rounds) {
		this.rounds = rounds;
	}

	@Override
	public boolean conditionOn(RoundResult element) {
		return rounds.size() == 0 || rounds.get(rounds.size() - 1).isFinished();
	}

	@Override
	public RuntimeException exceptionForCondition() {
		return new RoundResultAlreadyAdded();
	}

}