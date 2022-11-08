package pieces;

public class Bishop extends Piece {

    public Bishop(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public String toString() {
        return "B";
    }
}
