package com.rockpaperscissors.app;

import com.rockpaperscissors.model.GameRoundResult;
import com.rockpaperscissors.model.GameStrategy;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ResultSummarizerTest {

	@DataProvider(name = "summaryDataProvider")
	public static Object[][] summaryDataWithWinnerSegments() {
		return new Object[][]{{24L,13L,12215L,"Winner is: Player One"},{1L,2L,0L,"Winner is: Player Two"},
				{3L,3L,5L,"Game resulted in a draw"}};
	}

	@Test(dataProvider = "summaryDataProvider")
	void shouldReturnCorrectSummaryWhenValidResultMapIsSupplied(long playerOneWinsCount, long playerTwoWinsCount,
																long drawsCount, String winnerSegment) {
		String expectedString = getSummaryStringWithoutLastLine(playerOneWinsCount,playerTwoWinsCount,drawsCount)+
				"\n"+winnerSegment+" ("+playerOneWinsCount+"-"+playerTwoWinsCount+")";

		Map<GameRoundResult, Long> resultMap = Map.of(GameRoundResult.PLAYER_ONE_WON, playerOneWinsCount,
				GameRoundResult.PLAYER_TWO_WON,
				playerTwoWinsCount, GameRoundResult.DRAW, drawsCount);

		var resultSummarizer = new ResultSummarizer(resultMap);
		var actualResult = resultSummarizer.summarize();
		assertThat(actualResult).isEqualTo(expectedString);
	}

	private String getSummaryStringWithoutLastLine(long playerOneWinsCount, long playerTwoWinsCount, long drawsCount){
		long allRoundsCount = playerOneWinsCount + playerTwoWinsCount + drawsCount;

		return "Player One won " + playerOneWinsCount + " of " + allRoundsCount +" rounds"+
				"\nPlayer Two won " + playerTwoWinsCount + " of " + allRoundsCount +" rounds"+
				"\nDraw occurred in " + drawsCount + " of " + allRoundsCount+" rounds";
	}
}