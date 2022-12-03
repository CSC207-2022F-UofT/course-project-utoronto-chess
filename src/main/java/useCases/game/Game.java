package useCases.game;

import entities.pieces.Piece;
import useCases.board.Board;

import javax.swing.*;

public class Game {

    private static boolean whiteTurn;

    private final Board board = new Board();

    public boolean Over() {
        return white_checkmate() | black_checkmate();
    }

    public boolean white_checkmate() {
        if (!isWhiteTurn()){
            whiteTurn = !whiteTurn;
            for (Board board : board.getAllValidMoves_white()){
                if (!board.check(true)){
                    whiteTurn = !whiteTurn;
                    return false;
                }
            }
            whiteTurn = !whiteTurn;
            return true;
        }
        else {
            for (Board board : board.getAllValidMoves_white()){
                if (!board.check(true)){
                    return false;
                }
            }
        }
        return true;
    }



    public boolean black_checkmate() {
        if (isWhiteTurn()){
            whiteTurn = ! whiteTurn;
            for (Board board : board.getAllValidMoves_black()){
                if (!board.check(false)){
                    whiteTurn = !whiteTurn;
                    return false;
                }
            }
            whiteTurn = !whiteTurn;
            return true;
        }
        else {
            for (Board board : board.getAllValidMoves_black()){
                if (!board.check(false)){
                    return false;
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
        if(!Over()) {
            Board board1 = board.copy();
            boolean success = board1.movePiece(start, end);
            if (board1.check(whiteTurn)){
                System.out.println("Checked");
                success = false;
            }
            if (success) {
                board.movePiece(start, end);
                whiteTurn = !whiteTurn;
            }
        }
        if (black_checkmate()){
            JOptionPane.showMessageDialog(null, "Game Over. White Wins");
        }
        else if (white_checkmate()){
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
