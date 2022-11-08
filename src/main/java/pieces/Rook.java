package pieces;

public class Rook extends Piece {

    public Rook(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public String toString() {
        return "R";
    }
}
