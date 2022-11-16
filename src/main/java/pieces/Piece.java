package pieces;

import java.util.ArrayList;

public abstract class Piece {

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
