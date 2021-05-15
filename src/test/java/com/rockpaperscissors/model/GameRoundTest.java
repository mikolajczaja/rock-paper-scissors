package com.rockpaperscissors.model;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.*;

public class GameRoundTest {


	@Test
	void shouldReturnCorrectResult(){
		assertThat(new GameRound(1,Move.PAPER,Move.ROCK).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);
		assertThat(new GameRound(1,Move.ROCK,Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);
		assertThat(new GameRound(1,Move.SCISSORS,Move.PAPER).getResult()).isEqualTo(GameRoundResult.PLAYER_ONE_WON);

		assertThat(new GameRound(1,Move.ROCK,Move.PAPER).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);
		assertThat(new GameRound(1,Move.SCISSORS,Move.ROCK).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);
		assertThat(new GameRound(1,Move.PAPER,Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.PLAYER_TWO_WON);

		assertThat(new GameRound(1,Move.PAPER,Move.PAPER).getResult()).isEqualTo(GameRoundResult.DRAW);
		assertThat(new GameRound(1,Move.ROCK,Move.ROCK).getResult()).isEqualTo(GameRoundResult.DRAW);
		assertThat(new GameRound(1,Move.SCISSORS,Move.SCISSORS).getResult()).isEqualTo(GameRoundResult.DRAW);
	}
}