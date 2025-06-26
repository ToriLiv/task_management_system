package MANAGER;

//importar paquetes
import INTERFACES.*;
import MODEL.Task;
import MODEL.User;
import MODEL.TaskStatus;

import java.util.List;

//substitution liskov & interface segregation
public class TaskManager implements ITaskCreator, ITaskAssigner, ITaskStatusChanger{
    private final ITaskRepository repository; //dependencies

    public TaskManager(ITaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(String title, String description){
        Task task = new Task(title, description);
        repository.save(task);
        return task;
    }

    @Override
    public void assignTaskToUser(Task task, User user ){
        task.setAssignedUser(user);
        repository.update(task);
    }

    @Override
    public void changeStatus(Task task, TaskStatus status){
        task.setStatus(status);
        repository.update(task);
    }

    public void updateTask(Task task, String newTitle, String newDescription){
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        repository.update(task);
    }

    public void deleteTask(Task task){
        repository.delete(task);
    }

    public List<Task> getAllTasks(){
       return repository.getAll();
    }

    public void showAllTasks(){
        List<Task> tasks = repository.getAll();
        for(Task task : tasks) {
            System.out.println(task);
        }
    }


}
