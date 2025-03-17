package user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;

    public UserManager() {
        users = new ArrayList<User>();
        users.add(new User("Pierre", "KiRouhl"));
        users.add(new User("Roger", "Chat"));
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUser(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }
}
