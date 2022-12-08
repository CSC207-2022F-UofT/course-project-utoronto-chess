package entities.pieces;

public class Pawn extends Piece {

    /**
     * Creates a new Pawn object
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public Pawn(boolean white) {
        super(white);
    }


    /**
     * Checks if the pawn can move to the end position from the start position
     *
     * @param board the chess board
     * @param start the starting position
     * @param end   the ending position
     * @return true if the pawn can move to the end position from the start position, false otherwise
     */
    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (!this.isWhite()) {
            if (x == 1 && y == 0 && board[end[0]][end[1]] == null) {
                return true;
            }
            if (x == 2 && y == 0 && board[end[0]][end[1]] == null && board[end[0] - 1][end[1]] == null && start[0] == 1) {
                return true;
            }
            return x == 1 && Math.abs(y) == 1 && board[end[0]][end[1]] != null;
        } else {
            if (x == -1 && y == 0 && board[end[0]][end[1]] == null) {
                return true;
            }
            if (x == -2 && y == 0 && board[end[0]][end[1]] == null && board[end[0] + 1][end[1]] == null && start[0] == 6) {
                return true;
            }
            return x == -1 && Math.abs(y) == 1 && board[end[0]][end[1]] != null;
        }
    }

    /**
     * Returns a string representation of the piece
     *
     * @return a string representation of the piece
     */
    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "P" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "p" + RESET_COLOR;
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
            return "wPawn";
        } else {
            return "bPawn";
        }
    }
}
