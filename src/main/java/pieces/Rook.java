package pieces;

public class Rook extends Piece {

    public Rook(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (x != 0 && y != 0) {
            return false;
        }
        if (x > 0) {
            for (int i = 1; i < x; i++) {
                if (board[start[0] + i][start[1]] != null) {
                    return false;
                }
            }
        }
        else if (x < 0) {
            for (int i = 1; i < Math.abs(x); i++) {
                if (board[start[0] - i][start[1]] != null) {
                    return false;
                }
            }
        }
        else if (y > 0) {
            for (int i = 1; i < y; i++) {
                if (board[start[0]][start[1] + i] != null) {
                    return false;
                }
            }
        }
        else if (y < 0) {
            for (int i = 1; i < Math.abs(y); i++) {
                if (board[start[0]][start[1] - i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (white) {
            return WHITE_COLOR + "R" + RESET_COLOR;
        } else {
            return BLACK_COLOR + "r" + RESET_COLOR;
        }
    }
}
