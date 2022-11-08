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
            return "P";
        } else {
            return "p";
        }
    }
}
