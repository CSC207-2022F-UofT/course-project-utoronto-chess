package menu;
import user.entities.User;

public class Menu {

    public User getSignedInUser() {
        return new User("username", "password");
    }

}
