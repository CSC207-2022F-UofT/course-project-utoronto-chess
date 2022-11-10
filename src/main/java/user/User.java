package user;
import java.sql.*;

public class User {

    public boolean checkValidUsername(String username) {
        /*
         3 <= username.length() <= 15
         username must only contain alphanumeric characters, underscores, hyphens, and periods.
         */
        return username.matches("^[A-Za-z0-9_.-]{3,15}$");
    }

    public boolean checkValidPassword(String password) {
        /*
         8 <= password.length() <= 20
         password must contain at least one uppercase letter, one lowercase letter, and one digit.
         password must have one of the following special characters: !@#$%^&*()_+

         source: https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
         */
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public boolean signOut() {
        return true;
    }

    public boolean register(String username, String password) {
        if (checkValidUsername(username) && checkValidPassword(password)) {
            try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:chessdb.sqlite");
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";
                stmt.executeUpdate(sql);
                return true;
            } catch (SQLException e) {
                System.out.println("Could not connect to database");
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC driver not found");
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private boolean checkUsernameExists(String username) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:chessdb.sqlite");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            throw new RuntimeException(e);
        }
    }

    private boolean checkPasswordCorrect(String username, String password) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:chessdb.sqlite");
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Could not connect to database");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found");
            throw new RuntimeException(e);
        }
    }

    public boolean signIn(String username, String password) {
        if (checkValidUsername(username) && checkValidPassword(password)) {
            if (checkUsernameExists(username)) {
                // TODO: sign in user
                return checkPasswordCorrect(username, password);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        User aiden = new User();
        System.out.println(aiden.signIn("aiden", "Aiden123!"));
    }
}

