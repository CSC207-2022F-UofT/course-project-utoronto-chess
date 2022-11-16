package user.adapters;
import user.loginregistration.User;

public class UserController {

    public User currentUser = null;
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}
