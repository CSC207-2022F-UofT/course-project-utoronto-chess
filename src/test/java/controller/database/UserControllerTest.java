package controller.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserController userController;

    @BeforeEach
    void setUp() {
        this.userController = new UserController();
    }

    @AfterEach
    void cleanUp() {
        try {
            userController.deleteUser("adminuser");
        } catch (Exception e) {
            // do nothing
        }
    }

    @Test
    @DisplayName("register should return false if the username already exists.")
    void testRegisterUsernameTaken() {
        userController.register("adminuser", "Testpass123!");
        assertFalse(userController.register("adminuser", "Testerpass123!"));
    }

    @Test
    @DisplayName("register should return false if the username is invalid.")
    void testRegisterInvalidUsername() {
        assertFalse(userController.register("##", "Testpass123!"));
    }

    @Test
    @DisplayName("register should return false if the password is invalid.")
    void testRegisterInvalidPassword() {
        assertFalse(userController.register("adminuser", "##"));
    }

    @Test
    @DisplayName("register should return true if the username and password are valid.")
    void testRegisterValidRequest() {
        assertTrue(userController.register("adminuser", "Testpass123!"));
    }

    @Test
    @DisplayName("login should return true if the user exists and credentials are valid")
    void testLoginValidCredentials() {
        userController.register("adminuser", "Testpass123!");
        assertTrue(userController.login("adminuser", "Testpass123!"));
    }

    @Test
    @DisplayName("login should return false if the user exists and credentials are invalid")
    void testLoginInvalidCredentials() {
        userController.register("adminuser", "Testpass123!");
        assertFalse(userController.login("adminuser", "Testpass123"));
        assertFalse(userController.login("admin123", "Testpass123!"));
    }

    @Test
    @DisplayName("getELO should return 0 if the user does not exist")
    void testGetELOInvalidUser() {
        assertEquals(0, userController.getELO("admin123"));
    }

    @Test
    @DisplayName("getELO should return the ELO of the user if the user exists")
    void testGetELOValidUser() {
        userController.register("adminuser", "Testpass123!");
        assertEquals(1000, userController.getELO("adminuser"));
    }

    @Test
    @DisplayName("getELO should return the ELO of the correct ELO after it has been updated")
    void testGetELOUpdated() {
        userController.register("adminuser", "Testpass123!");
        userController.updateELO("adminuser", 1200);
        assertEquals(1200, userController.getELO("adminuser"));
    }

    @Test
    @DisplayName("updateELO should not update anything if the user does not exist")
    void updateELOInvalidUser() {
        userController.updateELO("admin", 1200);
        assertEquals(0, userController.getELO("admin"));
    }

    @Test
    @DisplayName("updateELO should update the ELO of the user if the user exists")
    void updateELOValidUser() {
        userController.register("adminuser", "Testpass123!");
        assertEquals(1000, userController.getELO("adminuser"));
        userController.updateELO("adminuser", 1200);
        assertEquals(1200, userController.getELO("adminuser"));
        }

    @Test
    @DisplayName("logout should set the curretuser to null")
    void testLogout() {
        userController.register("adminuser", "Testpass123!");
        userController.login("adminuser", "Testpass123!");
        assertEquals("adminuser", userController.getCurrentUser().getUsername());
        userController.logout();
        assertNull(userController.getCurrentUser());
    }

    @Test
    @DisplayName("deleteUser should delete the user if the user exists")
    void testDeleteUserValid() {
        userController.register("adminuser", "Testpass123!");
        assertEquals(1000, userController.getELO("adminuser"));
        userController.deleteUser("adminuser");
        assertEquals(0, userController.getELO("adminuser"));
    }

    @Test
    @DisplayName("userController should work for basic use cases")
    void testEverything() {
        userController.register("adminuser", "Testpass123!");
        userController.login("adminuser", "Testpass123!");
        assertEquals("adminuser", userController.getCurrentUser().getUsername());
        assertEquals(1000, userController.getELO("adminuser"));
        userController.updateELO("adminuser", 1200);
        assertEquals(1200, userController.getELO("adminuser"));
        userController.logout();
        assertNull(userController.getCurrentUser());
    }

}