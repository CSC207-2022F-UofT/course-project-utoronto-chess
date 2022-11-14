package pieces;

public class Pawn extends Piece{


    public Pawn(boolean white, int column, int row) {
        super(white,column, row);
    }

    public void promotion() {

    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        return false;
    }

    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "P" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "p" + RESET_COLOR;
        }
    }
}
