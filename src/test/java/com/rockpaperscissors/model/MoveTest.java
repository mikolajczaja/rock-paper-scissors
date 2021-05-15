package com.rockpaperscissors.model;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveTest {

	@Test
	void shouldReturnCorrectInferiorMove() {
		assertThat(Move.getMoveInferiorTo(Move.ROCK)).isEqualTo(Move.SCISSORS);
		assertThat(Move.getMoveInferiorTo(Move.PAPER)).isEqualTo(Move.ROCK);
		assertThat(Move.getMoveInferiorTo(Move.SCISSORS)).isEqualTo(Move.PAPER);
	}

	/**
	 * Main purpose of this test is to check if random does not exceed array bounds.
	 */
	@Test
	void shouldNotThrowAnyExceptionWhenGetRandomMoveIsCalledMultipleTimes() {
		for (int i = 0; i < 300; i++) {
			assertThat(Move.getRandomMove()).isInstanceOf(Move.class);
		}
	}
}