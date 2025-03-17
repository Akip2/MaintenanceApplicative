package user;

public class User {
    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public boolean isPassword(String pass) {
        return this.password.equals(pass);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
