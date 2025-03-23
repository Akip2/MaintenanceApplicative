import user.AuthManager;
import user.User;
import user.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    public UserManager userManager;
    public AuthManager authManager;

    @BeforeEach
    public void setUp() {
        userManager = new UserManager();
        authManager = new AuthManager(userManager);
    }

    @Test
    @DisplayName("Add user")
    public void testAddUser() {
        User u = new User("John", "pass");
        userManager.addUser(u);

        assertTrue(userManager.getUsers().contains(u));
    }

    @Test
    @DisplayName("Get user by name")
    public void testGetUserByName() {
        User u = new User("John", "pass");
        userManager.addUser(u);

        assertEquals(userManager.getUser("John"), u);
    }

    @Test
    @DisplayName("User exists if user not added")
    public void testUserExists1() {
        assertFalse(userManager.userExists("John"));
    }

    @Test
    @DisplayName("User exists if user added")
    public void testUserExists2() {
        User user = new User("John", "pass");
        userManager.addUser(user);
        assertTrue(userManager.userExists("John"));
    }

    @Test
    @DisplayName("Authenticate with no account")
    public void testAuthenticateWithNoAccount() {
        assertFalse(authManager.authenticate("John", "pass"));
    }

    @Test
    @DisplayName("Authenticate with wrong password")
    public void testAuthenticateWithWrongPassword() {
        User u = new User("John", "pass");
        userManager.addUser(u);

        assertFalse(authManager.authenticate("John", "wrong"));
    }

    @Test
    @DisplayName("Authenticate correctly")
    public void testAuthenticateCorrectly() {
        User u = new User("John", "pass");
        userManager.addUser(u);

        assertTrue(authManager.authenticate("John", "pass"));
    }

    @Test
    @DisplayName("Get logged in user")
    public void testGetLoggedInUser() {
        User u = new User("John", "pass");
        userManager.addUser(u);
        authManager.authenticate("John", "pass");

        assertEquals(u, authManager.getLoggedInUser());
    }

    @Test
    @DisplayName("Disconnect")
    public void testDisconnect() {
        User u = new User("John", "pass");
        userManager.addUser(u);
        authManager.authenticate("John", "pass");
        authManager.disconnect();

        assertFalse(authManager.isLoggedIn());
    }
}

