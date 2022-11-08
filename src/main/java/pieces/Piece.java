package pieces;

public abstract class Piece {

    boolean white;
    int row;
    int column;
    boolean taken;



    public Piece(boolean white, int column, int row) {
        this.white = white;
        this.row = row;
        this.column = column;
        this.taken = false;
    }

    public boolean isTaken() {
        return taken;
    }

    public boolean isWhite() {
        return white;
    }

    public int[] getPosition() {
        return new int[] {row, column};
    }

}
