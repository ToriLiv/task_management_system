package MANAGER;

//importar paquetes
import INTERFACES.*;
import MODEL.Task;
import MODEL.User;
import MODEL.TaskStatus;

import java.util.List;

/*
* ============LISKOV SUBSTITUTION PRINCIPLE (LSP)============
* depends of ITaskRepository abstraction, so any repository can be used
* */

/*
* ==============OPEN/CLOSED PRINCIPLE (OCP)================
* uses repository interface, to extend with the new repositories without changing taskmanager
* */

public class TaskManager implements ITaskCreator, ITaskAssigner, ITaskStatusChanger{
    private final ITaskRepository repository; //dependencies
    //DEPENDENCY INVERSION PRINCIPLE (DIP) -> Depends on ITaskRepository abstraction
    public TaskManager(ITaskRepository repository) {
        this.repository = repository;
    }

    //========CREATE TASK========
    @Override
    public Task createTask(String title, String description){
        Task task = new Task(title, description);
        repository.save(task);
        return task;
    }

    //=======ASSIGN TASK TO USER========
    @Override
    public void assignTaskToUser(Task task, User user ){
        task.setAssignedUser(user);
        repository.update(task);
    }

    //=======CHANGE TASK STATUS========
    @Override
    public void changeStatus(Task task, TaskStatus status){
        task.setStatus(status);
        repository.update(task);
    }

    //======UPDATE TASK========
    public void updateTask(Task task, String newTitle, String newDescription){
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        repository.update(task);
    }

    //=======DELETE TASK========
    public void deleteTask(Task task){
        repository.delete(task);
    }

    //=======LIST ALL TASKS========
    public List<Task> getAllTasks(){
       return repository.getAll();
    }

    //=======SHOW ALL TASKS========
    public void showAllTasks(){
        if(repository.getAll().isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        List<Task> tasks = repository.getAll();
        for(Task task : tasks) {
            System.out.println(task);
        }
    }


}
