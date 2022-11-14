package pieces;

public class Bishop extends Piece {

    public Bishop(boolean white, int column, int row) {
        super(white,column, row);
    }

    @Override
    public boolean canMove(Piece[][] board, int[] start, int[] end) {
        int x = end[0] - start[0];
        int y = end[1] - start[1];
        if (Math.abs(x) != Math.abs(y)) {
            return false;
        }
        if (x > 0 && y > 0) {
            for (int i = 1; i < x; i++) {
                if (board[start[0] + i][start[1] + i] != null) {
                    return false;
                }
            }
        }
        else if (x > 0 && y < 0) {
            for (int i = 1; i < x; i++) {
                if (board[start[0] + i][start[1] - i] != null) {
                    return false;
                }
            }
        }
        else if (x < 0 && y > 0) {
            for (int i = 1; i < Math.abs(x); i++) {
                if (board[start[0] - i][start[1] + i] != null) {
                    return false;
                }
            }
        }
        else if (x < 0 && y < 0) {
            for (int i = 1; i < Math.abs(x); i++) {
                if (board[start[0] - i][start[1] - i] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public String toString() {
        if (white) {
            return "B";
        } else {
            return "b";
        }
    }
}
