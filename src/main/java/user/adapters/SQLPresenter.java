package user.adapters;

import user.usecases.SQLGateway;
import user.entities.User;

import java.sql.*;

// Data Access Layer

public class SQLPresenter implements SQLGateway {

    private final Connection conn;

    public SQLPresenter() {
            try {
                Class.forName("org.sqlite.JDBC");
                this.conn = DriverManager.getConnection("jdbc:sqlite:chessdb.sqlite");
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found");
                throw new RuntimeException(e);
            } catch (SQLException e) {
                System.out.println("Could not connect to database");
                throw new RuntimeException(e);
            }
        }

    public boolean addUser(User user) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Could not add user to database");
            throw e;
        }
    }

    public boolean lookupUsername(String username) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw e;
        }
    }

    public boolean checkCredentials(User user) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not find user in database");
            throw e;
        }
    }

    public int lookupELO(String username) throws SQLException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ELO FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.getInt("ELO");
        } catch (SQLException e) {
            System.out.println("Could not find user in database");
            throw e;
        }
    }

}

