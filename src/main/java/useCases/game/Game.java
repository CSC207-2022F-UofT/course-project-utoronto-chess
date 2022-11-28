package useCases.game;

import entities.pieces.King;
import useCases.board.Board;
import entities.pieces.Piece;

public class Game {

    private static boolean whiteTurn;

    private boolean isOver;

    private final Board board = new Board();


    public boolean isOver() {
        boolean white_alive = false;
        boolean black_alive = false;
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                if (this.board.getBoard()[i][j] instanceof King){
                    if (this.board.getBoard()[i][j].isWhite()){
                        white_alive = true;
                    }
                    if (!this.board.getBoard()[i][j].isWhite()){
                        black_alive = true;
                    }
                }
            }
        }
        return (white_alive && black_alive);
    }

    public Game() {
        whiteTurn = true;
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

    public Piece[][] getBoard() {
        return board.getBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}
