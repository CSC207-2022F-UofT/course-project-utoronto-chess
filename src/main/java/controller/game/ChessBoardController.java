package controller.game;

import entities.pieces.Piece;
import org.jetbrains.annotations.NotNull;
import usecases.game.Game;

// Controller classes that handles user input from ChessPanel and makes the appropriate calls to the board class
public class ChessBoardController {

    private static boolean successfulMove;

    public Piece[][] getBoard(@NotNull Game game) {
        return game.getBoard();
    }

    public void movePiece(@NotNull Game game, int[] start, int[] end) {
        game.movePiece(start, end);
    }

}
