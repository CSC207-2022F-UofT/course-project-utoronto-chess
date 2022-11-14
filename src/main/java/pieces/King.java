package pieces;

public class King extends Piece {

    private boolean hasCastled = false;

    public King(boolean white, int column, int row) {
        super(white, column, row);
    }

    public boolean isCastlingDone(){
        return this.hasCastled;
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }

    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "K" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "k" + RESET_COLOR;
        }
    }
}
