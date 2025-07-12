package INTERFACES;

import MODEL.Task;

//-----------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSABILITY PRINCIPLE------------
public interface ITaskCreator {
    Task createTask(String title, String description);
}


