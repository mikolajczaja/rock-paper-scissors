package com.rockpaperscissors.model;

import com.rockpaperscissors.config.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class responsible for executing a game of Rock-Paper-Scissors containing of specified number of rounds between two
 * players.
 */
@Slf4j
public class Game {
	private final int numberOfRounds;
	private final Player playerOne;
	private final Player playerTwo;

	/**
	 * @param applicationConfig {@link ApplicationConfig} containing number of rounds and strategies of both players.
	 *                          Said strategies are used to instantiate {@link Player}
	 *                          objects.
	 */
	public Game(ApplicationConfig applicationConfig) {
		numberOfRounds = applicationConfig.getNumberOfRounds();
		playerOne = new Player(applicationConfig.getPlayerOneStrategy());
		playerTwo = new Player(applicationConfig.getPlayerTwoStrategy());
	}

	/**
	 * Starts the game.
	 *
	 * @return results of the game in form of a map which keys are {@link GameRoundResult} which occurred during the
	 * game and values are the numbers of said result occurrences.
	 */
	public Map<GameRoundResult, Long> play() {
		List<GameRoundResult> results = new ArrayList<>();
		log.info("Starting game with " + numberOfRounds + " rounds, player one: " + playerOne + " , player two: " +
				playerTwo);

		for (var i = 1; i <= numberOfRounds; i++) {
			var gameRound = new GameRound(playerOne.move(), playerTwo.move());
			var roundResult = gameRound.getResult();
			results.add(roundResult);
			log.debug("Round Number: " + i + " " + gameRound + ", result: " + roundResult);
		}

		return results.stream().collect(Collectors.groupingBy(r -> r, Collectors.counting()));
	}
}