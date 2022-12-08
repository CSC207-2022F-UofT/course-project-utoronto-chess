package views;

import controller.database.DatabaseGateway;
import controller.database.SQLPresenter;
import controller.database.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginWindow {
    private final boolean[] ar = new boolean[1];
    private String user;
    /**
    Initiates login window labels, icons, and input fields.
    Does not return, only changes the value of the logged in boolean
    */
    public LoginWindow() {
        JPasswordField passwordtext = new JPasswordField();
        JTextField usertext = new JTextField(20);
        JFrame frame = new JFrame();
        JLabel username = new JLabel("User Name");
        JLabel password = new JLabel("Password");
        JButton signIn = new JButton("Sign in");
        JButton register = new JButton("Register");
        JLabel success = new JLabel("<html>username must only contain alphanumeric <br/>characters," +
                " underscores, hyphens, and periods <br/> Password must have one letter, number, " +
                "capital letter, and symbol. <br/> " +
                "Successful passwords include: Aiden123!<html>");

        username.setBounds(10, 20 , 80, 25);
        usertext.setBounds(100, 20, 165, 25);
        password.setBounds(10, 50, 80, 25);
        passwordtext.setBounds(100, 50, 165, 25);
        signIn.setBounds(10, 80, 90, 25);
        register.setBounds(110, 80, 90, 25);
        success.setBounds(10, 105, 250, 150); // informs user if login was successful

        // sets the fonts for buttons
        username.setFont(new Font(null, Font.PLAIN, 12));
        password.setFont(new Font(null, Font.PLAIN, 12));

        DatabaseGateway gateWay = new SQLPresenter();

        UserController checker = new UserController(gateWay);
        
        // Action when user clicks sign in button
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checker.login(usertext.getText(), String.valueOf(passwordtext.getPassword()))){
                    success.setText("Signed in"); //checks user info by calling usercontroller
                    ar[0] = true;
                    user = usertext.getText();
                }
                else{
                    success.setText("Sign in unsuccessful");
                }
                // check username and password here
            }
        });
        
        // Action when user clicks register button
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(usertext.getText() + " " + String.valueOf(passwordtext.getPassword()));
                if(checker.register(usertext.getText(), String.valueOf(passwordtext.getPassword()))) {
                    success.setText("Registered"); //checks register info using usercontroller
                    ar[0] = true;
                    user = usertext.getText();
                }else {
                    success.setText("Register unsuccessful");
                }
            }
        });

        frame.add(username);
        frame.add(usertext);
        frame.add(password);
        frame.add(passwordtext);
        frame.add(signIn);
        frame.add(register);
        frame.add(success);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    /**
    Fetcher method for login
    */
    public boolean[] isLoginSuccess(){
        return ar;
    }
    
    /**
    fetcher method for username
    */
    public String getUsername(){
        return this.user;
    }
}
