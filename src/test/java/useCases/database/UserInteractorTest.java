package useCases.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInteractorTest {

    private UserInteractor userInteractor;

    @BeforeEach
    void setUp() {
        this.userInteractor = new UserInteractor();
    }

    @Test
    @DisplayName("UserInteractor should return null if username is too short.")
    void testNewUserRequestShortUsername() {
        assertNull(userInteractor.newUserRequest("no", "Password123!"));
        assertNull(userInteractor.newUserRequest("n", "Password123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if username is empty.")
    void testNewUserRequestEmptyUsername() {
        assertNull(userInteractor.newUserRequest("", "Password123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if username is too long.")
    void testNewUserRequestLongUsername() {
        assertNull(userInteractor.newUserRequest("thisusernameiswaytoolong", "Password123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if username contains invalid characters.")
    void testNewUserRequestInvalidUsername() {
        assertNull(userInteractor.newUserRequest("username!", "Password123!"));
        assertNull(userInteractor.newUserRequest("username@", "Password123!"));
        assertNull(userInteractor.newUserRequest("username#", "Password123!"));
        assertNull(userInteractor.newUserRequest("username$", "Password123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password is too short.")
    void testNewUserRequestShortPassword() {
        assertNull(userInteractor.newUserRequest("username", "pass"));
        assertNull(userInteractor.newUserRequest("username", "p"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password is empty.")
    void testNewUserRequestEmptyPassword() {
        assertNull(userInteractor.newUserRequest("username", ""));
    }

    @Test
    @DisplayName("UserInteractor should return null if password is too long.")
    void testNewUserRequestLongPassword() {
        assertNull(userInteractor.newUserRequest("username", "thispasswordiswaytoolong"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password does not contain a lowercase letter.")
    void testNewUserRequestPasswordNoLowercase() {
        assertNull(userInteractor.newUserRequest("username", "PASSWORD123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password does not contain an uppercase letter.")
    void testNewUserRequestPasswordNoUppercase() {
        assertNull(userInteractor.newUserRequest("username", "password123!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password does not contain a digit.")
    void testNewUserRequestPasswordNoDigit() {
        assertNull(userInteractor.newUserRequest("username", "Password!"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password does not contain a special character.")
    void testNewUserRequestPasswordNoSpecialCharacter() {
        assertNull(userInteractor.newUserRequest("username", "Password123"));
    }

    @Test
    @DisplayName("UserInteractor should return null if password contains a space.")
    void testNewUserRequestPasswordContainsSpace() {
        assertNull(userInteractor.newUserRequest("username", "Password 123!"));
    }

    @Test
    @DisplayName("UserInteractor should return the user if username and password are valid.")
    void testNewUserRequestValidUsernameAndPassword() {
        assertNotNull(userInteractor.newUserRequest("username", "Password123!"));
    }

}