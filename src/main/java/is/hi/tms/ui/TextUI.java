package is.hi.tms.ui;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;

import java.util.Scanner;

public class TextUI implements Observer {
    private TaskService taskService;
    private ProjectService projectService;

    public TextUI(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.taskService.addObserver(this);
        this.projectService.addObserver(this);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Task Management System!");
        while (true) {
            System.out.println("1. Create Task");
            System.out.println("2. Create Project");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskService.createTask(title, description, null, null, null);
                }
                case 2 -> {
                    System.out.print("Enter project name: ");
                    String name = scanner.nextLine();
                    projectService.createProject(name);
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}