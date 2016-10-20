package com.axa.sea;

public class Cell {

    CellState state;

    public Cell() {
        state = CellState.EMPTY;
    }

    public Cell(CellState cellState) {
        this.state = cellState;
    }

    public CellState getState() {
        return state;
    }

    public void changeState(CellState cellState) {
        if (this.state != CellState.EMPTY) {
            throw new IllegalArgumentException("cell must not be empty");
        }

        state = cellState;
    }

    enum CellState {

        EMPTY,
        RED,
        YELLOW;
    }
}