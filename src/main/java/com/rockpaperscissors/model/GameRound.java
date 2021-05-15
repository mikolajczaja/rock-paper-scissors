package com.rockpaperscissors.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Represents a single round (one move from each player) of Rock-Paper-Scissors game.
 */
@Slf4j
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class GameRound {

	private final int roundNumber;
	private final Move playerOneMove;
	private final Move playerTwoMove;

	/**
	 * Determines what is the result of round basing on {@link Move} of each {@link Player}.
	 * @return {@link GameRoundResult} representing result of the round.
	 */
	protected GameRoundResult getResult(){
		GameRoundResult result;
		if(playerOneMove.equals(playerTwoMove)){
			result = GameRoundResult.DRAW;
		} else if(Move.getMoveInferiorTo(playerOneMove).equals(playerTwoMove)){
			result =  GameRoundResult.PLAYER_ONE_WON;
		} else {
			result =  GameRoundResult.PLAYER_TWO_WON;
		}

		log.debug(this +"\nResult: "+result);
		return result;
	}
}
