package REPOSITORY;

import INTERFACES.IProjectRepository;
import MODEL.Project;


import java.util.ArrayList;
import java.util.List;

/*
 * SINGLE RESPONSABILITY PRINCIPLE (SRP)
 * This class handles only data storage in memory
 * */

/*
 * LISKOW SUBSTITUTION PRINCIPLE (LSP)
 * Implements IProjectRepository and can be substituted wherever the interface is used
 * */


public class InMemoryProjectRepository implements IProjectRepository {
    private final List<Project> projects = new ArrayList<>();
    private Project project = new Project ("project");

    //======save method========
    @Override
    public void save(Project project){
        projects.add(project);
    }

    //======update method========
    @Override
    public void update(Project project){
    }

    //======delete method========
    @Override
    public void delete(Project project){
        projects.remove(project);
    }

    //======getAll method========
    @Override
    public List<Project> getAll(){
        return projects;
    }
}
