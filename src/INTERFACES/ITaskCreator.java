package INTERFACES;

import MODEL.Task;

//interface segregation principle & single responsability
public interface ITaskCreator {
    Task createTask(String title, String description);
}


