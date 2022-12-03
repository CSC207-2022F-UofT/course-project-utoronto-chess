package useCases.board;

import entities.pieces.*;
import useCases.game.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    Piece[][] board;
    private static final String BOARD_BACKGROUND = "\u001B[40m";
    private static final String RESET_COLOR = "\u001B[0m";

    public Board() {
        this.createNewBoard();
    }

    public Piece[][] getBoard() {
        return board;
    }

    /*
     *   Creates a new board with all the chess pieces in their starting positions
     */
    public void createNewBoard() {

        this.board = new Piece[8][8];
        // Black pieces
        this.board[0][0] = new Rook(false);
        this.board[0][1] = new Knight(false);
        this.board[0][2] = new Bishop(false);
        this.board[0][3] = new Queen(false);
        this.board[0][4] = new King(false);
        this.board[0][5] = new Bishop(false);
        this.board[0][6] = new Knight(false);
        this.board[0][7] = new Rook(false);

        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(false);
        }

        // White pieces
        this.board[7][0] = new Rook(true);
        this.board[7][1] = new Knight(true);
        this.board[7][2] = new Bishop(true);
        this.board[7][3] = new Queen(true);
        this.board[7][4] = new King(true);
        this.board[7][5] = new Bishop(true);
        this.board[7][6] = new Knight(true);
        this.board[7][7] = new Rook(true);

        for (int i = 0; i < 8; i++) {
            this.board[6][i] = new Pawn(true);
        }

    }

    /*
        * Moves a piece from one position to another
        * Called from the Game class
     */
    public boolean movePiece(int[] start, int[] end) {
        Piece piece = board[start[0]][start[1]];


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
        else if (!piece.canMove(board, start, end)) {
            System.out.println("Illegal move");
            return false;
        }

        // Same color piece
        else if (board[end[0]][end[1]] != null && board[end[0]][end[1]].isWhite() == piece.isWhite()) {
            System.out.println("Same color piece");
            return false;
        }

        // Handle special cases where a piece can be moved

        // Castling
        return Castling(start, end, piece);
    }

    /*
     *  Move Piece without printing anything, used for find all valid moves.
     */
    private boolean move_Piece(int[] start, int[] end) {
        Piece piece = board[start[0]][start[1]];


        if (Arrays.equals(start, end)) {
            return false;
        }

        // No piece at this position
        else if (piece == null) {
            return false;
        }

        // Not a playable piece
        else if (piece.isWhite() != Game.isWhiteTurn()) {
            return false;
        }

        else if (!piece.canMove(board, start, end)) {
            return false;
        }

        else if (board[end[0]][end[1]] != null && board[end[0]][end[1]].isWhite() == piece.isWhite()) {
            return false;
        }

        return Castling(start, end, piece);
    }

    private boolean Castling(int[] start, int[] end, Piece piece) {
        if (piece instanceof King && Math.abs(end[1] - start[1]) == 2) {
            int rookX = end[1] == 2 ? 0 : 7;
            int rookY = end[0];
            int rookNewX = end[1] == 2 ? 3 : 5;
            int rookNewY = end[0];
            board[rookNewY][rookNewX] = board[rookY][rookX];
            board[rookY][rookX] = null;
        }

        if (piece instanceof Pawn && (end[0] == 0 || end[0] == 7)) {
            piece = new Queen(piece.isWhite());
        }

        board[end[0]][end[1]] = piece;
        board[start[0]][start[1]] = null;

        return true;
    }


    /*
     *  Gives a list of Boards after one move of white piece
     */
    public ArrayList<Board> getAllValidMoves_white() {
        ArrayList<Board> result = new ArrayList<>();
        if (Game.isWhiteTurn())
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (board[i][j] != null && board[i][j].isWhite()){
                        go_over_all_cells(result, i, j);
                    }
                }
            }
        return result;
    }

    /*
     *  Gives a list of Boards after one move of black piece
     */
    public ArrayList<Board> getAllValidMoves_black() {
        ArrayList<Board> result = new ArrayList<>();
        if (!Game.isWhiteTurn())
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (board[i][j] != null && !board[i][j].isWhite()){
                        go_over_all_cells(result, i, j);
                    }
                }
            }
        return result;
    }

    private void go_over_all_cells(ArrayList<Board> result, int i, int j) {
        for (int k = 0; k <= 7; k++) {
            for (int l = 0; l <= 7; l++) {
                Board board1 = this.copy();
                if(board1.move_Piece(new int[]{i, j}, new int[]{k, l})){
                    result.add(board1);}
                }
            }
    }

    public Board copy(){
        Board board1 = new Board();
        for (int i = 0; i <= 7; i++) {
            System.arraycopy(board[i], 0, board1.board[i], 0, 8);
        }
        return board1;
    }

    /*
     *  Gives the coordinate of king of the given colour
     */
    private int[] find_king(boolean is_white){
        if (is_white){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (board[i][j] instanceof King && board[i][j].isWhite()){
                        return new int[]{i, j};
                    }
                }
            }
        }
        else {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (board[i][j] instanceof King && !board[i][j].isWhite()){
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[] {-99, -99};
    }

    /*
     *   Checks if the king of the given color is in check. If it is in check it will check for checkmate
     */
    public boolean check(boolean white) {
        if (white){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    Piece piece = board[i][j];
                    if(piece!= null && !piece.isWhite() && piece.canMove(board, new int[]{i, j}, new int[]{find_king(true)[0], find_king(true)[1]})){
                    return true;
                    }
                }
            }
        }
        if (!white){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    Piece piece = board[i][j];
                    if(piece!= null && piece.isWhite() && piece.canMove(board, new int[]{i, j}, new int[]{find_king(false)[0], find_king(false)[1]})){
                        return true;
                    }
                }
            }
        }
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
                if (this.board[r][c] == null) {
                    brdStr.append(".");
                } else {
                    brdStr.append(this.board[r][c].toString()).append(BOARD_BACKGROUND);
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
