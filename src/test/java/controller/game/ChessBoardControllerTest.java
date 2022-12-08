package controller.game;

import org.junit.jupiter.api.Test;
import useCases.board.Board;
import useCases.game.Game;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardControllerTest {

    @Test
    void getBoard() {
        Game game = new Game();
        ChessBoardController controller = new ChessBoardController();
        assertEquals(game.getBoard(), controller.getBoard(game));
    }

    @Test
    void movePiece() {
        Game game = new Game();
        Board board = new Board();
        ChessBoardController controller = new ChessBoardController();
        int[] start = {6, 0};
        int[] end = {5, 0};
        board.movePiece(start, end);
        controller.movePiece(game, start, end);
        assertEquals(board.getChessBoard()[5][0].toString(), Game.getBoard()[5][0].toString());
    }
}