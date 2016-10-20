package com.axa.sea;

import static com.axa.sea.GridAnalyzer.checkColumns;
import static com.axa.sea.GridAnalyzer.checkDiagonalLeft2Right;
import static com.axa.sea.GridAnalyzer.checkRows;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axa.sea.Cell.TokenColour;
import org.junit.Test;

public class GridAnalyzerTest {

    // Test Columns

    @Test
    public void emptyColumnDoesNotWin() {
        Grid grid = new Grid();
        // Act and Assert
        assertFalse("should be false", checkColumns(grid));
    }

    @Test
    public void when4SameTokenAtBeginningOfAColumnThanAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);

        // Act and Assert
        assertTrue("should be true", checkColumns(grid));
    }

    @Test
    public void threeSameColumnsMustFailEvenWhenPrioColumsHasSameColour() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);

        // Act and Assert
        assertFalse("should be false", checkColumns(grid));
    }

    @Test
    public void when4SameTokenInMiddleOfAColumnThanAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);

        // Act and Assert
        assertTrue("should be true", checkColumns(grid));
    }

    @Test
    public void whenDifferentTokenOfAColumnThanAnalyseReturnFalse() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);

        // Act and Assert
        assertFalse("should be false", checkColumns(grid));
    }

    // Test Rows

    @Test
    public void emptyRowsDoesNotWin() {
        Grid grid = new Grid();
        // Act and Assert
        assertFalse("should be false", checkRows(grid));
    }

    @Test
    public void when4SameTokenAtBeginningOfARowThanAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.FIRST, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SECOND, TokenColour.YELLOW);
        grid.addToken(Grid.Column.THIRD, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);

        // Act and Assert
        assertTrue("should be true", checkRows(grid));
    }

    @Test
    public void when4SameTokenInMiddleOfARowThenAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.FIRST, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SECOND, TokenColour.RED);
        grid.addToken(Grid.Column.THIRD, TokenColour.RED);
        grid.addToken(Grid.Column.FOURTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);

        // Act and Assert
        assertTrue("should be true", checkRows(grid));
    }

    @Test
    public void whenDifferentTokenOfARowThanAnalyseReturnFalse() {
        Grid grid = new Grid();
        // Arrange
        createStubGridNoSameTokens(grid);

        // Act and Assert
        assertFalse("should be false", checkRows(grid));
    }

    // Test Diagonal from left 2 right

    @Test
    public void emptyColumnDoesNotWinByDiagonalCheck() {
        Grid grid = new Grid();
        // Act and Assert
        assertFalse("should be false", checkDiagonalLeft2Right(grid));
    }

    @Test
    public void when4SameTokenFromLeft2RightThanAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        createStubGridWithSameLeftDiagonals(grid);

        // Act and Assert
        assertTrue("should be true", checkDiagonalLeft2Right(grid));
    }

    @Test
    public void when4SameTokenFromLeft2Right1ThanAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        createStubGridWithSameLeftDiagonals1(grid);

        // Act and Assert
        assertTrue("should be true", checkDiagonalLeft2Right(grid));
    }

    @Test
    public void whenNo4SameDiagonalsThanAnalyseReturnFalse() {
        Grid grid = new Grid();
        // Arrange
        createStubGridNoSameTokens(grid);
        // Act and Assert
        assertFalse("should be false", checkDiagonalLeft2Right(grid));
    }

    private void createStubGridNoSameTokens(Grid grid) {
        grid.addToken(Grid.Column.THIRD, TokenColour.RED);
        grid.addToken(Grid.Column.FOURTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
    }

    private void createStubGridWithSameLeftDiagonals(Grid grid) {
        grid.addToken(Grid.Column.FIRST, TokenColour.YELLOW);

        grid.addToken(Grid.Column.SECOND, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SECOND, TokenColour.YELLOW);

        grid.addToken(Grid.Column.THIRD, TokenColour.YELLOW);
        grid.addToken(Grid.Column.THIRD, TokenColour.YELLOW);
        grid.addToken(Grid.Column.THIRD, TokenColour.YELLOW);

        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);
    }

    private void createStubGridWithSameLeftDiagonals1(Grid grid) {
        grid.addToken(Grid.Column.THIRD, TokenColour.YELLOW);

        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FOURTH, TokenColour.YELLOW);

        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.YELLOW);

        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
    }

}
