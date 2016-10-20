package com.axa.sea;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    Cell cell;

    @Test
    public void cellShouldHaveStateEmptyAtStart() {
        cell = new Cell();
        Assert.assertEquals(Cell.CellState.EMPTY, cell.getState());
    }

    @Test
    public void cellCanChangeStateWhenItIsEmpty() {
        cell = new Cell();
        // Act
        cell.changeState(Cell.CellState.YELLOW);
        // Assert
        Assert.assertEquals(Cell.CellState.YELLOW, cell.getState());
    }

    @Test(expected = IllegalArgumentException.class)
    public void cellCannotChangeStateWhenItIsNotEmpty() {
        // Arrange
        cell = new Cell(Cell.CellState.YELLOW);
        // Act
        cell.changeState(Cell.CellState.YELLOW);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cellCannotChangeStateWhenGivenStateIsEmpty() {
        // Arrange
        cell = new Cell(Cell.CellState.YELLOW);
        // Act
        cell.changeState(Cell.CellState.EMPTY);
    }

}
