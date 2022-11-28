package entities.pieces;

public abstract class Piece {


    protected static final String WHITE_COLOR = "\u001B[32m";
    protected static final String BLACK_COLOR = "\u001B[31m";
    protected static final String RESET_COLOR = "\u001B[0m";

    boolean white;



    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

    public abstract boolean canMove(Piece[][] board, int[] start, int[] end);

    public abstract String stringPath();
}
