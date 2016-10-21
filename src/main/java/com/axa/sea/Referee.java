package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.RED;
import static com.axa.sea.Cell.TokenColour.UNKNOWN;
import static com.axa.sea.Cell.TokenColour.YELLOW;

public class Referee {

    private final Grid grid;
    private Cell.TokenColour currentPlayerColour;

    public Cell.TokenColour sayCurretPlayerColour(){
        return currentPlayerColour;
    }

    public static Referee startNewGame(){
        return new Referee();
    }

    public boolean inputCurrentPlayerAndCheckForEnd(Grid.Column column){
        grid.addToken(column, currentPlayerColour);
        if (GridAnalyzer.checkGrid(grid) != UNKNOWN){
            return true;
        }
        toggleCurrentPLayerColour();
        return false;
    }

    private void toggleCurrentPLayerColour() {
        switch (currentPlayerColour){
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

    private Referee(){
        currentPlayerColour = YELLOW;
        grid = new Grid();
    }

}
