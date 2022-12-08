package views;


import useCases.game.Game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.MouseInfo;

public class Menu extends JFrame {

    /**
    Initiates the menu class and sets the frames to be visible
    */
    public Menu() {
        super("UToronto Chess");//shows title in the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit
        MenuPanel game = new MenuPanel();//creates a new panel in the frame
        add(game);//adds panel to the frame
        pack();
        setVisible(true);
    }

static class MenuPanel extends JPanel implements ActionListener, MouseListener {
    private String username;
    private boolean[] loginSuccess = new boolean[1];
    int dx = 900;
    int dy = 500;
    public MenuPanel() {
        /*
          Adds listeners for mouses and sets logic instance variable for user login
         */
        setPreferredSize(new Dimension(dx, dy));
        addMouseListener(this); // mouse inputs
        loginSuccess[0] = false;
    }

    /**
     * Paints the board with buttons and labels, checks if user is logged in.
     */
    @Override
    public void paint(Graphics g) {
        g.drawString("requires log in", 600, 335);
        g.drawString("Login", 510, 330);
        g.setFont(new Font("Serif", Font.ITALIC, 36));
        g.drawString("Play", 660, 300);
        g.drawString("Chess Game", 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(500 ,300, 50, 50); // button for login
        g.drawRect(600, 250, 200, 70); // button 3
        if(loginSuccess[0]){
           g.drawString(username, 500, 300); //displays username in corner once logged in
            g.clearRect(600, 335, 200, 10); // cleans the 'requires' log in
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
    Overides default mouse click action listener to respond to positions of buttons and
    calls the login window.
    */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p  = MouseInfo.getPointerInfo().getLocation();
        int x = p.x;
        int y = p.y;
        if(500< x && x < 560 && 330 < y && y < 380){ // this checks if mouse clicks over button
            LoginWindow newwindow = new LoginWindow();// opens login window when clicked
            MenuPanel.this.loginSuccess = newwindow.isLoginSuccess();
            username = newwindow.getUsername();
        } else if (600 < x && x < 810 && 280 < y && y < 350){
            if(MenuPanel.this.loginSuccess[0]){
                Game game = new Game();
                ChessPanel chessPanel = new ChessPanel(game);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}}
