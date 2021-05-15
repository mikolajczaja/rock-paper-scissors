package com.rockpaperscissors.model;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStrategyTest {

	@Test
	void shouldReturnCorrectStrategyWhenNameIsCorrect() {
		assertThat(GameStrategy.getGameStrategyWithName("rock")).contains(GameStrategy.ROCK_ONLY);
		assertThat(GameStrategy.getGameStrategyWithName("paper")).contains(GameStrategy.PAPER_ONLY);
		assertThat(GameStrategy.getGameStrategyWithName("scissors")).contains(GameStrategy.SCISSORS_ONLY);
		assertThat(GameStrategy.getGameStrategyWithName("random")).contains(GameStrategy.RANDOM);
	}

	@Test
	void shouldReturnEmptyOptionalWhenNameIsInvalid() {
		assertThat(GameStrategy.getGameStrategyWithName("invalid name")).isEmpty();
	}

}