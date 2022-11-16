package pieces;

public class Knight extends Piece {

    public Knight(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }
        return Math.abs(x) == 1 && Math.abs(y) == 2;
    }


    @Override
    public String toString() {
        if (isWhite()) {
            return "N";
        } else {
            return "n";
        }
    }
}
