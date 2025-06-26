package UI;

import INTERFACES.ITaskRepository;
import MANAGER.TaskManager;
import REPOSITORY.InMemoryTaskRepository;
import MODEL.*;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class ConsoleUI {
    private final TaskManager taskManager;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(){
        ITaskRepository repository = new InMemoryTaskRepository();
        this.taskManager = new TaskManager(new InMemoryTaskRepository());
    }

    public void start(){
        while(true){
            System.out.println("\n============MENU PRINCIPAL===============");
            System.out.println("1 -> Crear tarea");
            System.out.println("2 -> Ver tareas");
            System.out.println("3 -> Asignar tarea");
            System.out.println("4 -> Cambiar estado");
            System.out.println("5 -> Editar tarea");
            System.out.println("6 -> Eliminar tarea");
            System.out.println("7 -> Salir");
            System.out.print("Opcion: ");

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
                    System.out.println("Adios, muchas gracias.");
                    return;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }

    private void createTask(){
        System.out.print("Titulo: ");
        String title = scanner.nextLine();
        System.out.print("Descripcion: ");
        String description = scanner.nextLine();
        taskManager.createTask(title, description);
    }

    private void assignTask(){
        System.out.print("Titulo de tarea a asignar: ");
        String title = scanner.nextLine();
        Task task = findTaskByTitle(title);
        if(task != null){
            System.out.print("Nombre del usuario: ");
            String userName = scanner.nextLine();
            taskManager.assignTaskToUser(task, new User(userName));
        }else{
            System.out.println("Tarea no encontrada");
        }

    }

    private void changeStatus(){
        System.out.print("Titulo de la tarea: ");
        String title = scanner.nextLine();
        Task task = findTaskByTitle(title);
        try{
            if(task != null){
                System.out.println("1. Pendiente \n2. En progreso \n3. Completada");
                System.out.print("Nuevo estado: ");
                int choice = Integer.parseInt(scanner.nextLine());
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
                        System.out.println("Opcion invalida");
                        return;
                }

                taskManager.changeStatus(task, newStatus);
                System.out.println("Estado actualizado correctamente");
            }
        }catch(NumberFormatException e){
            System.out.println("Entrada invalida");
        }

    }

    private Task findTaskByTitle(String title){
        return taskManager
                .getAllTasks()
                .stream()
                .filter(task ->task.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    private void editTask(){
        System.out.print("Titulo de la tarea: ");
        String title = scanner.nextLine();
        Task task = findTaskByTitle(title);
        if(task != null){
            System.out.print("Nuevo titulo: ");
            String newTitle = scanner.nextLine();
            System.out.print("Nueva descripcion: ");
            String newDescription = scanner.nextLine();
            taskManager.updateTask(task, newTitle, newDescription);
        }else{
            System.out.println("Tarea no encontrada");
        }
    }

    private void deleteTask(){
        System.out.print("Titulo de la tarea a eliminar: ");
        String title = scanner.nextLine();
        Task task = findTaskByTitle(title);
        if(task != null){
            taskManager.deleteTask(task);
            System.out.println("Tarea eliminada");
        } else{
            System.out.println("Tarea no encontrada");
        }
    }



}
