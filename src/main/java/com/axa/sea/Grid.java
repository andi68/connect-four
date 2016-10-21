package com.axa.sea;

public class Grid {

    private final int MAX_ROWS = 6;
    private final int MAX_COLUMNS = 7;

    final Cell[][] grid;

    public Grid() {
        grid = new Cell[MAX_COLUMNS][MAX_ROWS];
        for (int col = 0; col < MAX_COLUMNS; col++) {
            for (int row = 0; row < MAX_ROWS; row++) {
                grid[col][row] = new Cell();
            }
        }
    }

    public Grid(Cell[][] grid) {
        this.grid = grid;
    }

    public boolean isEmpty() {
        for (int col = 0; col < MAX_COLUMNS; col++) {
            for (int row = 0; row < MAX_ROWS; row++) {
                Cell cell = grid[col][row];
                if (cell != null) {
                    if (!cell.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void addToken(Column column, Cell.TokenColour tokenColour) {

        validateIfColumnlIsFull(grid[column.ordinal()]);

        for (int row = 0; row < MAX_ROWS; row++) {
            Cell cell = grid[column.ordinal()][row];
            if (cell.isEmpty()) {
                cell.setColour(tokenColour);
                break;
            }
        }
    }

    private void validateIfColumnlIsFull(Cell[] cells) {
        Cell cell = cells[MAX_ROWS - 1];
        if (!cell.isEmpty()) {
            throw new ColumnIsFullException("col is full");
        }
    }

    enum Column {
        FIRST,
        SECOND,
        THIRD,
        FOURTH,
        FIFTH,
        SIXTH
    }

    int getColoumnCount(Column column) {
        //return 1;
        int count = 0;
        for (int row = 0; row < MAX_ROWS; row++) {
            Cell cell = grid[column.ordinal()][row];
            if (cell.isEmpty()) {
                break;
            }
            count++;
        }
        return count;
    }

    int getMaxRows(){
        return grid[0].length;
    }

    int getMaxCols(){
        return grid.length;
    }

    public Cell[][] getGrid() {
        return grid;
    }

}
