package INTERFACES;

import MODEL.Task;
import MODEL.User;

//interface segregation principle & single responsability
public interface ITaskAssigner {
    void assignTaskToUser(Task task, User user);
}
