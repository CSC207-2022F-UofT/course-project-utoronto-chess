package useCases.board;

import entities.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTests {

    Board board = new Board();


    // Craete tests to test all methods in Board.java

    // Test that the board is created with the correct pieces in the correct positions
    @Test
    public void testCreateNewBoard() {
        Piece[][] board = this.board.getBoard();
        Assertions.assertEquals(board[0][0].getClass(), Rook.class);
        Assertions.assertEquals(board[0][1].getClass(), Knight.class);
        Assertions.assertEquals(board[0][2].getClass(), Bishop.class);
        Assertions.assertEquals(board[0][3].getClass(), Queen.class);
        Assertions.assertEquals(board[0][4].getClass(), King.class);
        Assertions.assertEquals(board[0][5].getClass(), Bishop.class);
        Assertions.assertEquals(board[0][6].getClass(), Knight.class);
        Assertions.assertEquals(board[0][7].getClass(), Rook.class);
        for (int i = 0; i < 8; i++) {
            Assertions.assertEquals(board[1][i].getClass(), Pawn.class);
        }
        Assertions.assertEquals(board[7][0].getClass(), Rook.class);
        Assertions.assertEquals(board[7][1].getClass(), Knight.class);
        Assertions.assertEquals(board[7][2].getClass(), Bishop.class);
        Assertions.assertEquals(board[7][3].getClass(), Queen.class);
        Assertions.assertEquals(board[7][4].getClass(), King.class);
        Assertions.assertEquals(board[7][5].getClass(), Bishop.class);
        Assertions.assertEquals(board[7][6].getClass(), Knight.class);
        Assertions.assertEquals(board[7][7].getClass(), Rook.class);
        for (int i = 0; i < 8; i++) {
            Assertions.assertEquals(board[6][i].getClass(), Pawn.class);
        }
    }

    // Test that a piece can be moved from one position to another
    @Test
    public void testMovePiece() {
        int[] start = {1, 0};
        int[] end = {2, 0};
        Assertions.assertTrue(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is not on the board
    @Test
    public void testMovePieceOutOfBounds() {
        int[] start = {1, 0};
        int[] end = {8, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is occupied by a piece of the same color
    @Test
    public void testMovePieceSameColor() {
        int[] start = {1, 0};
        int[] end = {2, 0};
        Assertions.assertTrue(this.board.movePiece(start, end));
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is not a valid move for that piece
    @Test
    public void testMovePieceInvalidMove() {
        int[] start = {1, 0};
        int[] end = {4, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

}
