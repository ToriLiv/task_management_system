package MODEL;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private User assignedUser;
    private List<Task> tasks = new ArrayList<>();


    //----------constructor-----------
    public Project(String name) {
        this.name = name;
    }

    //--------------method to add tasks------------
    public void addTask(Task task){
        tasks.add(task);
    }
    //------------method to delete tasks-----------------
    public void removeTask(Task task){
        tasks.remove(task);
    }

    //-------------method to show project and its tasks--------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("================================================\n");
        sb.append("Project Name: ").append(name).append("\n");
        sb.append("Assigned User: ").append(assignedUser != null ? assignedUser.getName() : "No user assigned").append("\n");
        sb.append("Tasks:\n");
        for (Task task : tasks) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

    //-------------methods get y set-------------------
    public String getName() {
        return name;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}

