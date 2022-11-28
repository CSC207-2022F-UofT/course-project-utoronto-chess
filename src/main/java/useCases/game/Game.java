package useCases.game;

import controller.game.ChessBoardController;
import entities.board.Board;
import entities.pieces.King;
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
        if(isOver){
            System.out.println("Game over");
            return false;
        }
        if (board.movePiece(start, end)) {
            whiteTurn = !whiteTurn;
            return true;
        }
        return false;
    }

    public Piece[][] getBoard() {
        System.out.println(board);
        return board.getBoard();
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }


}
