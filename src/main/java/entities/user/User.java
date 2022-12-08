package entities.user;

// Entity Layer
public class User {
    private final String username;
    private final String password;


    /** Constructs a new User.
     * @param username username of user
     * @param password password of user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Gets the username of the user.
     * @return String username of user
     */
    public String getUsername() {
        return username;
    }

    /** Gets the password of the user.
     * @return String password of user
     */
    public String getPassword() {
        return password;
    }

}


