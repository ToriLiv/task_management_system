package INTERFACES;

import MODEL.Task;
import java.util.List;

//interface segregation principle
public interface ITaskRepository {
    void save(Task task);
    void delete(Task task);
    void update(Task task);
    List<Task> getAll();
}
