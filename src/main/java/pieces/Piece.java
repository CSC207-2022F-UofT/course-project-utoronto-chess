package pieces;

import java.util.ArrayList;

public abstract class Piece {

    protected static final String WHITE_COLOR = "\u001B[32m";
    protected static final String BLACK_COLOR = "\u001B[31m";
    protected static final String RESET_COLOR = "\u001B[0m";

//    // can make white final
    private final boolean isWhite;
    private int row;
    private int column;


    public Piece(boolean isWhite, int column, int row) {
        this.isWhite = isWhite;
        this.row = row;
        this.column = column;
    }

    //    Getters
    public boolean isWhite() {
        return isWhite;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }

    //    Setters
    public void setPosition(int[] position) {
        this.row = position[0];
        this.column = position[1];
    }

    public int[] getPosition() {
        return new int[]{row, column};
    }


    public abstract boolean canMove(Piece[][] board, int[] start, int[] end);
}
