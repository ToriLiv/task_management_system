package REPOSITORY;

import INTERFACES.ITaskRepository;
import MODEL.Project;
import MODEL.Task;

import java.util.ArrayList;
import java.util.List;

/*
* ========SINGLE RESPONSABILITY PRINCIPLE (SRP)=============
* This class handles only data storage in memory
* */

/*
* ===========LISKOW SUBSTITUTION PRINCIPLE (LSP)==========
* Implements ITaskRepository and can be substituted wherever the interface is used
* */
public class InMemoryTaskRepository implements ITaskRepository {
    private final List<Task> tasks = new ArrayList<>();
    private Project project = new Project ("project");

    //======save method========
    @Override
    public void save(Task task){
        tasks.add(task);
    }

    //======update method========
    @Override
    public void update(Task task){
    }

    //======delete method========
    @Override
    public void delete(Task task){
        tasks.remove(task);
    }

    //======getAll method========
    @Override
    public List<Task> getAll(){
        return tasks;
    }
}

