package board;

import pieces.*;

import java.util.Arrays;

public class Board {

    Piece[][] board;

    public Board() {
        this.createNewBoard();
    }


    public void movePiece() {

    }


    public void getAllValidMoves() {

    }

    /*
    *   Creates a new board with all the chess pieces in their starting positions
     */
    public void createNewBoard() {
        this.board = new Piece[8][8];
        // White pieces
        this.board[0][0] = new Rook(true, 0, 0);
        this.board[0][1] = new Knight(true, 0, 1);
        this.board[0][2] = new Bishop(true, 0, 2);
        this.board[0][3] = new Queen(true, 0, 3);
        this.board[0][4] = new King(true, 0, 4);
        this.board[0][5] = new Bishop(true, 0, 5);
        this.board[0][6] = new Knight(true, 0, 6);
        this.board[0][7] = new Rook(true, 0, 7);

        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(true, 1, i);
        }

        // Black pieces
        this.board[7][0] = new Rook(false, 7, 0);
        this.board[7][1] = new Knight(false, 7, 1);
        this.board[7][2] = new Bishop(false, 7, 2);
        this.board[7][3] = new Queen(false, 7, 3);
        this.board[7][4] = new King(false, 7, 4);
        this.board[7][5] = new Bishop(false, 7, 5);
        this.board[7][6] = new Knight(false, 7, 6);
        this.board[7][7] = new Rook(false, 7, 7);

        for (int i = 0; i < 8; i++) {
            this.board[6][i] = new Pawn(false, 6, i);
        }

    }


    public String toString() {
        StringBuilder brdStr = new StringBuilder();
        brdStr.append("  a b c d e f g h\n");
        for (int r = 0; r < 8; r++) {
            brdStr.append(8 - r).append(" ");
            for (int c = 0; c < 8; c++) {
                if (this.board[r][c] == null) {
                    brdStr.append(" ");
                } else {
                    brdStr.append(this.board[r][c].toString());
                }
                brdStr.append(" ");
            }
            brdStr.append(8 - r).append("\n");
        }
        brdStr.append("  a b c d e f g h\n");
        return brdStr.toString();
    }

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
    }

}
