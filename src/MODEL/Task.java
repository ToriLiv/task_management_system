package MODEL;

public class Task {
    private String title; //titulo de la tarea
    private String description; //descripcion de la tarea
    private TaskStatus status; //estado de la tarea
    private User assignedUser; //usuario asignado


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

    //metodo string
    @Override
    public String toString(){
        return "Tarea: " + title + "" + "\nEstado: " + status + "\nDescripcion: " + description +  "\nAsignado a: " + (assignedUser != null? assignedUser.getName() : "Nadie");
    }
}
