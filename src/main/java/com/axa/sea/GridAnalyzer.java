package com.axa.sea;

import static com.axa.sea.Cell.TokenColour.UNKNOWN;

import java.util.ArrayList;
import java.util.List;

public class GridAnalyzer {

    private GridAnalyzer() {
    }

    private static int NUMBER_OF_SAME_TOKENS_TO_WIN = 4;

    public static Cell.TokenColour checkGrid(Grid grid) {
        Cell.TokenColour colour;
        if ((colour = checkColumns(grid)) != UNKNOWN) return colour;
        if ((colour = checkRows(grid)) != UNKNOWN) return colour;
        if ((colour = checkDiagonalLeft2Right(grid)) != UNKNOWN) return colour;
        if ((colour = checkDiagonalRight2Left(grid)) != UNKNOWN) return colour;
        return UNKNOWN;
    }

    static Cell.TokenColour checkColumns(Grid grid) {
        for (Cell[] cols : grid.getGrid()) {
            Cell.TokenColour colour = checkLine4Win(cols);
            if (colour != UNKNOWN) {
                return colour;
            }
        }
        return UNKNOWN;
    }

    static Cell.TokenColour checkRows(Grid grid) {
        return checkColumns(swapCellsFromCols2Rows(grid));

    }

    static Cell.TokenColour checkDiagonalLeft2Right(Grid grid) {

        int maxColsToCheck = grid.getMaxCols() - NUMBER_OF_SAME_TOKENS_TO_WIN + 1;
        int maxRowsToCheck = grid.getMaxRows() - NUMBER_OF_SAME_TOKENS_TO_WIN + 1;

        for (int column = 0; column < maxColsToCheck; column++) {
            for (int row = 0; row < maxRowsToCheck; row++) {
                Cell[] line = extractDiagonalLineLeft2Rigth(grid, column, row);
                Cell.TokenColour colour = checkLine4Win(line);
                if (colour != UNKNOWN) {
                    return colour;
                }
            }
        }
        return UNKNOWN;
    }

    static Cell.TokenColour checkDiagonalRight2Left(Grid grid) {
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

    private static Cell.TokenColour checkLine4Win(Cell[] line) {
        int count = 1;
        Cell lastCell = new Cell();
        if (line[0].getColour() != UNKNOWN) {
            for (Cell rowCell : line) {
                if (rowCell.getColour() != UNKNOWN) {
                    if (rowCell.getColour() == lastCell.getColour()) {
                        count++;
                        if (count == NUMBER_OF_SAME_TOKENS_TO_WIN) {
                            return rowCell.getColour();
                        }
                    } else {
                        lastCell = rowCell;
                        count = 1;
                    }
                }
            }
        }
        return UNKNOWN;
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
