package INTERFACES;
import MODEL.User;

import java.util.List;

/*
 * ==============OPEN/CLOSED PRINCIPLE (OCP)=============
 * interface allows adding new repository implementation without modifying existing code
 * */
public interface IUserRepository {
    void save(User user);
    void delete(User user);
    List<User> getAll();
}
