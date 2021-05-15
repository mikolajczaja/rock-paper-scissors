package com.rockpaperscissors.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

/**
 * This enum specifies all available strategies for determining which {@link Move} will the {@link Player} choose.
 */
@RequiredArgsConstructor
public enum GameStrategy {
	ROCK_ONLY("rock", () -> Move.ROCK),
	PAPER_ONLY("paper", () -> Move.PAPER),
	SCISSORS_ONLY("scissors", () -> Move.SCISSORS),
	RANDOM("random", Move::getRandomMove);

	private final String name;
	@Getter
	private final MoveSelector move;

	/**
	 * @return Optional of {@link GameStrategy} with name equal to parameter.
	 * <br> Empty Optional if {@link GameStrategy} with specified name does not exist.
	 */
	public static Optional<GameStrategy> getGameStrategyWithName(String name) {
		return Arrays.stream(values()).filter(strategy -> strategy.name.equals(name)).findAny();
	}
}
