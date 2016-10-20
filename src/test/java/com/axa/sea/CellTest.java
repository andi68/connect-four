package com.axa.sea;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void assertThatNewCellsHasColourUnknown() {
        Assert.assertEquals(Cell.TokenColour.UNKNOWN, new Cell().getColour());
    }

}