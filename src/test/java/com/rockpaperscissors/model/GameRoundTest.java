package com.rockpaperscissors.model;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRoundTest {


	@Test
	void shouldReturnCorrectResult() {
		assertThat(new GameRound(Move.PAPER, Move.ROCK).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);
		assertThat(new GameRound(Move.ROCK, Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);
		assertThat(new GameRound(Move.SCISSORS, Move.PAPER).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);

		assertThat(new GameRound(Move.ROCK, Move.PAPER).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);
		assertThat(new GameRound(Move.SCISSORS, Move.ROCK).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);
		assertThat(new GameRound(Move.PAPER, Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);

		assertThat(new GameRound(Move.PAPER, Move.PAPER).getResult()).isEqualTo(GameRoundResult.DRAW);
		assertThat(new GameRound(Move.ROCK, Move.ROCK).getResult()).isEqualTo(GameRoundResult.DRAW);
		assertThat(new GameRound(Move.SCISSORS, Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.DRAW);
	}
}