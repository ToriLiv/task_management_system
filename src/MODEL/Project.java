package MODEL;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Task> tasks = new ArrayList<>();

    //constructor con el atributo nombre
    public Project(String name) {
        this.name = name;
    }

    //metodo para agregar tareas
    public void addTask(Task task){
        tasks.add(task);
    }

    //metodos get

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
