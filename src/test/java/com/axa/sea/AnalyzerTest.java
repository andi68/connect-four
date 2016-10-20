package com.axa.sea;

import static com.axa.sea.GridAnalyzer.checkColumns;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.axa.sea.Cell.TokenColour;
import org.junit.Test;

public class AnalyzerTest {

    @Test
    public void when4SameTokenInAColumnThanAnalyseReturnTrue() {
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
    public void whenDifferentTokenInAColumnThanAnalyseReturnFalse() {
        Grid grid = new Grid();
        // Arrange
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);
        grid.addToken(Grid.Column.SIXTH, TokenColour.RED);
        grid.addToken(Grid.Column.SIXTH, TokenColour.YELLOW);

        // Act and Assert
        assertFalse("should be false", checkColumns(grid));
    }

}
