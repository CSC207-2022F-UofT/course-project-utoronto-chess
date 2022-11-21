package views;

import entities.user.User;

public class Menu {

    public User getSignedInUser() {
        return new User("username", "password");
    }

}
