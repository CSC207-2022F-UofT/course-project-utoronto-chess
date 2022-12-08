package entities.pieces;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import useCases.board.Board;

class PiecesTests {
    //    Creates a board with starting pieces to run once before all tests
    private final Board board = new Board();

    //    Tests that both the black and white pawns can be moved one square away from their starting position
    @Test
    @DisplayName("Pawn movement test")
    void testMovePawnOneSquare() {
        Piece[][] board = this.board.getChessBoard();
        Assertions.assertTrue(board[1][0].canMove(board, new int[]{1, 0}, new int[]{2, 0}));
        this.board.movePiece(new int[]{1, 0}, new int[]{2, 0});
        Assertions.assertTrue(board[6][0].canMove(board, new int[]{6, 0}, new int[]{5, 0}));
        this.board.movePiece(new int[]{6, 0}, new int[]{5, 0});
    }

    //    Tests that both the black and white pawns can be moved two squares from their starting positions
    @Test
    void testMovePawnTwoSquares() {
        Piece[][] board = this.board.getChessBoard();
        Assertions.assertTrue(board[1][0].canMove(board, new int[]{1, 0}, new int[]{3, 0}));
        this.board.movePiece(new int[]{1, 0}, new int[]{3, 0});
        Assertions.assertTrue(board[6][0].canMove(board, new int[]{6, 0}, new int[]{4, 0}));
        this.board.movePiece(new int[]{6, 0}, new int[]{4, 0});
    }

    //  Tests that knights can be moved according to their rules
    //  Checks initial movement from starting position as well as subsequent movement to the edge of the board
    @Test
    void testMoveKnight() {
        Piece[][] board = this.board.getChessBoard();
        Assertions.assertTrue(board[0][1].canMove(board, new int[]{0,1}, new int[]{2,2}));
        this.board.movePiece(new int[]{0,1}, new int[]{2,2});
        Assertions.assertTrue(board[2][2].canMove(board, new int[]{2,2}, new int[]{4,1}));
        this.board.movePiece(new int[]{2,2}, new int[]{4,1});
        Assertions.assertTrue(board[4][1].canMove(board, new int[]{4,1}, new int[]{3,3}));
        this.board.movePiece(new int[]{4,1}, new int[]{3,3});
        Assertions.assertTrue(board[3][3].canMove(board, new int[]{3,3}, new int[]{4,5}));
        this.board.movePiece(new int[]{3,3}, new int[]{4,5});
        Assertions.assertTrue(board[4][5].canMove(board, new int[]{4,5}, new int[]{5,3}));
        this.board.movePiece(new int[]{4,5}, new int[]{5,3});
        Assertions.assertTrue(board[5][3].canMove(board, new int[]{5,3}, new int[]{3,2}));
        this.board.movePiece(new int[]{5,3}, new int[]{3,2});
        Assertions.assertTrue(board[3][2].canMove(board, new int[]{3,2}, new int[]{2,0}));
        this.board.movePiece(new int[]{3,2}, new int[]{2,0});
        Assertions.assertTrue(board[2][0].canMove(board, new int[]{2,0}, new int[]{0,1}));
        this.board.movePiece(new int[]{2,0}, new int[]{0,1});
    }

    //  Tests that bishops can be moved according to their rules
    @Test
    void testMoveBishop() {
        Piece[][] board = this.board.getChessBoard();
//        Moves the pawn out of the way to allow the bishop to move
        this.board.movePiece(new int[]{1,1}, new int[]{2,1});

        Assertions.assertTrue(board[0][2].canMove(board, new int[]{0,2}, new int[]{2,0}));
        this.board.movePiece(new int[]{0,2}, new int[]{2,0});
        Assertions.assertTrue(board[2][0].canMove(board, new int[]{2,0}, new int[]{4,2}));
        this.board.movePiece(new int[]{2,0}, new int[]{4,2});
        Assertions.assertTrue(board[4][2].canMove(board, new int[]{4,2}, new int[]{2,0}));
        this.board.movePiece(new int[]{4,2}, new int[]{2,0});
        Assertions.assertTrue(board[2][0].canMove(board, new int[]{2,0}, new int[]{0,2}));
        this.board.movePiece(new int[]{2,0}, new int[]{0,2});
    }

    //  Tests that rooks can be moved according to their rules
    @Test
    void testMoveRook() {
        Piece[][] board = this.board.getChessBoard();
//        Moves the pawn out of the way to allow the rook to move
        this.board.movePiece(new int[]{1,0}, new int[]{3,0});

        Assertions.assertTrue(board[0][0].canMove(board, new int[]{0,0}, new int[]{2,0}));
        this.board.movePiece(new int[]{0,0}, new int[]{2,0});
        Assertions.assertTrue(board[2][0].canMove(board, new int[]{2,0}, new int[]{2,2}));
        this.board.movePiece(new int[]{2,0}, new int[]{2,2});
        Assertions.assertTrue(board[2][2].canMove(board, new int[]{2,2}, new int[]{2,0}));
        this.board.movePiece(new int[]{2,2}, new int[]{2,0});
        Assertions.assertTrue(board[2][0].canMove(board, new int[]{2,0}, new int[]{0,0}));
        this.board.movePiece(new int[]{2,0}, new int[]{0,0});
    }

    //  Tests that queens can be moved according to their rules
    @Test
    void testMoveQueen() {
        Piece[][] board = this.board.getChessBoard();
//        Moves the pawn out of the way to allow the queen to move
        this.board.movePiece(new int[]{1,3}, new int[]{3,3});

        Assertions.assertTrue(board[0][3].canMove(board, new int[]{0,3}, new int[]{2,3}));
        this.board.movePiece(new int[]{0,3}, new int[]{2,3});
        Assertions.assertTrue(board[2][3].canMove(board, new int[]{2,3}, new int[]{2,5}));
        this.board.movePiece(new int[]{2,3}, new int[]{2,5});
        Assertions.assertTrue(board[2][5].canMove(board, new int[]{2,5}, new int[]{4,7}));
        this.board.movePiece(new int[]{2,5}, new int[]{4,7});
        Assertions.assertTrue(board[4][7].canMove(board, new int[]{4,7}, new int[]{2,5}));
        this.board.movePiece(new int[]{4,7}, new int[]{2,5});
        Assertions.assertTrue(board[2][5].canMove(board, new int[]{2,5}, new int[]{4,3}));
        this.board.movePiece(new int[]{2,5}, new int[]{4,3});
        Assertions.assertTrue(board[4][3].canMove(board, new int[]{4,3}, new int[]{2,5}));
        this.board.movePiece(new int[]{4,3}, new int[]{2,5});
        Assertions.assertTrue(board[2][5].canMove(board, new int[]{2,5}, new int[]{2,3}));
        this.board.movePiece(new int[]{2,5}, new int[]{2,3});
        Assertions.assertTrue(board[2][3].canMove(board, new int[]{2,3}, new int[]{0,3}));
        this.board.movePiece(new int[]{2,3}, new int[]{0,3});
    }

    //  Tests that kings can be moved according to their rules
    @Test
    void testMoveKing() {
        Piece [][] board = this.board.getChessBoard();
//        Moves the pawn out of the way to allow the king to move
        this.board.movePiece(new int[]{1,4}, new int[]{3,4});

        Assertions.assertTrue(board[0][4].canMove(board, new int[]{0,4}, new int[]{1,4}));
        this.board.movePiece(new int[]{0,4}, new int[]{1,4});
        Assertions.assertTrue(board[1][4].canMove(board, new int[]{1,4}, new int[]{2,3}));
        this.board.movePiece(new int[]{1,4}, new int[]{2,3});
        Assertions.assertTrue(board[2][3].canMove(board, new int[]{2,3}, new int[]{2,2}));
        this.board.movePiece(new int[]{2,3}, new int[]{2,2});
        Assertions.assertTrue(board[2][2].canMove(board, new int[]{2,2}, new int[]{3,3}));
        this.board.movePiece(new int[]{2,2}, new int[]{3,3});
        Assertions.assertTrue(board[3][3].canMove(board, new int[]{3,3}, new int[]{2,2}));
        this.board.movePiece(new int[]{3,3}, new int[]{2,2});
        Assertions.assertTrue(board[2][2].canMove(board, new int[]{2,2}, new int[]{2,3}));
        this.board.movePiece(new int[]{2,2}, new int[]{2,3});
        Assertions.assertTrue(board[2][3].canMove(board, new int[]{2,3}, new int[]{1,4}));
        this.board.movePiece(new int[]{2,3}, new int[]{1,4});
        Assertions.assertTrue(board[1][4].canMove(board, new int[]{1,4}, new int[]{0,4}));
        this.board.movePiece(new int[]{1,4}, new int[]{0,4});
    }

}
