package INTERFACES;

import MODEL.Project;
import MODEL.Task;
import MODEL.User;

//----------INTERFACE SEGREGATION PRINCIPLE AND SINGLE RESPONSIBILITY PRINCIPLE----------
public interface IProjectAssigner {
    void assignUserToProject(User user, Project project);
}
