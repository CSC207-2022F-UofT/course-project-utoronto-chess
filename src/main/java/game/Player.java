package game;

public class Player {

    private String pieceColour;

    public Player(String pieceColour) {
        this.pieceColour = pieceColour;
    }
    public boolean inCheck() {
        return false;
    }

    public boolean hasCastled() {
        return false;
    }
}
