package entities.pieces;

public class Bishop extends Piece {

    /**
     * Creates a new Bishop object
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public Bishop(boolean white) {
        super(white);
    }

    /**
     * Checks if the bishop can move to the end position from the start position
     *
     * @param board the chess board
     * @param start the starting position
     * @param end   the ending position
     * @return true if the bishop can move to the end position from the start position, false otherwise
     */
    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) != Math.abs(y)) {
            return false;
        }
        if (x > 0 && y > 0) {
            for (int i = 1; i < x; i++) {
                if (board[start[0] + i][start[1] + i] != null) {
                    return false;
                }
            }
        } else if (x > 0 && y < 0) {
            for (int i = 1; i < x; i++) {
                if (board[start[0] + i][start[1] - i] != null) {
                    return false;
                }
            }
        } else if (x < 0 && y > 0) {
            for (int i = 1; i < Math.abs(x); i++) {
                if (board[start[0] - i][start[1] + i] != null) {
                    return false;
                }
            }
        } else if (x < 0 && y < 0) {
            for (int i = 1; i < Math.abs(x); i++) {
                if (board[start[0] - i][start[1] - i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the piece
     *
     * @return a string representation of the piece
     */
    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "B" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "b" + RESET_COLOR;
        }
    }

    /**
     * Returns the path to the image of the piece
     *
     * @return the path to the image of the piece
     */
    @Override
    public String stringPath() {
        if (white) {
            return "wBishop";
        } else {
            return "bBishop";
        }
    }
}
