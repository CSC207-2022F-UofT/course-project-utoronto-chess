package controller.game;

import org.junit.jupiter.api.Test;
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
        ChessBoardController controller = new ChessBoardController();
        int[] start = new int[2];
        int[] end = new int[2];
        start[0] = 1;
        start[1] = 0;
        end[0] = 2;
        end[1] = 0;
        controller.movePiece(game, start, end);
        assertEquals(true, controller.isSuccessfulMove());
    }

    @Test
    void isSuccessfulMove() {
        ChessBoardController controller = new ChessBoardController();
        controller.isSuccessfulMove();
        assertEquals(false, controller.isSuccessfulMove());
    }
}