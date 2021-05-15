package com.rockpaperscissors.model;

import java.util.Map;
import java.util.Random;

/**
 * Enum representing valid moves in Rock-Paper-Scissors game.
 */
public enum Move {
	ROCK,
	PAPER,
	SCISSORS;

	//Using map as a workaround for forward reference
	private static final Map<Move, Move> movesToInferiorMovesMap = Map.of(
			ROCK, SCISSORS,
			PAPER, ROCK,
			SCISSORS, PAPER);

	private static final Random RANDOM = new Random();

	/**
	 * @return Randomly selected value of this enum.
	 */
	protected static Move getRandomMove() {
		var randomMoveIndex = RANDOM.nextInt(3);
		return values()[randomMoveIndex];
	}

	/**
	 * @return a Move that is inferior (losing) to the Move passed as parameter
	 */
	protected static Move getMoveInferiorTo(Move move) {
		return movesToInferiorMovesMap.get(move);
	}
}