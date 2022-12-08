package useCases.board;

import entities.pieces.*;
import useCases.game.Game;

import java.util.Arrays;

public class Board {

    Piece[][] chessBoard;
    private static final String BOARD_BACKGROUND = "\u001B[40m";
    private static final String RESET_COLOR = "\u001B[0m";

    /*
     * Create a new board object and initialize the chess board with the correct pieces in the
     * correct positions
     */
    public Board() {
        this.createNewBoard();
    }

    /*
     * Getter for the chess board
     */
    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    /*
     *   Creates a new board with all the chess pieces in their starting positions
     */
    public void createNewBoard() {

        this.chessBoard = new Piece[8][8];
        // Black pieces
        this.chessBoard[0][0] = new Rook(false);
        this.chessBoard[0][1] = new Knight(false);
        this.chessBoard[0][2] = new Bishop(false);
        this.chessBoard[0][3] = new Queen(false);
        this.chessBoard[0][4] = new King(false);
        this.chessBoard[0][5] = new Bishop(false);
        this.chessBoard[0][6] = new Knight(false);
        this.chessBoard[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++) {
            this.chessBoard[1][i] = new Pawn(false);
        }

        // White pieces
        this.chessBoard[7][0] = new Rook(true);
        this.chessBoard[7][1] = new Knight(true);
        this.chessBoard[7][2] = new Bishop(true);
        this.chessBoard[7][3] = new Queen(true);
        this.chessBoard[7][4] = new King(true);
        this.chessBoard[7][5] = new Bishop(true);
        this.chessBoard[7][6] = new Knight(true);
        this.chessBoard[7][7] = new Rook(true);

        for (int i = 0; i < 8; i++) {
            this.chessBoard[6][i] = new Pawn(true);
        }

    }

    /*
        * Moves a piece from one position to another
        * Called from the Game class
        * Returns true if the move was successful
        * Returns false if the move was unsuccessful
        @param start: the starting position of the piece
        @param end: the ending position of the piece
     */
    public boolean movePiece(int[] start, int[] end) {
        Piece piece = chessBoard[start[0]][start[1]];


        // Handle all cases where a piece cannot be moved

        // Same location
        if (Arrays.equals(start, end)) {
            System.out.println("Same position");
            return false;
        }

        // No piece at this position
        else if (piece == null) {
            System.out.println("No piece at start position");
            return false;
        }

        // Not a playable piece
        else if (piece.isWhite() != Game.isWhiteTurn()) {
            System.out.println("Not your turn");
            return false;
        }

        // Not legal move
        else if (!piece.canMove(chessBoard, start, end)) {
            System.out.println("Illegal move");
            return false;
        }

        // Same color piece
        else if (chessBoard[end[0]][end[1]] != null && chessBoard[end[0]][end[1]].isWhite() == piece.isWhite()) {
            System.out.println("Same color piece");
            return false;
        }

        // Handle special cases where a piece can be moved

        // Castling
        if (piece instanceof King && Math.abs(end[1] - start[1]) == 2) {
            this.castling(end);
        }

        // Auto Queen promotion for pawn
        if (piece instanceof Pawn && (end[0] == 0 || end[0] == 7)) {
           piece = new Queen(piece.isWhite());
        }

        chessBoard[end[0]][end[1]] = piece;
        chessBoard[start[0]][start[1]] = null;

        return true;
    }

    private void castling(int[] end) {
        int rookX = end[1] == 2 ? 0 : 7;
        int rookY = end[0];
        int rookNewX = end[1] == 2 ? 3 : 5;
        int rookNewY = end[0];
        chessBoard[rookNewY][rookNewX] = chessBoard[rookY][rookX];
        chessBoard[rookY][rookX] = null;
    }


    /*
     *   Checks if the king of the given color is in check. If it is in check it will check for checkmate
     */
    public boolean check() {
        // TODO
        return false;
    }

    /*
     *   Checks if the king of the given color is in checkmate
     */
    public boolean checkmate() {
        // TODO
        return false;
    }



    /*
     *   Prints the current board to the console
     */
    public String toString() {
        StringBuilder brdStr = new StringBuilder();
        brdStr.append("\u001B[40m    a   b   c   d   e   f   g   h    \u001B[0m\n");
        for (int r = 0; r < 8; r++) {
            brdStr.append(BOARD_BACKGROUND + "  +---+---+---+---+---+---+---+---+  " + RESET_COLOR + "\n");
            brdStr.append(BOARD_BACKGROUND).append(8 - r).append(" | ");
            for (int c = 0; c < 8; c++) {
                if (this.chessBoard[r][c] == null) {
                    brdStr.append(".");
                } else {
                    brdStr.append(this.chessBoard[r][c].toString()).append(BOARD_BACKGROUND);
                }
                brdStr.append(" | ");
            }
            brdStr.append(8 - r).append(RESET_COLOR).append("\n");
        }
        brdStr.append(BOARD_BACKGROUND + "  +---+---+---+---+---+---+---+---+  " + RESET_COLOR + "\n");
        brdStr.append(BOARD_BACKGROUND + "    a   b   c   d   e   f   g   h    " + RESET_COLOR + "\n");

        return brdStr.toString();
    }
}
