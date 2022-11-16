package user.loginregistration;

// use case layer

public class UserInteractor implements UserLoginModel, UserRegistrationModel {

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

    public User loginRequest(String username, String password) throws IllegalArgumentException {
        if (checkValidUsername(username) && checkValidPassword(password)) {
            return new User(username, password);
        }
        throw new IllegalArgumentException("Invalid username or password.");
    }

    public boolean logoutRequest(String username) {
        return true;
    }

    public User registerRequest(String username, String password1, String password2) throws IllegalArgumentException {
        if (checkValidUsername(username) && checkValidPassword(password1)) {
            if (password1.equals(password2)) {
                return new User(username, password1);
            }
            throw new IllegalArgumentException("Passwords do not match.");
        }
        throw new IllegalArgumentException("Invalid username or password.");
    }
}

