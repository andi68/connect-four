package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.RED;
import static com.axa.sea.Cell.TokenColour.UNKNOWN;
import static com.axa.sea.Cell.TokenColour.YELLOW;

public class Referee {

    private final Grid grid;
    private Cell.TokenColour currentPlayerColour;
    private boolean isGameOver;

    public Cell.TokenColour sayCurrentPlayerColour() {
        return currentPlayerColour;
    }

    public static Referee startNewGame() {
        return new Referee();
    }

    public boolean inputCurrentPlayerAndCheckForGameOver(Grid.Column column) {
        if (isGameOver) {
            throw new GameOverException("the game was already finished");
        }
        grid.addToken(column, currentPlayerColour);
        if (GridAnalyzer.checkGrid(grid) != UNKNOWN) {
            isGameOver = true;
            return true;
        }
        toggleCurrentPLayerColour();
        return false;
    }

    public Cell.TokenColour sayWinner() {
        return isGameOver ? currentPlayerColour : UNKNOWN;
    }

    private void toggleCurrentPLayerColour() {
        switch (currentPlayerColour) {
            case RED:
                currentPlayerColour = YELLOW;
                break;
            case YELLOW:
                currentPlayerColour = RED;
                break;
            default:
                throw new IllegalArgumentException("unkown player colour cant be toggled");
        }
    }

    private Referee() {
        currentPlayerColour = YELLOW;
        grid = new Grid();
        isGameOver = false;
    }

}
