package useCases.game;

import entities.pieces.Piece;
import useCases.board.Board;

public class Game {

    private static boolean whiteTurn;

    private static final Board board = new Board();

    /* Constructor */
    public Game() {
        whiteTurn = true;
    }

    public boolean over() {
        return whiteCheckmate() || blackCheckmate();
    }

    /* Checks to see if the white king is in check and/or checkmate
     * @return true if white is in checkmate
     */
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

    /* Checks to see if the black king is in check and/or checkmate
     * @return true if black is in checkmate
     */
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

    /*
        * Moves a piece from one position to another
        * @param start: the starting position of the piece
        * @param end: the ending position of the piece
     */
    public void movePiece(int[] start, int[] end) {
        if (!over()) {
            boolean success = board.movePiece(start, end);
            if (success) {
                whiteTurn = !whiteTurn;
            }
        }
    }

    /* Gets the game board
     * @return the game board
     */
    public static Piece[][] getBoard() {
        return board.getChessBoard();
    }

    /* Gets the current turn
     * @return true if it is white's turn, false if it is black's turn
     */
    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}