package REPOSITORY;
import INTERFACES.IUserRepository;
import MODEL.Project;
import MODEL.Task;
import MODEL.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements IUserRepository {
    private final List<User> users = new ArrayList<>();
    private User user = new User ("user");

    //======save method========
    @Override
    public void save(User user) {
        users.add(user);
    }

    //======update method========
    @Override
    public void delete(User user){
        users.remove(user);
    }

    //======delete method========
    @Override
    public List<User> getAll(){
        return users;
    }
}
