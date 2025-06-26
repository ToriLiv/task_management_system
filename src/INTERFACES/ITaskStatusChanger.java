package INTERFACES;

import MODEL.Task;
import MODEL.TaskStatus;

//interface segregation principle
public interface ITaskStatusChanger {
    void changeStatus(Task task, TaskStatus status);
}
