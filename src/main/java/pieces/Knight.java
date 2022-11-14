package pieces;

public class Knight extends Piece {

    public Knight(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }


    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "N" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "n" + RESET_COLOR;
        }
    }
}
