package user;

public class AuthManager {
    private UserManager userManager;
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

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
