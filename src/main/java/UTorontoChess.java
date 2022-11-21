import useCases.Game;
import views.ChessPanel;

public class UTorontoChess {

    public static void main(String[] arguments) {
        Game game = new Game();
        ChessPanel chessPanel = new ChessPanel(game);
    }
}
