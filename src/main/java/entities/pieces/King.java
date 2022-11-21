package entities.pieces;

public class King extends Piece {

    private boolean hasCastled = false;

    public King(boolean white, int column, int row) {
        super(white, column, row);
    }

    public boolean isCastlingDone(){
        return this.hasCastled;
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) > 1  || Math.abs(y) > 1) {
            return false;
        }
        return board[end[0]][end[1]] == null || board[end[0]][end[1]].isWhite() != this.isWhite();
    }

    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "K" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "k" + RESET_COLOR;
        }
    }

    @Override
    public String stringPath() {
        if (white) {
            return "wKing";
        } else {
            return "bKing";
        }
    }
}