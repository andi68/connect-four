package com.axa.sea;

public class GridAnalyzer {

    private GridAnalyzer() {
    }

    private static int NUMBER_OF_SAME_TOKENS_TO_WIN = 4;

    public static boolean checkColumns(Grid grid) {
        int count = 1;
        Cell lastCell = new Cell();
        for (Cell[] cells : grid.getGrid()) {

            if (cells[0].getColour() == Cell.TokenColour.UNKNOWN) {
                continue;
            }
            for (Cell cell : cells) {
                if (cell.getColour() == Cell.TokenColour.UNKNOWN) {
                    continue;
                }
                if (cell.getColour() == lastCell.getColour()) {
                    count++;
                    if (count == NUMBER_OF_SAME_TOKENS_TO_WIN) {
                        return true;
                    }
                } else {
                    lastCell = cell;
                    count = 1;
                }
            }
            count = 0;
        }

        return false;
    }

    public static boolean checkRows(Grid grid) {
        return checkColumns(swapCellsFromCols2Rows(grid));

    }

    private static Grid swapCellsFromCols2Rows(Grid oldGrid) {
        int cntCols = oldGrid.getGrid().length;
        int cntRows = (oldGrid.getGrid()[0]).length;
        Cell[][] swappedCells = new Cell[cntRows][cntCols];
        // iterate through old grid to fill new grid
        for (int col = 0; col < cntCols; col++) {
            for (int row = 0; row < cntRows; row++) {
                Cell cell = oldGrid.getGrid()[col][row];
                swappedCells[row][col] = cell;
            }
        }
        return new Grid(swappedCells);
    }

}
