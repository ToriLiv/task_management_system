package UI;

import INTERFACES.IProjectRepository;
import INTERFACES.ITaskRepository;
import INTERFACES.IUserRepository;
import MANAGER.ProjectManager;
import MANAGER.TaskManager;
import MANAGER.UserManager;
import REPOSITORY.InMemoryProjectRepository;
import REPOSITORY.InMemoryTaskRepository;
import MODEL.*;
import REPOSITORY.InMemoryUserRepository;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/*
* SINGLE RESPONSABILITY PRINCIPLE (SRP)
* Delegates business logic and handles interaction with the user
* can extend with new repositories without changing taskmanager
* */

public class ConsoleUI {
    private final TaskManager taskManager;
    private final ProjectManager projectManager;
    private final UserManager userManager;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(){
        ITaskRepository repository = new InMemoryTaskRepository();
        IProjectRepository repository2 = new InMemoryProjectRepository();
        IUserRepository repository3 = new InMemoryUserRepository();
        this.taskManager = new TaskManager(new InMemoryTaskRepository());
        this.userManager = new UserManager(new InMemoryUserRepository());
        this.projectManager = new ProjectManager(new InMemoryProjectRepository());
    }

    //-------------------------------------MAIN METHOD-------------------------------------
    public void start(){
        while(true){
            //main menu
            System.out.println("┌──────────────────────────────────────┐");
            System.out.println("│              MAIN MENU               │");
            System.out.println("├──────────────────────────────────────┤");
            System.out.println("│ 1 ->  Create task                    │");
            System.out.println("│ 2 ->  See tasks                      │");
            System.out.println("│ 3 ->  Assign user to a task          │");
            System.out.println("│ 4 ->  Change status of a task        │");
            System.out.println("│ 5 ->  Edit task                      │");
            System.out.println("│ 6 ->  Delete task                    │");
            System.out.println("│ 7 ->  Create a project               │");
            System.out.println("│ 8 ->  Delete a project               │");
            System.out.println("│ 9 ->  See projects                   │");
            System.out.println("│ 10 -> Assign task to a project       │");
            System.out.println("│ 11 -> Delete task from a project     │");
            System.out.println("│ 12 -> Assign user to a project       │");
            System.out.println("│ 13 -> Create user                    │");
            System.out.println("│ 14 -> Delete user                    │");
            System.out.println("│ 15 -> See users                      │");
            System.out.println("│ 0 ->  Log Out                        │");
            System.out.println("└──────────────────────────────────────┘");
            System.out.print("Option: ");

            String option = scanner.nextLine();
            switch(option){
                case "1":
                    createTask();
                    break;
                case "2":
                    taskManager.showAllTasks();
                    break;
                case "3":
                    assignTask();
                    break;
                case "4":
                    changeStatus();
                    break;
                case "5":
                    editTask();
                    break;
                case "6":
                    deleteTask();
                    break;
                case "7":
                    createProject();
                    break;
                case "8":
                    deleteProject();
                    break;
                case "9":
                    projectManager.showAllProjects();
                    break;
                case "10":
                    System.out.print("Name of the project: ");
                    String projectName = scanner.nextLine();
                    Project project = findProjectByTitle(projectName);
                    if (project != null) {
                        assignTasksToProject(project, scanner);
                    } else {
                        System.out.println("Project not found");
                    }
                    break;
                case "11":
                    System.out.print("Enter project's name: ");
                    String nameProject = scanner.nextLine();
                    Project projects = findProjectByTitle(nameProject);
                    if (projects != null) {
                        removeTaskFromProject(projects, scanner);
                    } else {
                        System.out.println("Project not found");
                    }
                    break;
                case "12":
                    assignUser();
                    break;
                case "13":
                    createUser();
                    break;
                case "14":
                deleteUser();
                break;
                case "15":
                    userManager.showAllUsers();
                    break;
                case "0":
                    System.out.println("Goodbye, thank you.");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    //-------------------------------------METHODS FOR TASK MANAGEMENT-------------------------------------

    //=======CREATE TASK========
    private void createTask(){
        System.out.print("Title of the task: ");
        String title = scanner.nextLine();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        System.out.print("Description of the task: ");
        String description = scanner.nextLine();

        if (description.isEmpty()) {
            System.out.println("Description cannot be empty.");
            return;
        }
        taskManager.createTask(title, description);
        System.out.println("Task created successfully.");
    }

    //========ASSIGN TASK TO USER========
    private void assignTask(){
        System.out.print("Title of the task you want to assign a user: ");
        String title = scanner.nextLine();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Task task = findTaskByTitle(title);
        if(task != null){
            System.out.print("User name: ");
            String userName = scanner.nextLine();

        if (userName.isEmpty()) {
            System.out.println("User name cannot be empty.");
            return;
        }
            User user = findUserByName(userName);
            if (user == null) {
                System.out.println("User not found, creating new user.");
                user = userManager.createUser(userName);
        }
            taskManager.assignTaskToUser(task, new User(userName));
            System.out.println("Task assigned to user: " + user.getName());
        }else{
            System.out.println("Task not found");
        }

    }

    //========CHANGE STATUS OF TASK========
    private void changeStatus(){
        System.out.print("Title of the task: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Task task = findTaskByTitle(title);
        try{
            if(task != null){
                System.out.println("1. Pending \n2. In progress \n3. Completed");
                System.out.print("New Status: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid option, please choose between 1 and 3.");
                    return;
                }
                TaskStatus newStatus;
                switch(choice){
                    case 1:
                        newStatus = TaskStatus.PENDING;
                        break;
                    case 2:
                        newStatus = TaskStatus.PROGRESS;
                        break;
                    case 3:
                        newStatus = TaskStatus.COMPLETED;
                        break;
                    default:
                        System.out.println("Invalid option");
                        return;
                }
                //Change the status of the task
                if (task.getStatus() == newStatus) {
                    System.out.println("The task is already in this status.");
                    return;
                }
                taskManager.changeStatus(task, newStatus);
                System.out.println("Status changed to: " + newStatus);
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid input, please enter a number.");
        }

    }

    //========FIND TASK BY TITLE========
    private Task findTaskByTitle(String title){
        return taskManager
                .getAllTasks()
                .stream()
                .filter(task ->task.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    //========EDIT TASK========
    private void editTask(){
        System.out.print("Title of the task you want to edit: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Task task = findTaskByTitle(title);
        if(task != null){
            System.out.print("New title: ");
            String newTitle = scanner.nextLine();
            if (newTitle.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            System.out.print("New description: ");
            String newDescription = scanner.nextLine();
            if (newDescription.isEmpty()) {
                System.out.println("Description cannot be empty.");
                return;
            }
            taskManager.updateTask(task, newTitle, newDescription);
            System.out.println("Task updated successfully.");
        }else{
            System.out.println("Title not found");
        }
    }

    //========DELETE TASK========
    private void deleteTask(){
        System.out.print("Title of the task you want to delete: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Task task = findTaskByTitle(title);
        if(task != null){
            taskManager.deleteTask(task);
            System.out.println("Task deleted");
        } else{
            System.out.println("Task not found");
        }
    }

    //-------------------------------------METHODS FOR PROJECT MANAGEMENT-------------------------------------
    //=======CREATE PROJECT========
    private void createProject(){
        System.out.print("Name of the project: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        if (projectManager.getAllProjects().stream().anyMatch(project -> project.getName().equalsIgnoreCase(name))) {
            System.out.println("Project with this name already exists.");
            return;
        }
        //Create the project
        System.out.println("Creating project: " + name);
        projectManager.createProject(name);
        System.out.println("Project created successfully.");
    }

    //========FIND PROJECT BY TITLE========
    private Project findProjectByTitle(String title){
        return projectManager
                .getAllProjects()
                .stream()
                .filter(project -> project.getName().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
    //========DELETE PROJECT========
    private void deleteProject(){
        System.out.print("Title of the project you want to delete: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Project project = findProjectByTitle(name);
        if(project != null){
            projectManager.deleteProject(project);
            System.out.println("Project deleted");
        } else{
            System.out.println("Project not found");
        }
    }

    //==========ASSIGN USER TO PROJECT========
    private void assignUser(){
        System.out.print("Name of the project you want to assign a user: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        Project project = findProjectByTitle(name);
        if(project != null){
            System.out.print("User name: ");
            String userName = scanner.nextLine();

            if (userName.isEmpty()) {
                System.out.println("User name cannot be empty.");
                return;
            }
            User user = findUserByName(userName);
            if (user == null) {
                System.out.println("User not found, creating new user.");
                user = userManager.createUser(userName);
            }
            projectManager.assignUserToProject(new User(userName), project);
            System.out.println("Project assigned to user: " + user.getName());
        }else{
            System.out.println("Project not found");
        }

    }
    //========ASSIGN TASKS TO PROJECT========
    public void assignTasksToProject(Project project, Scanner scanner) {
        System.out.print("¿How much tasks you want to add? ");
        int numTasks = Integer.parseInt(scanner.nextLine());
        if (numTasks <= 0) {
            System.out.println("Number of tasks must be greater than 0.");
            return;
        }
        System.out.println("Adding " + numTasks + " tasks to the project '" + project.getName() + "'.");
        //for para preguntar por cada tarea
        for (int i = 1; i <= numTasks; i++) {
            System.out.print("Title of the task #" + i + ": ");
            String titleTask = scanner.nextLine();
            if (titleTask.isEmpty()) {
                System.out.println("Title cannot be empty.");
                return;
            }
            System.out.print("Description of the task #" + i + ": ");
            String descriptionTask = scanner.nextLine();
            if (descriptionTask.isEmpty()) {
                System.out.println("Description cannot be empty.");
                return;
            }
            Task task = new Task(titleTask, descriptionTask);
            projectManager.assignTaskToProject(task, project);
            System.out.println("Task added: " + task.getTitle());
        }

        System.out.println("Added " + numTasks + " tasks to the project '" + project.getName() + "'.");
    }

    //========REMOVE TASK FROM PROJECT========
    public void removeTaskFromProject(Project project, Scanner scanner) {
        List<Task> tasks = project.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("This project doesn't have tasks.");
            return;
        }
        //Mostrar las tareas actuales del proyecto
        System.out.println("Tasks of the project '" + project.getName() + "':");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getTitle());
        }
        System.out.print("Enter the title of the task you want to delete: ");
        String title = scanner.nextLine();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Optional<Task> taskToRemove = tasks.stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .findFirst();
        if (taskToRemove.isPresent()) {
            project.removeTask(taskToRemove.get());
            System.out.println("Task deleted: " + taskToRemove.get().getTitle());
        } else {
            System.out.println("Invalid name.");
        }
    }

    //-------------------------------------METHODS FOR USER MANAGEMENT-------------------------------------
    //=======CREATE USER========
    private void createUser() {
        System.out.print("Name of the user: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        userManager.createUser(name);
        System.out.println("User created successfully.");
    }

    //========FIND USER BY NAME========
    private User findUserByName(String name) {
        return userManager
                .getAllUsers()
                .stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    //========DELETE USER========
    private void deleteUser() {
        System.out.print("Name of the user you want to delete: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        User user = findUserByName(name);
        if (user != null) {
            userManager.deleteUser(user);
            System.out.println("User deleted");
        } else {
            System.out.println("User not found");
        }
    }






}
