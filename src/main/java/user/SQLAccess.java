package user;
import user.loginregistration.*;
import user.adapters.*;

import java.sql.*;

// Framework Layer (Data Access Object)

public class SQLAccess implements DBAccessInterface {
    private Connection conn;
    public UserController userController = new UserController();

    public SQLAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:chessdb.sqlite");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
        }
    }

    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            System.out.println("Could not close connection");
        }
    }

    public boolean login(User userRequest) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            String username = userRequest.getUsername();
            String password = userRequest.getPassword();
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userController.setCurrentUser(userRequest);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("User could not be logged in.");
        }
        return false;
    }

    public boolean register(User userRequest) {
        try {
            PreparedStatement stmt = this.conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            String username = userRequest.getUsername();
            String password = userRequest.getPassword();
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("User cannot be registered");
            return false;
        }
    }

}
