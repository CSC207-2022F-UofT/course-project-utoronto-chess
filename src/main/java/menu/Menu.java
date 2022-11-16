package menu;
import user.loginregistration.User;

public class Menu {

    public User getSignedInUser() {
        return new User("username", "password");
    }
}
