package useCases.game;

import useCases.board.Board;
import entities.pieces.Piece;

public class Game {

    private static boolean whiteTurn = true;

    private boolean isOver;

    private static final Board board = new Board();


    public boolean isOver() {
        return isOver;
    }

    public Game() {
        isOver = false;
    }


    /*
        * Moves a piece from one position to another
     */
    public void movePiece(int[] start, int[] end) {
        boolean success = board.movePiece(start, end);
        if (success) {
            whiteTurn = !whiteTurn;
        }
    }

    public static Piece[][] getBoard() {
        return board.getChessBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}
