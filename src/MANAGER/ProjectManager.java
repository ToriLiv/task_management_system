package MANAGER;
import INTERFACES.*;
import MODEL.Project;
import MODEL.Task;
import MODEL.User;
import java.util.List;

/*
 * =========LISKOV SUBSTITUTION PRINCIPLE (LSP)============
 * depends of IProjectRepository abstraction, so any repository can be used
 * */

/*
 * ===============OPEN/CLOSED PRINCIPLE (OCP)================
 * uses repository interface, to extend with the new repositories without changing taskmanager
 * */

public class ProjectManager implements IProjectCreator, IProjectAssigner {
    private final IProjectRepository projectRepository;
    //DEPENDENCY INVERSION PRINCIPLE (DIP) -> Depends on IProjectRepository abstraction

    public ProjectManager(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    //========CREATE PROJECT========
    @Override
    public Project createProject(String name) {
        Project project = new Project(name);
        projectRepository.save(project);
        return project;
    }

    //=======DELETE PROJECT========
    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    //=======ASSIGN TASK TO PROJECT========
    public void assignTaskToProject(Task task, Project project) {
         project.addTask(task);
         projectRepository.update(project);
    }

    //=======LIST ALL PROJECTS========
    public List<Project> getAllProjects() {
        return projectRepository.getAll();
    }


    //=======SHOW ALL PROJECTS========
    public void showAllProjects(){
        if(projectRepository.getAll().isEmpty()) {
            System.out.println("No projects available.");
            return;
        }
        List<Project> projects = projectRepository.getAll();
        for(Project project : projects) {
            System.out.println(project);
        }
    }

    //=======ASSIGN USER TO PROJECT========
    @Override
    public void assignUserToProject(User user, Project project) {
        project.setAssignedUser(user);
        projectRepository.update(project);
    }
}
