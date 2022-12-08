import useCases.game.Game;
import views.ChessPanel;
import views.LoginWindow;

public class UTorontoChess {

    public static void main(String[] arguments) {
        Game game = new Game();
        //Anim menu = new Anim();
        ChessPanel chessPanel = new ChessPanel(game);

    }
}
