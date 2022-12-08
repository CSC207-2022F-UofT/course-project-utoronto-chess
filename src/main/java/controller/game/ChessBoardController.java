package controller.game;

import entities.pieces.Piece;
import useCases.game.Game;

// Controller classes that handles user input from ChessPanel and makes the appropriate calls to the board class
public class ChessBoardController {

    private static boolean successfulMove;

    public Piece[][] getBoard(Game game) {
        return game.getBoard();
    }

    public void movePiece(Game game, int[] start, int[] end) {
        successfulMove = game.movePiece(start, end);
    }

    public static boolean isSuccessfulMove() {
        boolean temp = successfulMove;
        successfulMove = false;
        return temp;
    }

}
