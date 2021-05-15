package com.rockpaperscissors.app;

import com.rockpaperscissors.model.GameRoundResult;

import java.util.Map;
import java.util.StringJoiner;

/**
 * Summarizes the results: number of rounds won by player one, number of rounds won by player two,
 * number of draw rounds, overall winner.
 */
public class ResultSummarizer {

	private final long playerOneWinsCount;
	private final long playerTwoWinsCount;
	private final long drawsCount;
	private final long roundsCount;

	protected ResultSummarizer(Map<GameRoundResult, Long> resultsWithNumbersOfOccurrences) {
		this.playerOneWinsCount = getValueByKeyOrDefaultValueIfNotExists(GameRoundResult.PLAYER_ONE_WON,
				resultsWithNumbersOfOccurrences);
		this.playerTwoWinsCount = getValueByKeyOrDefaultValueIfNotExists(GameRoundResult.PLAYER_TWO_WON,
				resultsWithNumbersOfOccurrences);
		this.drawsCount = getValueByKeyOrDefaultValueIfNotExists(GameRoundResult.DRAW, resultsWithNumbersOfOccurrences);
		roundsCount = playerOneWinsCount + playerTwoWinsCount + drawsCount;
	}

	/**
	 * Prevents from {@link NullPointerException} on unboxing of {@link Long} value.
	 */
	private long getValueByKeyOrDefaultValueIfNotExists(GameRoundResult key, Map<GameRoundResult, Long> resultMap) {
		Long value = resultMap.get(key);
		if (value == null) {
			return 0L;
		} else {
			return value;
		}
	}

	/**
	 * Returns summary of results, line determining winner of the game always contains score in format like
	 * <b>(21-13)</b> or <b>(13-321)</b> with <b>left side for player one</b> and <b>right side for player two</b>
	 */
	protected String summarize() {
		var stringJoiner = new StringJoiner("\n");
		stringJoiner.add("Player One won " + getEventCountComparedToNumberOfAllRoundsSegment(playerOneWinsCount));
		stringJoiner.add("Player Two won " + getEventCountComparedToNumberOfAllRoundsSegment(playerTwoWinsCount));
		stringJoiner.add("Draw occurred in " + getEventCountComparedToNumberOfAllRoundsSegment(drawsCount));
		stringJoiner.add(getWinnerSummarySegment());
		return stringJoiner.toString();
	}

	private String getEventCountComparedToNumberOfAllRoundsSegment(long eventCount) {
		return String.format("%d of %d rounds", eventCount, roundsCount);
	}

	private String getWinnerSummarySegment() {
		String scoreSuffix = "(" + playerOneWinsCount + "-" + playerTwoWinsCount + ")";

		if (playerOneWinsCount > playerTwoWinsCount) {
			return "Winner is: Player One " + scoreSuffix;
		} else if (playerTwoWinsCount > playerOneWinsCount) {
			return "Winner is: Player Two " + scoreSuffix;
		} else {
			return "Game resulted in a draw " + scoreSuffix;
		}
	}
}