package com.axa.sea;

import java.util.ArrayList;
import java.util.List;

public class GridAnalyzer {

    private GridAnalyzer() {
    }

    private static int NUMBER_OF_SAME_TOKENS_TO_WIN = 4;

    public static boolean checkColumns(Grid grid) {
        for (Cell[] cols : grid.getGrid()) {
            if (checkLine4Win(cols)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRows(Grid grid) {
        return checkColumns(swapCellsFromCols2Rows(grid));

    }

    public static boolean checkDiagonalLeft2Right(Grid grid) {

        int maxColsToCheck = grid.getMaxCols() - NUMBER_OF_SAME_TOKENS_TO_WIN + 1;
        int maxRowsToCheck = grid.getMaxRows() - NUMBER_OF_SAME_TOKENS_TO_WIN + 1;

        for (int column = 0; column < maxColsToCheck; column++) {
            for (int row = 0; row < maxRowsToCheck; row++) {
                Cell[] line = extractDiagonalLineLeft2Rigth(grid, column, row);
                if (checkLine4Win(line)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonalRight2Left(Grid grid){
        return checkDiagonalLeft2Right(reverseColumnOrder(grid));
    }

    private static Grid reverseColumnOrder(Grid oldGrid) {
        int cntCols = oldGrid.getMaxCols();
        int cntRows = oldGrid.getMaxRows();
        Cell[][] revesedColumns = new Cell[cntCols][cntRows];
        for (int col = 0; col < cntCols; col++) {
            revesedColumns[cntCols - col - 1] = oldGrid.getGrid()[col];
        }
        return new Grid(revesedColumns);
    }

    private static Cell[] extractDiagonalLineLeft2Rigth(Grid grid, int startColumn, int startRow) {
        List<Cell> cells = new ArrayList<>();
        for (int col = startColumn, row = startRow; col < grid.getMaxCols() && row < grid.getMaxRows(); col++, row++) {
            cells.add(grid.getGrid()[col][row]);
        }
        return cells.toArray(new Cell[cells.size()]);
    }

    private static boolean checkLine4Win(Cell[] line) {
        int count = 1;
        Cell lastCell = new Cell();
        if (line[0].getColour() != Cell.TokenColour.UNKNOWN) {
            for (Cell rowCell : line) {
                if (rowCell.getColour() != Cell.TokenColour.UNKNOWN) {
                    if (rowCell.getColour() == lastCell.getColour()) {
                        count++;
                        if (count == NUMBER_OF_SAME_TOKENS_TO_WIN) {
                            return true;
                        }
                    } else {
                        lastCell = rowCell;
                        count = 1;
                    }
                }
            }
        }
        return false;
    }

    private static Grid swapCellsFromCols2Rows(Grid oldGrid) {
        int cntCols = oldGrid.getMaxCols();
        int cntRows = oldGrid.getMaxRows();
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
