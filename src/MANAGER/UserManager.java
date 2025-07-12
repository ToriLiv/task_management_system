package MANAGER;
import INTERFACES.*;
import MODEL.User;

import java.util.List;
/*
 * =========LISKOV SUBSTITUTION PRINCIPLE (LSP)==========
 * depends of IUserRepository abstraction, so any repository can be used
 * */

/*
 * ==============OPEN/CLOSED PRINCIPLE (OCP)==========
 * uses repository interface, to extend with the new repositories without changing taskmanager
 * */

public class UserManager implements IUserCreator {
    private final IUserRepository userRepository;
    //DEPENDENCY INVERSION PRINCIPLE (DIP) -> Depends on IUserRepository abstraction
    public UserManager(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //========CREATE USER========
    @Override
    public User createUser(String name) {
        User user = new MODEL.User(name);
        userRepository.save(user);
        return user;
    }

    //=======DELETE USER========
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    //=======LIST ALL USERS========
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    //=======SHOW ALL USERS========
    public void showAllUsers() {
        if (userRepository.getAll().isEmpty()) {
            System.out.println("No users available.");
            return;
        }
        List<User> users = userRepository.getAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
