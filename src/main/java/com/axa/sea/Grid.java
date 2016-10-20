package com.axa.sea;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    List<List<Cell>> grid = new ArrayList<>();

    public boolean isEmpty() {
        for (List<Cell> cols : grid
            ) {
            return cols.isEmpty();
            //                for (Cell cell : cols
            //                    ) {
            //                    if (cell.getState() != Cell.CellState.EMPTY)
            //                        return false;
            //                }
        }
        return true;
    }

    public void add(Column column, CellState cellState) {

    }

    enum CellState {
        RED,
        YELLOW;
    }

    enum Column {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH;
    }

}
