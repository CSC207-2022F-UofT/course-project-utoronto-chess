package useCases.board;

import entities.pieces.*;
import useCases.game.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @return the chess board
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
        * @param start: the starting position of the piece
        * @param end: the ending position of the piece
        * @return true if the move was successful, false otherwise
     */
    public boolean movePiece(int[] start, int[] end) {
        Piece piece = chessBoard[start[0]][start[1]];


        // Handle all cases where a piece cannot be moved

        // Same location
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

        // Not legal move
        else if (!piece.canMove(chessBoard, start, end)) {
            return false;
        }

        // Same color piece
        else if (chessBoard[end[0]][end[1]] != null && chessBoard[end[0]][end[1]].isWhite() == piece.isWhite()) {
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
     *  Gives the coordinate of king of the given colour
     */
    private int[] findKing(boolean isWhite){
        if (isWhite){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (chessBoard[i][j] instanceof King && chessBoard[i][j].isWhite()){
                        return new int[]{i, j};
                    }
                }
            }
        }
        else {
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (chessBoard[i][j] instanceof King && !chessBoard[i][j].isWhite()){
                        return new int[]{i, j};
                    }
                }
            }
        }
        return new int[] {-99, -99};
    }

    /*
     *  Gives a list of Boards after one move of white piece
     *  @return a list of boards
     */
    public List<Board> getAllValidMovesWhite() {
        ArrayList<Board> result = new ArrayList<>();
        if (Game.isWhiteTurn())
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (chessBoard[i][j] != null && chessBoard[i][j].isWhite()){
                        goOverAllCells(result, i, j);
                    }
                }
            }
        return result;
    }

    /*
     *  Gives a list of Boards after one move of black piece
     * @return a list of boards
     */
    public List<Board> getAllValidMovesBlack() {
        ArrayList<Board> result = new ArrayList<>();
        if (!Game.isWhiteTurn())
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    if (chessBoard[i][j] != null && !chessBoard[i][j].isWhite()){
                        goOverAllCells(result, i, j);
                    }
                }
            }
        return result;
    }

    private void goOverAllCells(ArrayList<Board> result, int i, int j) {
        for (int k = 0; k <= 7; k++) {
            for (int l = 0; l <= 7; l++) {
                Board board1 = this.copy();
                if(board1.movePiece(new int[]{i, j}, new int[]{k, l})){
                    result.add(board1);}
            }
        }
    }

    /*
     *  Gives a copy of the board
     *  @return a copy of the board
     */
    public Board copy(){
        Board board1 = new Board();
        for (int i = 0; i <= 7; i++) {
            System.arraycopy(chessBoard[i], 0, board1.chessBoard[i], 0, 8);
        }
        return board1;
    }

    /*
     *   Checks if the king of the given color is in check
     *  @param isWhite: the color of the king
     * @return true if the king is in check
     */
    public boolean check(boolean isWhite) {
        if (isWhite){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    Piece piece = chessBoard[i][j];
                    if(piece!= null && !piece.isWhite() && piece.canMove(chessBoard, new int[]{i, j}, new int[]{findKing(true)[0], findKing(true)[1]})){
                    return true;
                    }
                }
            }
        }
        if (!isWhite){
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    Piece piece = chessBoard[i][j];
                    if(piece!= null && piece.isWhite() && piece.canMove(chessBoard, new int[]{i, j}, new int[]{findKing(false)[0], findKing(false)[1]})){
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
