package INTERFACES;

import MODEL.Task;
import java.util.List;

//----------INTERFACE SEGREGATION PRINCIPLE----------
/*
* OPEN/CLOSED PRINCIPLE (OCP)
* interface allows adding new repository implementation without modifying existing code
* */
public interface ITaskRepository {
    void save(Task task);
    void delete(Task task);
    void update(Task task);
    List<Task> getAll();
}
