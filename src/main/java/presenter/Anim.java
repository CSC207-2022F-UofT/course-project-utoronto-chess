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
        super("Anim");//shows title in the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes on exit

        AnimPanel game = new AnimPanel();//creates a new panel in the frame
        add(game);//adds panel to the frame
        pack();
//        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] arguments) {
        Anim frame = new Anim();
    }
}

class AnimPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    private boolean[] keys;
    Rectangle[][] grid = new Rectangle[8][8]; // Code should come from somewhere
    int dx = 900;
    int dy = 500;
    public AnimPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        setPreferredSize(new Dimension(dx, dy));
        addKeyListener(this); //key inputs
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
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

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
