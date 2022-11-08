package pieces;

public class Knight extends Piece {

    public Knight(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public String toString() {
        return "N";
    }
}
