package pieces;

public class Bishop extends Piece {

    public Bishop(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }


    @Override
    public String toString() {
        if (white) {
            return "B";
        } else {
            return "b";
        }
    }
}
