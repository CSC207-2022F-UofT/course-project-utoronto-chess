package entities.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("User constructor should allow invalid and valid usernames.")
    void testUsername() {
        User user = new User("username" , "password");
        assertEquals("username", user.getUsername());
    }

    @Test
    @DisplayName("User constructor should allow invalid and valid passwords.")
    void testPassword() {
        User user = new User("username" , "password");
        assertEquals("password", user.getPassword());
    }
}