import UI.ConsoleUI;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//=============SINGLE RESPONSIBILITY PRINCIPLE================
//initialization of the application and execution of the main loop
public class Main {
    public static void main(String[] args) {
        ConsoleUI UI = new ConsoleUI();
        UI.start();
    }
}