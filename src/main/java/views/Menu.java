package views;

import user.User;

public class Menu {

    public User getSignedInUser() {
        return new User("username", "password");
    }

}
