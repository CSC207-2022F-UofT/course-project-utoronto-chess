package pieces;

public class King extends Piece {

    public King(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public String toString() {
        return "K";
    }
}
