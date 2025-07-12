package MODEL;

public class Task {
    private String title; //title of the task
    private String description; //description of the task
    private TaskStatus status; //status of the task (PENDING, IN PROGRESS, COMPLETED)
    private User assignedUser; //user assigned to the task

    /*========SINGLE RESPONSABILITY PRINCIPLE (SRP)============
     * this class correctly focuses on task data and relates getters/setters only
     * */

    //CONSTRUCTOR
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    //GETTERS Y SETTERS
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    //method string
    @Override
    public String toString(){
        return "--------------------------------------------------------------------" + "\nTask: " + title + "" + "\nStatus: " + status + "\nDescription: " + description +  "\nAssigned to: " + (assignedUser != null? assignedUser.getName() : "No user assigned" + "\n--------------------------------------------------------------------");
    }
}
