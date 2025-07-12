package INTERFACES;

import MODEL.Task;
import MODEL.TaskStatus;

//-------------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSABILITY-----------
public interface ITaskStatusChanger {
    void changeStatus(Task task, TaskStatus status);
}
