package INTERFACES;
import MODEL.Project;
import java.util.List;

//----------INTERFACE SEGREGATION PRINCIPLE---------------
/*
 * ==========OPEN/CLOSED PRINCIPLE (OCP)===========
 * interface allows adding new repository implementation without modifying existing code
 * */
public interface IProjectRepository {
    void save(Project project);
    void delete(Project project);
    List<Project> getAll();
    void update(Project project);
}
