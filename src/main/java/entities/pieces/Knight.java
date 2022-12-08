package entities.pieces;

public class Knight extends Piece {

    /**
     * Creates a new Knight object
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public Knight(boolean white) {
        super(white);
    }

    /**
     * Checks if the knight can move to the end position from the start position
     *
     * @param board the chess board
     * @param start the starting position
     * @param end   the ending position
     * @return true if the knight can move to the end position from the start position, false otherwise
     */
    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }
        return Math.abs(x) == 1 && Math.abs(y) == 2;
    }

    /**
     * Returns a string representation of the piece
     *
     * @return a string representation of the piece
     */
    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "N" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "n" + RESET_COLOR;
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
            return "wKnight";
        } else {
            return "bKnight";
        }
    }
}
