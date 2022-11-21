package entities.pieces;

public abstract class Piece {


    protected static final String WHITE_COLOR = "\u001B[32m";
    protected static final String BLACK_COLOR = "\u001B[31m";
    protected static final String RESET_COLOR = "\u001B[0m";

    boolean white;
    int row;
    int column;



    public Piece(boolean white, int column, int row) {
        this.white = white;
        this.row = row;
        this.column = column;
    }

    public boolean isWhite() {
        return white;
    }

    public void setPosition(int[] position) {
        this.row = position[0];
        this.column = position[1];
    }

    public int[] getPosition() {
        return new int[] {row, column};
    }

    public abstract boolean canMove(Piece[][] board, int[] start, int[] end);

    public abstract String stringPath();
}
