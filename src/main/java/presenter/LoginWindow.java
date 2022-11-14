package presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow implements ActionListener {
    private JPasswordField passwordtext = new JPasswordField();
    private JTextField usertext = new JTextField(20);
    private String name;
    private String pw;

    private boolean login_success;
    public LoginWindow() {
        JFrame frame = new JFrame();
        JLabel username = new JLabel("User Name");
        JLabel password = new JLabel("Password");
        JButton sign_in = new JButton("Sign in");
        JLabel success = new JLabel();

        username.setBounds(10, 20 , 80, 25);
        usertext.setBounds(100, 20, 165, 25);
        password.setBounds(10, 50, 80, 25);
        passwordtext.setBounds(100, 50, 165, 25);
        sign_in.setBounds(10, 80, 80, 25);
        success.setBounds(10, 110, 300, 25); // informs user if login was successful

        username.setFont(new Font(null, Font.PLAIN, 12));
        password.setFont(new Font(null, Font.PLAIN, 12));
        sign_in.addActionListener(this);

        frame.add(username);
        frame.add(usertext);
        frame.add(password);
        frame.add(passwordtext);
        frame.add(sign_in);

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Sign In Clicked...");
        this.name = this.usertext.getText();
        char[] pw_char = this.passwordtext.getPassword();
        this.pw = new String(pw_char);
        // need condition here, coordinate with database
        this.login_success = true;
        //System.out.println("name: " + this.getName() + " pw: " + this.getPw() + " login: " + this.isLogin_success());
    }
    public String getName(){
        return this.name;
    }
    public String getPw(){
        return this.pw;
    }
    public boolean isLogin_success(){
        return this.login_success;
    }
}
