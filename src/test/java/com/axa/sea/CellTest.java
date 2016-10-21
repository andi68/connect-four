package com.axa.sea;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void assertThatNewCellsHasUnknownColour() {
        Assert.assertEquals(Cell.TokenColour.UNKNOWN, new Cell().getColour());
    }

    @Test
    public void assertThatNewCellsIsEmptyl() {
        Cell cell = new Cell();
        Assert.assertTrue(cell.isEmpty());
    }

    @Test
    public void assertThatCellsWithUnknownColoureIsEmptyl() {
        Cell cell = new Cell();
        cell.setColour(Cell.TokenColour.UNKNOWN);
        Assert.assertTrue(cell.isEmpty());
    }

    @Test
    public void assertThatCellsWithoutUnknownColourIsNotEmpty() {
        Cell cell = new Cell();
        cell.setColour(Cell.TokenColour.RED);
        Assert.assertFalse(cell.isEmpty());
    }

}