package useCases.database;

// use case layer

import entities.user.User;

public class UserInteractor {
    private boolean checkValidUsername(String username) {
        /*
         3 <= username.length() <= 15
         username must only contain alphanumeric characters, underscores, hyphens, and periods.
         */
        return username.matches("^[A-Za-z0-9_.-]{3,15}$");
    }

    private boolean checkValidPassword(String password) {
        /*
         8 <= password.length() <= 20
         password must contain at least one uppercase letter, one lowercase letter, and one digit.
         password must have one of the following special characters: !@#$%^&*()_+

         source: https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
         */
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public User newUserRequest(String username, String password) {
        if (checkValidUsername(username) && checkValidPassword(password)) {
            return new User(username, password);
        }
        return null;
    }


}


