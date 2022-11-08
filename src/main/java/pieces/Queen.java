package pieces;

public class Queen extends Piece {

    public Queen(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }

    @Override
    public String toString() {
        if (white) {
            return "Q";
        } else {
            return "q";
        }
    }
}
