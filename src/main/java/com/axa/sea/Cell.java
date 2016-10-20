package com.axa.sea;

public class Cell {

    private TokenColour colour;

    public Cell() {
        this.colour = TokenColour.UNKNOWN;
    }

    public boolean isEmpty() {
        return this.colour == TokenColour.UNKNOWN;
    }

    public TokenColour getColour() {
        return colour;
    }

    public void setColour(TokenColour colour) {
        this.colour = colour;
    }

    enum TokenColour {
        UNKNOWN,
        RED,
        YELLOW
    }
}
