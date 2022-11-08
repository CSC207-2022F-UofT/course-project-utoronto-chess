package pieces;

public class Rook extends Piece {

    public Rook(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }

    @Override
    public String toString() {
        if (white) {
            return "R";
        } else {
            return "r";
        }
    }
}
