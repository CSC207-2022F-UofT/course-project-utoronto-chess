package useCases.board;

import entities.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTests {

    Board board = new Board();

    // Test that the board is created with the correct pieces in the correct positions
    @Test
    @DisplayName("New Board")
    void testCreateNewBoard() {
        Piece[][] board = this.board.getChessBoard();
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

    // Test that a piece cannot be moved to the same position
    @Test
    @DisplayName("Same Position")
    void testMovePieceSamePosition() {
        int[] start = {6, 0};
        int[] end = {6, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece is not at the starting position
    @Test
    @DisplayName("No Piece at Start")
    void testMovePieceNoPiece() {
        int[] start = {4, 0};
        int[] end = {5, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is not on the board
    @Test
    @DisplayName("Out of Bounds")
    void testMovePieceOutOfBounds() {
        int[] start = {6, 0};
        int[] end = {8, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is occupied by a piece of the same color
    @Test
    @DisplayName("Same Color")
    void testMovePieceSameColor() {
        int[] start = {6, 0};
        int[] end = {5, 0};
        this.board.movePiece(start, end);
        start = new int[]{1, 0};
        end = new int[]{2, 0};
        this.board.movePiece(start, end);
        start = new int[]{7, 0};
        end = new int[]{5, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece cannot be moved to a position that is not a valid move for that piece
    @Test
    @DisplayName("Invalid Move")
    void testMovePieceInvalidMove() {
        int[] start = {5, 0};
        int[] end = {2, 0};
        Assertions.assertFalse(this.board.movePiece(start, end));
    }

    // Test that a piece can be moved from one position to another
    @Test
    @DisplayName("Valid Move")
    void testMovePiece() {
        int[] start = {1, 0};
        int[] end = {2, 0};
        Assertions.assertTrue(this.board.movePiece(start, end));
    }

}
