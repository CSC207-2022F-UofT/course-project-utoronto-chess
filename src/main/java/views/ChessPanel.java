package views;

import controller.game.ChessBoardController;
import entities.pieces.*;
import usecases.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessPanel extends JFrame{

    public ChessPanel(Game game) {
        super("UTorontoChess");//shows title in the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit

        ChessPanelContent panel = new ChessPanelContent(game);//creates a new panel in the frame
        add(panel);//adds panel to the frame
        pack();
        setVisible(true);
    }
}

class ChessPanelContent extends JPanel implements MouseListener {
    Rectangle[][] grid;
    String moveMode = "select";

    int[] start = new int[2];
    int[] end = new int[2];

    Game game;

    Rectangle selectRect;

    ChessBoardController controller = new ChessBoardController();

    public ChessPanelContent(Game game) {

        // Assign class variables
        this.game = game;

        selectRect = new Rectangle();

        // Size of Panel
        int dx = 500;
        int dy = 500;

        setPreferredSize(new Dimension(dx, dy));
        addMouseListener(this); // mouse inputs

        // Create grid for chess board
        grid = new Rectangle[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                grid[i][j] = new Rectangle(50 + 50 * j, 50 + 50 * i, 50, 50);
            }
        }
    }

    public Image load(String name) { //loads images
        return new ImageIcon(name).getImage();
    }

    /*
     *   Paints the chess board and the pieces on the board
     *  @param g - the graphics object used to draw the board and pieces
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        paintComponent(g);
        boardDrawing(g);
        g.setColor(Color.red);
        selectDrawing(g);
        try {
            pieceDrawing(g);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void boardDrawing(Graphics g){
        for(Rectangle[] rect_list : grid){
            for(Rectangle rect : rect_list){
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    private void selectDrawing(Graphics g){
        if (moveMode.equals("move")){
            g.drawRect(selectRect.x, selectRect.y, selectRect.width, selectRect.height);
        }
    }

    private void pieceDrawing(Graphics g) throws IOException {
        Piece[][] board = controller.getBoard(game);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (board[i][j] != null) {
                    BufferedImage img = ImageIO.read(new File("src/main/assets/"+ board[i][j].stringPath() + ".png"));
                    g.drawImage(img, grid[i][j].x, grid[i][j].y, 50, 50, null);
                }
            }
        }
    }

    private void gameOverPrompt() {
        if (controller.checkmate(game)) {
            if (game.whiteCheckmate()) {
                JOptionPane.showMessageDialog(null, "Black wins!");
            } else {
                JOptionPane.showMessageDialog(null, "White wins!");
            }
        }
    }

    //Checks if mouse is clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(grid[i][j].contains(e.getX(), e.getY()) && moveMode.equals("select")){
                    start[0] = i;
                    start[1] = j;
                    selectRect = grid[i][j];
                    moveMode = "move";
                    repaint();
                }
                else if(grid[i][j].contains(e.getX(), e.getY()) && moveMode.equals("move")){
                    end[0] = i;
                    end[1] = j;
                    controller.movePiece(game, start, end);
                    repaint();
                    moveMode = "select";
                    start = new int[2];
                    end = new int[2];
                    gameOverPrompt();

                }
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Gets info when mouse is pressed
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
