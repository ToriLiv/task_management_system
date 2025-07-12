package INTERFACES;

import MODEL.Task;
import MODEL.User;

//------------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSABILITY PRINCIPLE--------------
public interface IUserCreator {
    User createUser(String name);
}
