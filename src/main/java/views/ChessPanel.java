package views;

import entities.board.Board;
import entities.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessPanel extends JFrame{

    public ChessPanel() {
        super("UTorontoChess");//shows title in the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit

        ChessPanelContent game = new ChessPanelContent();//creates a new panel in the frame
        add(game);//adds panel to the frame
        pack();
        // setResizable(false);
        setVisible(true);
    }
}

class ChessPanelContent extends JPanel implements KeyListener, ActionListener, MouseListener {
    private final boolean[] keys;
    private final Timer myTimer;

    Board board;
    Rectangle[][] grid;
    String moveMode;

    int[] start;
    int[] end;

//    private Image background;

    public ChessPanelContent() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        myTimer = new Timer(50, this);
//        Font fnt = new Font("Comic Sans", Font.BOLD, 30);

        //boundries
        int dx = 900;
        //dimensions of game
        int dy = 500;
        setPreferredSize(new Dimension(dx, dy));
        addKeyListener(this); //key inputs
        addMouseListener(this); // mouse inputs

        moveMode = "select";
        start = new int[2];
        end = new int[2];

        board = new Board();
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
        boardDrawing(g);
        pieceDrawing(g);
    }

    private void boardDrawing(Graphics g){
        for(Rectangle[] rect_list : grid){
            for(Rectangle rect : rect_list){
                g.drawRect(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    private void pieceDrawing(Graphics g){
        Piece[][] pieces = board.getBoard();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                typeDrawing(g, pieces, i, j);
            }
        }
    }

    private void typeDrawing(Graphics g, Piece[][] pieces, int i, int j){
        if(pieces[i][j] instanceof Pawn){
            if(pieces[i][j].isWhite()) {
                g.drawString("P", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("p", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
        if(pieces[i][j] instanceof Rook){
            if(pieces[i][j].isWhite()) {
                g.drawString("R", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("r", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
        if(pieces[i][j] instanceof Knight){
            if(pieces[i][j].isWhite()) {
                g.drawString("N", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("n", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
        if(pieces[i][j] instanceof Bishop){
            if(pieces[i][j].isWhite()) {
                g.drawString("B", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("b", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
        if(pieces[i][j] instanceof Queen){
            if(pieces[i][j].isWhite()) {
                g.drawString("Q", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("q", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
        if(pieces[i][j] instanceof King){
            if(pieces[i][j].isWhite()) {
                g.drawString("K", grid[i][j].x + 25, grid[i][j].y + 25);
            }
            else{
                g.drawString("k", grid[i][j].x + 25, grid[i][j].y + 25);
            }
        }
    }

    //Checks if mouse is clicked
    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(grid[i][j].contains(e.getPoint()) && moveMode.equals("select")){
                    start[0] = i;
                    start[1] = j;
                    moveMode = "move";
                }
                else if(grid[i][j].contains(e.getPoint()) && moveMode.equals("move")){
                    end[0] = i;
                    end[1] = j;
                    board.movePiece(start, end);
                    System.out.println(board.getBoard()[start[0]][start[1]]instanceof Pawn);
                    moveMode = "select";
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
        repaint();
    }

}
