package controller.database;

import entities.user.User;
import useCases.database.UserInteractor;


public class UserController {

    private User currentUser = null;

    private final DatabaseGateway gateWay;
    private static final UserInteractor userInteractor = new UserInteractor();

    public UserController(DatabaseGateway gateWay) {
        this.gateWay = gateWay;
    }

    /** Attempts to register a new user with the given username and password by connecting to the database.
     * @param username desired username
     * @param password desired password
     * @return true if user was successfully created, false otherwise
     */
    public boolean register(String username, String password) {
        User user = userInteractor.newUserRequest(username, password);
        if (user != null) {
            try {
                if (!gateWay.lookupUsername(user.getUsername())) {
                    gateWay.addUser(user);
                    return true;
                }
                System.out.println("Username already exists.");
                return false;
            } catch (RuntimeException e) {
                System.out.println("Could not connect to database.");
                return false;
            }
        }
        System.out.println("Username or password is invalid.");
        return false;
    }

    /** Attempts to login a user with the given username and password by connecting to the database.
     * @param username requested username
     * @param password attempted password
     * @return true if user was successfully logged in, false otherwise
     */
    public boolean login(String username, String password) {
        User user = userInteractor.newUserRequest(username, password);
        if (user != null) {
            try {
                if (gateWay.checkCredentials(user)) {
                    currentUser = user;
                    return true;
                }
            } catch (RuntimeException e) {
                System.out.println("Could not find user in database.");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Username or password is invalid.");
        return false;
    }

    /** Retrieves the ELO of a requested user.
     * @param username user to check ELO
     * @throws RuntimeException if user is not found in database
     * @return user's ELO if found, 0 otherwise
     */
    public int getELO(String username) throws RuntimeException {
        try {
            return gateWay.lookupELO(username);
        } catch (RuntimeException e) {
            System.out.println("Could not connect to database.");
            throw new RuntimeException(e);
        }
    }

    /** Updates the ELO of a user in the database.
     * @param username user to update ELO
     * @param elo new ELO
     * @throws RuntimeException if user is not found in database
     */
    public void updateELO(String username, int elo) throws RuntimeException {
        try {
            gateWay.updateELO(username, elo);
        } catch (RuntimeException e) {
            System.out.println("Could not find user in database.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the user from the database.
     * @param username user to delete
     */
    public void deleteUser(String username) {
        try {
            gateWay.deleteUser(username);
        } catch (RuntimeException e) {
            System.out.println("Could not find user in database.");
            throw new RuntimeException(e);
        }
    }

    /** Returns the current user.
     * @return current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /** Logs out the current user. */
    public void logout() {
        currentUser = null;
    }
}


