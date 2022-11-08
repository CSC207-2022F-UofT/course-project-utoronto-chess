package pieces;

public class Queen extends Piece {

    public Queen(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
