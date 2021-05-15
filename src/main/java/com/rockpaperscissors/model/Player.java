package com.rockpaperscissors.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents a player of Rock-Paper-Scissors game.
 */
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {
	private final GameStrategy gameStrategy;

	protected Move move(){
		return gameStrategy.getMove().execute();
	}
}
