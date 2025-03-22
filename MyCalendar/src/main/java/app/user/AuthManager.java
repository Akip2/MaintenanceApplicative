package app.user;

public class AuthManager {
    private final UserManager userManager;
    private User loggedInUser;

    public AuthManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean authenticate(String username, String password) {
        User user = userManager.getUser(username);
        if (user != null && user.isPassword(password)) {
            loggedInUser = user;
            return true;
        } else {
            return false;
        }
    }

    public void disconnect() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
