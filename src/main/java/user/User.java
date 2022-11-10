package user;

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
         */
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
    }

    public boolean signOut() {
        return true;
    }

    public boolean register(String username, String password) {
        return true;
    }

    public boolean signIn(String username, String password) {
        return true;
    }



}
