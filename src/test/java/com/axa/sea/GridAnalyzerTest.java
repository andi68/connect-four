package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.RED;
import static com.axa.sea.Cell.TokenColour.UNKNOWN;
import static com.axa.sea.Cell.TokenColour.YELLOW;
import static com.axa.sea.Grid.Column.FIRST;
import static com.axa.sea.Grid.Column.FOURTH;
import static com.axa.sea.Grid.Column.SECOND;
import static com.axa.sea.Grid.Column.SIXTH;
import static com.axa.sea.Grid.Column.THIRD;
import static com.axa.sea.GridAnalyzer.checkColumns;
import static com.axa.sea.GridAnalyzer.checkDiagonalLeft2Right;
import static com.axa.sea.GridAnalyzer.checkDiagonalRight2Left;
import static com.axa.sea.GridAnalyzer.checkGrid;
import static com.axa.sea.GridAnalyzer.checkRows;
import static org.junit.Assert.assertEquals;

import com.axa.sea.Cell.TokenColour;
import org.junit.Test;

public class GridAnalyzerTest {

    // Test Grid

    @Test
    public void emptyGridDoesNotHaveAWinner() {
        Grid grid = new Grid();
        assertEquals("should be unknown", TokenColour.UNKNOWN, checkGrid(grid));
    }

    @Test
    public void when4SameOfRedTokensAreInAColumnYellowdHasWon() {
        Grid grid = createStubGridWith4SameYellowInAColum();
        // Act and Assert
        assertEquals("should be yellow", TokenColour.YELLOW, checkGrid(grid));
    }

    @Test
    public void when4SameOfRedTokensAreInARowYellowdHasWon() {
        Grid grid = createStubGridWith4SameYellowInARow();
        // Act and Assert
        assertEquals("should be yellow", TokenColour.YELLOW, checkGrid(grid));
    }

    @Test
    public void when4SameOfRedTokensAreInALeft2RightDiagonalYellowdHasWon() {
        Grid grid = createStubGridWithSameYellowLeftDiagonals1();
        // Act and Assert
        assertEquals("should be yellow", TokenColour.YELLOW, checkGrid(grid));
    }

    @Test
    public void when4SameOfRedTokensAreInARightToLeftDiagonalYellowdHasWon() {
        Grid grid = createStubGridWithSameYellowRightDiagonals();
        // Act and Assert
        assertEquals("should be yellow", TokenColour.YELLOW, checkGrid(grid));
    }




    // Test Columns

    @Test
    public void emptyColumnDoesNotWin() {
        Grid grid = new Grid();
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkColumns(grid));
    }

    @Test
    public void when4SameTokenAtBeginningOfAColumnThanAnalyseReturnTrue() {
        Grid grid = createStubGridWith4SameYellowInAColum();
        // Act and Assert
        assertEquals("should be yellow", YELLOW, checkColumns(grid));
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
        assertEquals("should be unknonw", UNKNOWN, checkColumns(grid));
    }

    @Test
    public void when4SameTokenInMiddleOfAColumnThanAnalyseReturnTrue() {
        // Arrange
        Grid grid = new Grid();
        addColumn2Grid(grid, SIXTH, YELLOW, RED, RED, RED, RED, YELLOW);
        // Act and Assert
        assertEquals("should be red", RED, checkColumns(grid));
    }

    @Test
    public void whenDifferentTokenOfAColumnThanAnalyseReturnFalse() {
        // Arrange
        Grid grid = new Grid();
        addColumn2Grid(grid, SIXTH, RED, YELLOW, RED, YELLOW);
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkColumns(grid));
    }

    // Test Rows

    @Test
    public void emptyRowsDoesNotWin() {
        Grid grid = new Grid();
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkRows(grid));
    }

    @Test
    public void when4SameTokenAtBeginningOfARowThanAnalyseReturnTrue() {
        Grid grid = createStubGridWith4SameYellowInARow();
        // Act and Assert
        assertEquals("should be yellow", YELLOW, checkRows(grid));
    }

    @Test
    public void when4SameTokenInMiddleOfARowThenAnalyseReturnTrue() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(FIRST, YELLOW);
        grid.addToken(SECOND, TokenColour.RED);
        grid.addToken(THIRD, TokenColour.RED);
        grid.addToken(FOURTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, YELLOW);

        // Act and Assert
        assertEquals("should be red", RED, checkRows(grid));
    }

    @Test
    public void whenDifferentTokenOfARowThanAnalyseReturnFalse() {
        // Arrange
        Grid grid = createStubGridNoSameTokens();
        // Act and Assert
        assertEquals("should be unknowns", UNKNOWN, checkRows(grid));
    }

    // Test Diagonal from left 2 right

    @Test
    public void emptyColumnDoesNotWinByDiagonalCheck() {
        Grid grid = new Grid();
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkDiagonalLeft2Right(grid));
    }

    @Test
    public void when4SameTokenFromLeft2RightThanAnalyseReturnTrue() {
        // Arrange
        Grid grid = createStubGridWithSameYellowLeftDiagonals();
        // Act and Assert
        assertEquals("should be yellow", YELLOW, checkDiagonalLeft2Right(grid));
    }

    @Test
    public void when4SameTokenFromLeft2Right1ThanAnalyseReturnTrue() {
        // Arrange
        Grid grid = createStubGridWithSameYellowLeftDiagonals1();
        // Act and Assert
        assertEquals("should be yellow", YELLOW, checkDiagonalLeft2Right(grid));
    }

    @Test
    public void whenNo4SameDiagonalsThanAnalyseReturnFalse() {
        // Arrange
        Grid grid = createStubGridNoSameTokens();
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkDiagonalLeft2Right(grid));
    }

    private Grid createStubGridNoSameTokens() {
        Grid grid = new Grid();
        grid.addToken(THIRD, TokenColour.RED);
        grid.addToken(FOURTH, TokenColour.RED);
        grid.addToken(Grid.Column.FIFTH, YELLOW);
        grid.addToken(Grid.Column.SIXTH, YELLOW);
        return grid;
    }

    private Grid createStubGridWithSameYellowLeftDiagonals() {
        Grid grid = new Grid();
        addColumn2Grid(grid, FIRST, YELLOW);
        addColumn2Grid(grid, SECOND, YELLOW, YELLOW);
        addColumn2Grid(grid, THIRD, YELLOW, YELLOW, YELLOW);
        addColumn2Grid(grid, FOURTH, YELLOW, YELLOW, YELLOW, YELLOW);
        return grid;
    }

    // Test Diagonal from right 2 left

    @Test

    public void emptyColumnDoesNotWinByDiagonalCheckRight2Left() {
        Grid grid = new Grid();
        // Act and Assert
        assertEquals("should be unknown", UNKNOWN, checkDiagonalRight2Left(grid));
    }

    @Test
    public void when4SameTokenFromRight2LeftThanAnalyseReturnTrue() {
        // Arrange
        Grid grid = createStubGridWithSameYellowRightDiagonals();
        // Act and Assert
        assertEquals("should be yellow", YELLOW, checkDiagonalRight2Left(grid));
    }

    private Grid createStubGridWith4SameYellowInARow() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(FIRST, YELLOW);
        grid.addToken(SECOND, YELLOW);
        grid.addToken(THIRD, YELLOW);
        grid.addToken(FOURTH, YELLOW);
        return grid;
    }

    private Grid createStubGridWith4SameYellowInAColum() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(SIXTH, YELLOW);
        grid.addToken(SIXTH, YELLOW);
        grid.addToken(SIXTH, YELLOW);
        grid.addToken(SIXTH, YELLOW);
        return grid;
    }

    private Grid createStubGridWithSameYellowRightDiagonals() {
        Grid grid = new Grid();
        addColumn2Grid(grid, Grid.Column.FIFTH, YELLOW);
        addColumn2Grid(grid, FOURTH, YELLOW, YELLOW);
        addColumn2Grid(grid, THIRD, YELLOW, YELLOW, YELLOW);
        addColumn2Grid(grid, SECOND, YELLOW, RED, YELLOW, YELLOW);
        return grid;
    }

    private Grid createStubGridWithSameYellowLeftDiagonals1() {
        Grid grid = new Grid();
        addColumn2Grid(grid, THIRD, YELLOW);
        addColumn2Grid(grid, FOURTH, YELLOW, YELLOW);
        addColumn2Grid(grid, Grid.Column.FIFTH, YELLOW, YELLOW, YELLOW);
        addColumn2Grid(grid, Grid.Column.SIXTH, YELLOW, YELLOW, RED, YELLOW);
        return grid;
    }

    private void addColumn2Grid(Grid grid, Grid.Column column, TokenColour... colours) {
        for (TokenColour col : colours) {
            grid.addToken(column, col);
        }
    }

}
