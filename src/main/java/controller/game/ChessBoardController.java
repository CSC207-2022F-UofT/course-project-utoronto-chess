package controller.game;


import entities.pieces.Piece;
import org.jetbrains.annotations.NotNull;
import useCases.game.Game;

// Controller classes that handles user input from ChessPanel and makes the appropriate calls to the board class
public class ChessBoardController {

    public Piece[][] getBoard(@NotNull Game game) {
        return game.getBoard();
    }

    public void movePiece(@NotNull Game game, int[] start, int[] end) {
        game.movePiece(start, end);
    }

    public boolean checkmate(Game game) {
        return game.over();
    }

}
