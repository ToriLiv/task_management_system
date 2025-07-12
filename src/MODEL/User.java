package MODEL;

public class User {
    private String name;

    //constructor
    public User(String name) {
        this.name = name;
    }

    //method get
    public String getName() {
        return name;
    }

    public String toString() {
        return "User: " + name;
    }
}
