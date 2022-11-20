package presenter;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import java.awt.MouseInfo;
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
    Rectangle[][] grid = new Rectangle[8][8]; // Code should come from somewhere
    int dx = 900;
    int dy = 500;
    public AnimPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        setPreferredSize(new Dimension(dx, dy));
        addMouseListener(this); // mouse inputs

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
        g.drawRect(50,50,400,400);
        g.setColor(Color.RED);
        g.drawRect(500 ,300, 50, 50); // button for login
        //g.drawRect(600, 50, 200, 70); // button 1
        //g.drawRect(600, 150, 200, 70); // button 2
        g.drawRect(600, 250, 200, 70); // button 3
        for(int i  = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                ((Graphics2D) g).draw(grid[i][j]);
            }
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
        } else if (600 < x && x < 810 && 280 < y && y < 350) {
            System.out.println("This is working");
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
