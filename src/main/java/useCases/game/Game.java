package useCases.game;

import entities.pieces.King;
import entities.pieces.Piece;
import useCases.board.Board;

import javax.swing.*;

public class Game {

    private static boolean whiteTurn;

    private final Board board = new Board();


    public boolean isOver() {
        return (black_win() | white_win());
    }

    private boolean black_win(){
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (this.board.getBoard()[i][j] instanceof King) {
                    if (this.board.getBoard()[i][j].isWhite()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean white_win(){
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (this.board.getBoard()[i][j] instanceof King) {
                    if (!this.board.getBoard()[i][j].isWhite()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Game() {
        whiteTurn = true;
    }


    /*
        * Moves a piece from one position to another
     */
    public void movePiece(int[] start, int[] end) {
        if(!isOver()) {
            boolean success = board.movePiece(start, end);
            if (success) {
                whiteTurn = !whiteTurn;
            }
        }
        if (white_win()){
            JOptionPane.showMessageDialog(null, "Game Over. White Wins");
        }
        else if (black_win()){
            JOptionPane.showMessageDialog(null, "Game Over. Black Wins");
        }
    }

    public Piece[][] getBoard() {
        return board.getBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}
