package entities.pieces;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import useCases.board.Board;
//import useCases.game.Game;

public class PiecesTests {
    //    Creates a board with starting pieces to run once before all tests TODO
    private final Board board = new Board();

//    @Before
//    public void setUp() {
//        board = new Board();
//    }

    //    Tests that a white pawn can be moved one square from its original starting position
    @Test
    public void testMoveWhitePawnOneSquare() {
        Piece[][] board = this.board.getBoard();
        Assertions.assertEquals(board[1][0].getClass(), Pawn.class);
        Assertions.assertNull(board[2][0]);
        this.board.movePiece(new int[]{1, 0}, new int[]{2, 0});
        Assertions.assertEquals(board[2][0].getClass(), Pawn.class);
        Assertions.assertNull(board[1][0]);
    }

    //    Tests that a white pawn can be moved two squares from its original starting position
    @Test
    public void testMoveWhitePawnTwoSquares() {
        Piece[][] board = this.board.getBoard();
        Assertions.assertEquals(board[1][0].getClass(), Pawn.class);
        Assertions.assertNull(board[3][0]);
        this.board.movePiece(new int[]{1, 0}, new int[]{3, 0});
        Assertions.assertEquals(board[3][0].getClass(), Pawn.class);
        Assertions.assertNull(board[1][0]);
    }

    //  Tests that white knights can be moved according to their rules
    //  Checks initial movement from starting position as well as subsequent movement to the edge of the board
    @Test
    public void testMoveWhiteKnight() {
        Piece[][] board = this.board.getBoard();
        Assertions.assertEquals(board[0][1].getClass(), Knight.class);
        Assertions.assertNull(board[2][2]);
        this.board.movePiece(new int[]{0, 1}, new int[]{2, 2});
        Assertions.assertEquals(board[2][2].getClass(), Knight.class);
        Assertions.assertNull(board[0][1]);
    }

}
