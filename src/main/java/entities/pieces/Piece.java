package entities.pieces;

public abstract class Piece {


    protected static final String WHITE_COLOR = "\u001B[32m";
    protected static final String BLACK_COLOR = "\u001B[31m";
    protected static final String RESET_COLOR = "\u001B[0m";

    boolean white;


    /*
        * Constructor
     */
    public Piece(boolean white) {
        this.white = white;
    }

    /*
        * Getter for piece color
        * @return true if piece is white, false if piece is black
     */
    public boolean isWhite() {
        return white;
    }

    /*
        * Abstract method for checking if a move is valid
        * @param board the board the piece is on
        * @param start the starting position of the piece
        * @param end the ending position of the piece
        * @return true if the move is valid, false if the move is invalid
     */
    public abstract boolean canMove(Piece[][] board, int[] start, int[] end);

    /*
        * Abstract method for getting the string representation of the piece
        * @return the string representation of the piece
     */
    public abstract String stringPath();
}
