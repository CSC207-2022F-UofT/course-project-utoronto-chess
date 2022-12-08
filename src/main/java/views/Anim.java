package views;


import useCases.game.Game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.MouseInfo;

public class Anim extends JFrame {

    public Anim() {
        super("Utoronto Chess");//shows title in the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit
        AnimPanel game = new AnimPanel();//creates a new panel in the frame
        add(game);//adds panel to the frame
        pack();
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Anim frame = new Anim();
    }
}

class AnimPanel extends JPanel implements ActionListener, MouseListener {
    private boolean[] keys;
    private String username;
    private boolean[] login_success = new boolean[1];
    Rectangle[][] grid = new Rectangle[8][8]; // Code should come from somewhere
    int dx = 900;
    int dy = 500;
    public AnimPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        setPreferredSize(new Dimension(dx, dy));
        addMouseListener(this); // mouse inputs
        login_success[0] = false;
        /// THIS CODE SHOULD COME FROM SOMEWHERE ELSE
        for(int i  = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                grid[i][j] = new Rectangle(50 + 50 * i, 50 + 50 * j, 50, 50);
            }
        }

        ///

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //g.drawRect(50,50,400,400);
        g.drawString("requires log in", 600, 335);
        g.drawString("Login", 510, 330);
        g.setFont(new Font("Serif", Font.ITALIC, 36));
        g.drawString("Play", 660, 300);
        g.drawString("Chess Game", 100, 100);
        g.setColor(Color.BLACK);
        g.drawRect(500 ,300, 50, 50); // button for login
        g.drawRect(600, 250, 200, 70); // button 3
        if(login_success[0]){
           // g.drawString(username, 500, 300); //displays username in corner once logged in
            //g.clearRect(600, 335, 200, 10); // cleans the 'requires' log in
            System.out.println("changes user name");
            //repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(grid[i][j].contains(e.getPoint())){
                    System.out.println(i + " " + j);
                }
            }
        }
        Point p  = MouseInfo.getPointerInfo().getLocation();
        int x = p.x;
        int y = p.y;
        if(500< x && x < 560 && 330 < y && y < 380){ // this checks if mouse clicks over button
            LoginWindow newwindow = new LoginWindow();// opens login window when clicked
            AnimPanel.this.login_success = newwindow.isLogin_success();
            System.out.println("this is the login button");
        } else if (600 < x && x < 810 && 280 < y && y < 350){
            if(AnimPanel.this.login_success[0]){
                System.out.println("This is working");
                Game game = new Game();
                ChessPanel chessPanel = new ChessPanel(game);
            }
            System.out.println("Log In first");

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
}
