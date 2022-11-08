package pieces;

public class Pawn extends Piece{


    public Pawn(boolean white, int column, int row) {
        super(white,column, row);
    }

    public void promotion() {

    }

    @Override
    public String toString() {
        return "P";
    }
}
