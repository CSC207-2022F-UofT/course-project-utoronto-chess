import board.Board;

import java.io.PrintWriter;

public class UTorontoChess {

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board);
        board.movePiece(new int[]{1, 1}, new int[]{3, 1});
        board.movePiece(new int[]{1, 2}, new int[]{3, 2});
        board.movePiece(new int[]{0, 2}, new int[]{2, 0});
        System.out.println(board);
    }
}
