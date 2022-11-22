package views;

import controller.game.ChessBoardController;
import entities.pieces.*;
import useCases.game.Game;

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
        // setResizable(false);
        setVisible(true);
    }
}

class ChessPanelContent extends JPanel implements KeyListener, ActionListener, MouseListener {
    private final boolean[] keys;
    private final Timer myTimer;

    Rectangle[][] grid;
    String moveMode;

    int[] start = new int[2];
    int[] end = new int[2];

    Game game;

    ChessBoardController controller = new ChessBoardController();

//    private Image background;

    public ChessPanelContent(Game game) {

        // Assign class variables
        this.game = game;
        moveMode = "select";


        keys = new boolean[KeyEvent.KEY_LAST + 1];
        myTimer = new Timer(1000, this);
//        Font fnt = new Font("Comic Sans", Font.BOLD, 30);

        // Size of Panel
        int dx = 900;
        int dy = 500;

        setPreferredSize(new Dimension(dx, dy));
        addKeyListener(this); //key inputs
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


    // addNotify triggers when the Panel gets added to the frame.
    // Using this avoids null-pointer exceptions.
    // x.y() - if x is null, we get null-pointer exception
    @Override
    public void addNotify() {
        super.addNotify();
        setFocusable(true);
        requestFocus();
        myTimer.start();
    }

    //used to draw all aspects of the game
    @Override
    public void paint(Graphics g) {
        paintComponent(g);
        boardDrawing(g);
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

    //Checks if mouse is clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(grid[i][j].contains(e.getX(), e.getY()) && moveMode.equals("select")){
                    start[0] = i;
                    start[1] = j;
                    moveMode = "move";
                }
                else if(grid[i][j].contains(e.getX(), e.getY()) && moveMode.equals("move")){
                    end[0] = i;
                    end[1] = j;
                    controller.movePiece(game, start, end);
                    repaint();
                    moveMode = "select";
                    start = new int[2];
                    end = new int[2];
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

}
