package useCases.game;

import entities.pieces.Piece;
import useCases.board.Board;

public class Game {

    private static boolean whiteTurn;

    private static final Board board = new Board();

    public boolean over() {
        return whiteCheckmate() || blackCheckmate();
    }

    public boolean whiteCheckmate() {
        if (!isWhiteTurn()){
            whiteTurn = !whiteTurn;
            for (Board board : board.getAllValidMovesWhite()){
                if (!board.check(true)){
                    whiteTurn = !whiteTurn;
                    return false;
                }
            }
            whiteTurn = !whiteTurn;
            return true;
        }
        else {
            for (Board board : board.getAllValidMovesWhite()){
                if (!board.check(true)){
                    return false;
                }
            }
        }
        return true;
    }



    public boolean blackCheckmate() {
        if (isWhiteTurn()){
            whiteTurn = ! whiteTurn;
            for (Board board : board.getAllValidMovesBlack()){
                if (!board.check(false)){
                    whiteTurn = !whiteTurn;
                    return false;
                }
            }
            whiteTurn = !whiteTurn;
            return true;
        }
        else {
            for (Board board : board.getAllValidMovesBlack()){
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
            if (!over()) {
                Board board1 = board.copy();
                boolean success = board1.movePiece(start, end);
                if (board1.check(whiteTurn)) {
                    System.out.println("Checked");
                    success = false;
                }
                if (success) {
                    board.movePiece(start, end);
                    whiteTurn = !whiteTurn;
                }
            }
    }

    public static Piece[][] getBoard() {
        return board.getChessBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}