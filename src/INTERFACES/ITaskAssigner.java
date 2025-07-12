package INTERFACES;

import MODEL.Task;
import MODEL.User;

//-------------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSABILITY PRINCIPLE-------------
public interface ITaskAssigner {
    void assignTaskToUser(Task task, User user);
}
