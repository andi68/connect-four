package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.RED;
import static com.axa.sea.Cell.TokenColour.YELLOW;
import static com.axa.sea.Grid.Column.FIRST;
import static com.axa.sea.Grid.Column.SECOND;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RefereeTest {

    @Test
    public void newRefereeMustStartWithYellowPlayer() {
        // Arrange + Act
        Referee referee = Referee.startNewGame();
        // Assert
        assertEquals("player should be yellow", YELLOW, referee.sayCurretPlayerColour());
    }

    @Test
    public void whenNextMoveCurrentPlayerColourChanges() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        referee.inputCurrentPlayerAndCheckForEnd(FIRST);
        assertEquals("player should be red", RED, referee.sayCurretPlayerColour());

    }

    @Test
    public void when2MovesCurrentPlayerColourIsSame() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        referee.inputCurrentPlayerAndCheckForEnd(FIRST);
        referee.inputCurrentPlayerAndCheckForEnd(FIRST);
        assertEquals("player should be red", YELLOW, referee.sayCurretPlayerColour());
    }

    @Test
    public void currentInputMoveDoesNotEndGame() {
        // Arrange
        Referee referee = Referee.startNewGame();
        // Act
        assertFalse("game should not end", referee.inputCurrentPlayerAndCheckForEnd(FIRST));
    }

    @Test
    public void currentInputMoveEndsGameWhenFourColoursAreSame() {
        // Arrange
        Referee referee = createStubNewMoveToFirstColumYelloWIns();

        // Act + Assert
        assertTrue("game should end", referee.inputCurrentPlayerAndCheckForEnd(FIRST));
    }

    private Referee createStubNewMoveToFirstColumYelloWIns() {
        Referee referee = Referee.startNewGame();
        referee.inputCurrentPlayerAndCheckForEnd(FIRST); //y
        referee.inputCurrentPlayerAndCheckForEnd(SECOND); // r
        referee.inputCurrentPlayerAndCheckForEnd(FIRST); // Y
        referee.inputCurrentPlayerAndCheckForEnd(SECOND); // r
        referee.inputCurrentPlayerAndCheckForEnd(FIRST); //y
        referee.inputCurrentPlayerAndCheckForEnd(SECOND);  // r
        return referee;
    }

    @Test(expected = GameOverException.class)
    public void noMoreMovesAreAllowedWhenGameIsOver() {

    }

    @Test(expected = ColumnIsFullException.class)
    public void currentInputMoveThrowsExceptionWhenColumnIsFull() {
        //Arrange
        createStubWithFirstColumnFull();

    }

    private Referee createStubWithFirstColumnFull() {
        Referee referee = Referee.startNewGame();
        addMoves(referee, FIRST, FIRST, FIRST, FIRST, FIRST, FIRST, FIRST);
        return referee;
    }

    private Referee addMoves(Referee referee, Grid.Column... columns) {
        for (Grid.Column column : columns) {
            referee.inputCurrentPlayerAndCheckForEnd(column);
        }
        return referee;
    }

}