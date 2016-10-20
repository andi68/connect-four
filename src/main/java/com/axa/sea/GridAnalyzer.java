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

}
