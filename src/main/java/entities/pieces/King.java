package entities.pieces;

public class King extends Piece {

    private boolean hasCastled = false;

    /**
     * Creates a new King object
     *
     * @param white true if the piece is white, false if the piece is black
     */
    public King(boolean white) {
        super(white);
    }

    /**
     * Checks if the king has castled
     *
     * @return true if the king has castled, false otherwise
     */
    public boolean isCastlingDone() {
        return this.hasCastled;
    }

    /**
     * Checks if the king can move to the end position from the start position
     *
     * @param board the chess board
     * @param start the starting position
     * @param end   the ending position
     * @return true if the king can move to the end position from the start position, false otherwise
     */
    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) > 1 || Math.abs(y) > 1) {
            return false;
        }
        return board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != this.isWhite();
    }

    /**
     * Returns a string representation of the piece
     *
     * @return a string representation of the piece
     */
    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "K" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "k" + RESET_COLOR;
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
            return "wKing";
        } else {
            return "bKing";
        }
    }
}
