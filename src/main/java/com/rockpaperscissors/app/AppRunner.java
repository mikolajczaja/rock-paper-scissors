package com.rockpaperscissors.app;

import com.rockpaperscissors.config.ApplicationConfig;
import com.rockpaperscissors.config.DefaultApplicationProperties;
import com.rockpaperscissors.model.Game;
import com.rockpaperscissors.model.GameStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppRunner {

	/**
	 * Main method used for running a Paper-Rock-Scissors game.
	 */
	public static void main(String[] args) {
		var defaultProperties = new DefaultApplicationProperties(100, GameStrategy.PAPER_ONLY, GameStrategy.RANDOM);
		var applicationConfig = new ApplicationConfig("application.properties", defaultProperties);
		applicationConfig.initialize();
		var game = new Game(applicationConfig);

		String summary = new ResultSummarizer(game.play()).summarize();
		log.info("Game finished, summary:\n" + summary);
	}
}
