package useCases.game;

import entities.board.Board;
import entities.pieces.Piece;

public class Game {

    private static boolean whiteTurn;

    private boolean isOver;

    private final Board board = new Board();


    public boolean isOver() {
        return isOver;
    }

    public Game() {
        whiteTurn = true;
        isOver = false;

        // TODO Implement the Game loop
//        while (!isOver) {
//            boolean move = ChessBoardController.isSuccessfulMove();
//            if (whiteTurn) {
//                // white's turn
//                if (move) {
//                    whiteTurn = false;
//                }
//            } else {
//                // black's turn
//                if (move) {
//                    whiteTurn = true;
//                }
//            }
//
//            // check if game is over (checkmate or stalemate)
//            if (board.isCheckmate()) {
//                if (!whiteTurn) {
//                    System.out.println("Black wins");
//                } else {
//                    System.out.println("White wins");
//                }
//                isOver = true;
//            }
//
//        }
    }

    public boolean movePiece(int[] start, int[] end) {
        return board.movePiece(start, end);
    }

    public Piece[][] getBoard() {
        System.out.println(board);
        return board.getBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}
