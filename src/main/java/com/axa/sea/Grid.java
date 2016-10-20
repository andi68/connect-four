package com.axa.sea;

public class Grid {

    private final int MAX_ROWS = 6;
    private final int MAX_COLUMNS = 7;

    Cell[][] grid;

    public Grid() {
        grid = new Cell[MAX_COLUMNS][MAX_ROWS];
        for (int col = 0; col < MAX_COLUMNS; col++) {
            for (int row = 0; row < MAX_ROWS; row++) {
                grid[col][row] = new Cell();
            }
        }
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

    public void addToken(Column column, TokenColour tokenColour) {

        validateIfColIsFull(grid[column.ordinal()]);

        for (int row = 0; row < MAX_ROWS; row++) {
            Cell cell = grid[column.ordinal()][row];
            if (cell.isEmpty()) {
                cell.setColour(tokenColour);
                break;
            }
        }
    }

    private void validateIfColIsFull(Cell[] cells) {
        Cell cell = cells[MAX_ROWS-1];
        if (!cell.isEmpty()) {
            throw new ColumnIsFullException("col is full");
        }
    }

    public class Cell {

        private TokenColour colour = TokenColour.UNKNOWN;

        public boolean isEmpty() {
            return this.colour == TokenColour.UNKNOWN;
        }

        public TokenColour getColour() {
            return colour;
        }

        public void setColour(TokenColour colour) {
            this.colour = colour;
        }
    }

    enum TokenColour {
        UNKNOWN,
        RED,
        YELLOW
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
}
