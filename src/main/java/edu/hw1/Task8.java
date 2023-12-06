package edu.hw1;

/**
 * The {@code Task8} class provides a method {@link Task8#knightBoardCapture(int[][])}
 * which checks if any knight on a chess board can capture any other knight.
 */
public final class Task8 {
    private static final int BOARD_SIZE = 8;
    private static final int[] DX = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] DY = {-1, 1, -2, 2, -2, 2, -1, 1};

    private Task8() {
    }

    /**
     * Checks if a knight on the given chess board can capture any other knights.
     *
     * @param board the chess board represented as a 2D array.
     * @return {@code true} if no knight can capture any other knight, {@code false} otherwise.
     * @throws IllegalArgumentException if the board is {@code null}, not 8x8, or contains values other than 0 or 1.
     */
    public static boolean knightBoardCapture(int[][] board) {
        validateBoard(board);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 1 && canKnightCaptureOtherKnights(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Validates the given chess board.
     *
     * @param board the chess board represented as a 2D array.
     * @throws IllegalArgumentException if the board is {@code null}, not 8x8, or contains values other than 0 or 1.
     */
    private static void validateBoard(int[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Input array 'board' cannot be null");
        }

        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException("Input array 'board' must be " + BOARD_SIZE + "x" + BOARD_SIZE);
        }

        for (int[] row : board) {
            if (row.length != BOARD_SIZE) {
                throw new IllegalArgumentException("Each row of the 'board' must have " + BOARD_SIZE + " elements");
            }

            for (int element : row) {
                if (element != 0 && element != 1) {
                    throw new IllegalArgumentException("Input array 'board' can only contain 0 or 1");
                }
            }
        }
    }

    /**
     * Checks if a knight at a specific location on the board can capture any other knights.
     *
     * @param board the chess board represented as a 2D array.
     * @param row   the row index of the knight.
     * @param col   the column index of the knight.
     * @return {@code true} if the knight can capture another knight, {@code false} otherwise.
     */
    private static boolean canKnightCaptureOtherKnights(int[][] board, int row, int col) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            int newRow = row + DX[i];
            int newCol = col + DY[i];

            if (isValidMove(newRow, newCol) && board[newRow][newCol] == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a move is within the bounds of the chessboard.
     *
     * @param row the row index of the move.
     * @param col the column index of the move.
     * @return {@code true} if the move is valid, {@code false} otherwise.
     */
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }
}
