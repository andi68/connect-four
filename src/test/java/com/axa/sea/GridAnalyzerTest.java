package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.YELLOW;
import static com.axa.sea.GridAnalyzer.checkColumns;
import static com.axa.sea.GridAnalyzer.checkDiagonalLeft2Right;
import static com.axa.sea.GridAnalyzer.checkDiagonalRight2Left;
import static com.axa.sea.GridAnalyzer.checkRows;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axa.sea.Cell.TokenColour;
import org.junit.Ignore;
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
        grid.addToken(Grid.Column.FIFTH, YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, YELLOW);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, YELLOW);
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
        grid.addToken(Grid.Column.SIXTH, YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, YELLOW);

        // Act and Assert
        assertTrue("should be true", checkColumns(grid));
    }

    @Test
    public void whenDifferentTokenOfAColumnThanAnalyseReturnFalse() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, YELLOW);

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
        grid.addToken(Grid.Column.FIRST, YELLOW);
        grid.addToken(Grid.Column.SECOND, YELLOW);
        grid.addToken(Grid.Column.THIRD, YELLOW);
        grid.addToken(Grid.Column.FOURTH, YELLOW);

        // Act and Assert
        assertTrue("should be true", checkRows(grid));
    }

    @Test
    public void when4SameTokenInMiddleOfARowThenAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.FIRST, YELLOW);
        grid.addToken(Grid.Column.SECOND, TokenColour.RED);
        grid.addToken(Grid.Column.THIRD, TokenColour.RED);
        grid.addToken(Grid.Column.FOURTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, YELLOW);

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
        // Arrange
        Grid grid = createStubGridWithSameLeftDiagonals1();
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
        grid.addToken(Grid.Column.FIFTH, YELLOW);
        grid.addToken(Grid.Column.SIXTH, YELLOW);
    }

    private void createStubGridWithSameLeftDiagonals(Grid grid) {
        grid.addToken(Grid.Column.FIRST, YELLOW);

        grid.addToken(Grid.Column.SECOND, YELLOW);
        grid.addToken(Grid.Column.SECOND, YELLOW);

        grid.addToken(Grid.Column.THIRD, YELLOW);
        grid.addToken(Grid.Column.THIRD, YELLOW);
        grid.addToken(Grid.Column.THIRD, YELLOW);

        grid.addToken(Grid.Column.FOURTH, YELLOW);
        grid.addToken(Grid.Column.FOURTH, YELLOW);
        grid.addToken(Grid.Column.FOURTH, YELLOW);
        grid.addToken(Grid.Column.FOURTH, YELLOW);
    }

    // Test Diagonal from right 2 left

    @Test

    public void emptyColumnDoesNotWinByDiagonalCheckRight2Left() {
        Grid grid = new Grid();
        // Act and Assert
        assertFalse("should be false", checkDiagonalRight2Left(grid));
    }

    @Test
    public void when4SameTokenFromRight2LeftThanAnalyseReturnTrue() {
        // Arrange
        Grid grid = createStubGridWithSameRightDiagonals();
        // Act and Assert
        assertTrue("should be true", checkDiagonalRight2Left(grid));
    }

    @Test
    @Ignore
    public void when4SameTokenFromLeft2Right1ThanAnalyseReturnTrueRight2Left() {
        // Arrange
        Grid grid = createStubGridWithSameLeftDiagonals1();
        // Act and Assert
        assertTrue("should be true", checkDiagonalLeft2Right(grid));
    }

    @Test
    @Ignore
    public void whenNo4SameDiagonalsThanAnalyseReturnFalseRight2Left() {
        Grid grid = new Grid();
        // Arrange
        createStubGridNoSameTokens(grid);
        // Act and Assert
        assertFalse("should be false", checkDiagonalLeft2Right(grid));
    }

    private Grid createStubGridWithSameRightDiagonals() {
        Grid grid = new Grid();
        addRow2Grid(grid, Grid.Column.FIFTH, YELLOW);
        addRow2Grid(grid, Grid.Column.FOURTH, YELLOW, YELLOW);
        addRow2Grid(grid, Grid.Column.THIRD, YELLOW, YELLOW, YELLOW);
        addRow2Grid(grid, Grid.Column.SECOND, YELLOW, YELLOW, YELLOW, YELLOW);
        return grid;
    }

    private Grid createStubGridWithSameLeftDiagonals1() {
        Grid grid = new Grid();
        addRow2Grid(grid, Grid.Column.THIRD, YELLOW);
        addRow2Grid(grid, Grid.Column.FOURTH, YELLOW, YELLOW);
        addRow2Grid(grid, Grid.Column.FIFTH, YELLOW, YELLOW, YELLOW);
        addRow2Grid(grid, Grid.Column.SIXTH, YELLOW, YELLOW, YELLOW, YELLOW);
        return grid;
    }

    private void addRow2Grid(Grid grid, Grid.Column column, TokenColour... colours) {
        for (TokenColour col : colours) {
            grid.addToken(column, col);
        }
    }

}
