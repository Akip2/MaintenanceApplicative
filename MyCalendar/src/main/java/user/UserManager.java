package user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final List<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean userExists(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }
}
