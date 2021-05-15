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

	public Game(ApplicationConfig applicationConfig){
		numberOfRounds= applicationConfig.getNumberOfRounds();
		playerOne= new Player(applicationConfig.getPlayerOneStrategy());
		playerTwo= new Player(applicationConfig.getPlayerTwoStrategy());
	}

	public Map<GameRoundResult, Long> play(){
		List<GameRoundResult> results=new ArrayList<>();
		log.info("Starting game with "+numberOfRounds+" rounds, player one: "+playerOne+ " , player two: "+playerTwo);

		for (var i = 1; i <= numberOfRounds; i++) {
			log.debug("Commencing round "+i);
			var gameRound = new GameRound(i, playerOne.move(), playerTwo.move());
			results.add(gameRound.getResult());
		}

		var resultsWithNumbersOfOccurrences =
				results.stream().collect(Collectors.groupingBy(r -> r,Collectors.counting()));
		log.info(String.valueOf(resultsWithNumbersOfOccurrences));

		return resultsWithNumbersOfOccurrences;
	}
}