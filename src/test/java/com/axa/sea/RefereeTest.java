package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.RED;
import static com.axa.sea.Cell.TokenColour.UNKNOWN;
import static com.axa.sea.Cell.TokenColour.YELLOW;
import static com.axa.sea.Grid.Column.FIRST;
import static com.axa.sea.Grid.Column.FOURTH;
import static com.axa.sea.Grid.Column.SECOND;
import static com.axa.sea.Grid.Column.THIRD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class RefereeTest {

    @Test
    public void newRefereeMustStartWithYellowPlayer() {
        // Arrange + Act
        Referee referee = Referee.startNewGame();
        // Assert
        assertEquals("player should be yellow", YELLOW, referee.sayCurrentPlayerColour());
    }

    @Test
    public void whenNextMoveCurrentPlayerColourChanges() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST);
        assertEquals("player should be red", RED, referee.sayCurrentPlayerColour());

    }

    @Test
    public void when2MovesCurrentPlayerColourIsSame() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST);
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST);
        assertEquals("player should be red", YELLOW, referee.sayCurrentPlayerColour());
    }

    @Test
    public void currentInputMoveDoesNotEndGame() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        assertFalse("game should not end", referee.inputCurrentPlayerAndCheckForGameOver(FIRST));
    }

    @Test
    public void currentInputMoveEndsGameWhenFourColoursAreSame() {
        // Arrange
        Referee referee = createStubNewMoveToFirstColumYelloWIns();

        // Act + Assert
        assertTrue("game should end", referee.inputCurrentPlayerAndCheckForGameOver(FIRST));
    }

    private Referee createStubNewMoveToFirstColumYelloWIns() {
        Referee referee = Referee.startNewGame();
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST); //y
        referee.inputCurrentPlayerAndCheckForGameOver(SECOND); // r
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST); // Y
        referee.inputCurrentPlayerAndCheckForGameOver(SECOND); // r
        referee.inputCurrentPlayerAndCheckForGameOver(FIRST); //y
        referee.inputCurrentPlayerAndCheckForGameOver(SECOND);  // r
        return referee;
    }

    @Test(expected = GameOverException.class)
    public void noMoreMovesAreAllowedWhenGameIsOver() {
        //Arrange - yello wins by 4 same in first row in coloumns 1,2,3,4
        Referee referee = Referee.startNewGame();
        addMoves(referee, FIRST, FIRST, SECOND, FIRST, THIRD, FIRST, FOURTH);
        // Act - game is over - one more move is not allowed
        addMoves(referee, SECOND);
    }

    @Test(expected = ColumnIsFullException.class)
    public void currentInputMoveThrowsExceptionWhenColumnIsFull() {
        //Arrange
        createStubWithFirstColumnFull();

    }

    @Test
    @Ignore("not implemented yet")
    public void gameIsOverWhenGridIsFull() {

    }

    @Test
    @Ignore("not implemented yet")
    public void drawGridIsOK() {

    }

    @Test
    public void whenYellowWinsRefereeSaysIt() {
        //Arrange - yello wins by 4 same in first row in coloumns 1,2,3,4
        Referee referee = Referee.startNewGame();
        addMoves(referee, FIRST, FIRST, SECOND, FIRST, THIRD, FIRST, FOURTH);
        // Act
        assertEquals("Yellow should wins", YELLOW, referee.sayWinner());
    }

    @Test
    public void whenWeHaveNoWinnerSayWinnerShouldReturnUknown() {
        //Arrange
        Referee referee = Referee.startNewGame();
        addMoves(referee, FIRST, FIRST, SECOND, FIRST);
        // Act
        assertEquals("Nobody should wins", UNKNOWN, referee.sayWinner());
    }

    private Referee createStubWithFirstColumnFull() {
        Referee referee = Referee.startNewGame();
        addMoves(referee, FIRST, FIRST, FIRST, FIRST, FIRST, FIRST, FIRST);
        return referee;
    }

    private Referee addMoves(Referee referee, Grid.Column... columns) {
        for (Grid.Column column : columns) {
            referee.inputCurrentPlayerAndCheckForGameOver(column);
        }
        return referee;
    }

}