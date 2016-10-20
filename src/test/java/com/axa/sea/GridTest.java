package com.axa.sea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {

    private final int MAX_ROWS = 6;

    @Test
    public void assertNewGridIsEmpty() {
        Grid grid = new Grid();
        assertTrue(grid.isEmpty());
    }

    @Test
    public void assertGridWithTokensIsNotEmpty() {
        Grid grid = new Grid();
        grid.addToken(Grid.Column.FIRST, Cell.TokenColour.RED);
        assertFalse(grid.isEmpty());
    }

    @Test(expected = ColumnIsFullException.class)
    public void addChipFailsWhenColumnIsFull() {
        Grid grid = new Grid();
        for (int i = 0; i < MAX_ROWS + 1; i++) {
            grid.addToken(Grid.Column.FIRST, Cell.TokenColour.RED);
        }
    }

    @Test
    public void addChipIncreaseColumnCount() {
        Grid grid = new Grid();
        // Act
        grid.addToken(Grid.Column.FIRST, Cell.TokenColour.RED);
        // Assert
        assertEquals(1, grid.getColoumnCount(Grid.Column.FIRST));
    }

    @Test
    public void addChipDoesNotThrowAnExceptionWhenColumnIsNotFull() {
        Grid grid = new Grid();
        grid.addToken(Grid.Column.FIRST, Cell.TokenColour.YELLOW);
        // do not fail
    }

}
