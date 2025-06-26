package REPOSITORY;

import INTERFACES.ITaskRepository;
import MODEL.Project;
import MODEL.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements ITaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private Project project = new Project ("project");

    @Override
    public void save(Task task){
        tasks.add(task);
    }

    @Override
    public void update(Task task){
        //no es necesario agregar algo para editar
    }

    @Override
    public void delete(Task task){
        tasks.remove(task);
    }

    @Override
    public List<Task> getAll(){
        return tasks;
    }
}

