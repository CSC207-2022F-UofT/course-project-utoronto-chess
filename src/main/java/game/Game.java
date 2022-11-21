package game;

import board.Board;
import pieces.King;

public class Game {

    private static boolean whiteTurn;
    public final Player[] players;
    public Board board;



    public boolean isOver() {
        boolean white_alive = false;
        boolean black_alive = false;
        for (int i = 0; i <= 7; i++){
            for (int j = 0; j <= 7; j++){
                if (this.board.getBoard()[i][j] instanceof King){
                    if (this.board.getBoard()[i][j].isWhite()){
                        white_alive = true;
                    }
                    if (!this.board.getBoard()[i][j].isWhite()){
                        black_alive = true;
                    }
                }
            }
        }
        return !(white_alive && black_alive);
    }

    public Game(Player player1, Player player2, Board board) {
        this.players = new Player[]{player1, player2};
        this.board = board;
    }

    public static boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void Turn(){
        whiteTurn = !whiteTurn;
    }


}
