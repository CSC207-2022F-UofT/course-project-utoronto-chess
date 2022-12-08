package controller.database;

import useCases.database.SQLGateway;
import entities.user.User;
import java.sql.*;

// use case layer

public class SQLPresenter implements DatabaseGateway {

    private final Connection conn;

    public SQLPresenter() throws RuntimeException{
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

    /**
     * Attempts to add a user to the database.
     *
     * @param user user to add to database
     * @throws SQLException if there is an error connecting with database
     */
    public void addUser(User user) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not add user to database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a user exists in the database.
     *
     * @param username username to search for in database
     * @return true if username is found, false otherwise
     * @throws SQLException if there is an error connecting with database
     */
    public boolean lookupUsername(String username) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks that the user login request credentials match those in the database.
     * Credentials must be passed in as a User object.
     *
     * @param user user to check credentials for
     * @return true if credentials match, false otherwise
     * @throws SQLException if there is an error connecting with database
     */
    public boolean checkCredentials(User user) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not find user in database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the ELO of a user.
     *
     * @param username user to check ELO
     * @return desired user's ELO
     * @throws SQLException if there is an error connecting with database
     */
    public int lookupELO(String username) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ELO FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.getInt("ELO");
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the ELO of a user.
     *
     * @param username user to update ELO
     * @param elo new ELO
     * @throws SQLException if there is an error connecting with database
     */
    public void updateELO(String username, int elo) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET ELO = ? WHERE username = ?");
            ps.setInt(1, elo);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not update user in database");
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a user from the database.
     * @param username user to delete
     * @throws SQLException if there is an error connecting with database
     */
    public void deleteUser(String username) throws RuntimeException {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE username = ?");
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not delete user from database");
            throw new RuntimeException(e);
        }

    }
}

