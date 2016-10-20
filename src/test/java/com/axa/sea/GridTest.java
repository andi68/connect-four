package com.axa.sea;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GridTest {

    // test new grid has empty Cells
    @Test
    public void assertNewGridIsEmpty() {
        Grid grid = new Grid();
        assertTrue(grid.isEmpty());
    }

    // add Chip to Grid is ok, when
    // count in column is less 6

    @Test(expected = ColumnIsFullException.class)
    public void addChipFailsWhenColumnIsFull() {
        Grid grid = new Grid();
        grid.add(Grid.Column.FIRST, Grid.CellState.RED);
    }

    @Test
    public void addChipDoesNotThrowAnExceptionWhenColumnIsNotFull() {
        Grid grid = new Grid();
        grid.add(Grid.Column.FIRST, Grid.CellState.RED);
    }


    @Test
        public void addChipIncreaseColumnCountWhenColumnIsNotFull() {
            Grid grid = new Grid();
            grid.add(Grid.Column.FIRST, Grid.CellState.RED);
        }

}
