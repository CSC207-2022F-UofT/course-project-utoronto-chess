package controller.database;
import entities.user.User;
import useCases.database.UserInteractor;
import java.sql.SQLException;

public class UserController {

    public User currentUser = null;
    private static final SQLPresenter sqlPresenter = new SQLPresenter();
    private static final UserInteractor userInteractor = new UserInteractor();

    public boolean register(String username, String password) {
        User user = userInteractor.newUserRequest(username, password);
        if (user != null) {
            try {
                if (!sqlPresenter.lookupUsername(user.getUsername())) {
                    sqlPresenter.addUser(user);
                    return true;
                }
                System.out.println("Username already exists.");
                return false;
            } catch (SQLException e) {
                System.out.println("Could not connect to database.");
                return false;
            }
        }
        System.out.println("Username or password is invalid.");
        return false;
    }

    public boolean login(String username, String password) {
        User user = userInteractor.newUserRequest(username, password);
        if (user != null) {
            try {
                if (sqlPresenter.checkCredentials(user)) {
                    currentUser = user;
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Could not find user in database.");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Username or password is invalid.");
        return false;
    }

    public int getELO(String username) {
        try {
            return sqlPresenter.lookupELO(username);
        } catch (SQLException e) {
            System.out.println("Could not find user in database.");
            throw new RuntimeException(e);
        }
    }

    // TODO: implement a method to update the ELO of a user

    public void logout() {
        currentUser = null;
    }
}


