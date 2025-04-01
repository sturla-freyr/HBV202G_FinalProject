package is.hi.tms.ui;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;
import is.hi.tms.domain.Project;
import is.hi.tms.domain.Task;
import is.hi.tms.domain.WorkItem;
import is.hi.tms.domain.TaskStatus;

import java.util.Scanner;
import java.util.List;

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
        while (true) {
            clearScreen(); // Clear the terminal
            displayHeader(); // Display the ASCII art header
            displayProjects(); // Display the list of projects with progress

            System.out.println("1. Create Task");
            System.out.println("2. Create Project");
            System.out.println("3. List Tasks");
            System.out.println("4. List Projects");
            System.out.println("5. Exit");
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
                    clearScreen();
                    displayHeader();
                    System.out.println("Tasks:");
                    taskService.listTasks().forEach(task -> System.out.println("- " + task.getTitle()));
                    System.out.println("\nPress Enter to return to the menu...");
                    scanner.nextLine();
                }
                case 4 -> {
                    clearScreen();
                    displayHeader();
                    System.out.println("Projects:");
                    projectService.listProjects().forEach(project -> System.out.println("- " + project.getTitle()));
                    System.out.println("\nPress Enter to return to the menu...");
                    scanner.nextLine();
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    private void displayHeader() {
        System.out.println("  _____           _           _     __  __                                   ");
        System.out.println(" |  __ \\         (_)         | |   |  \\/  |                                  ");
        System.out.println(" | |__) | __ ___  _  ___  ___| |_  | \\  / | __ _ _ __   __ _  __ _  ___ _ __ ");
        System.out.println(" |  ___/ '__/ _ \\| |/ _ \\/ __| __| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|");
        System.out.println(" | |   | | | (_) | |  __/ (__| |_  | |  | | (_| | | | | (_| | (_| |  __/ |   ");
        System.out.println(" |_|   |_|  \\___/| |\\___|\\___|\\__| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   ");
        System.out.println("                _/ |                                          __/ |          ");
        System.out.println("  _            |______ _              _         ______       |___/           ");
        System.out.println(" | |            / ____| |            | |       |  ____|                      ");
        System.out.println(" | |__  _   _  | (___ | |_ _   _ _ __| | __ _  | |__ _ __ ___ _   _ _ __     ");
        System.out.println(" | '_ \\| | | |  \\___ \\| __| | | | '__| |/ _` | |  __| '__/ _ \\ | | | '__|    ");
        System.out.println(" | |_) | |_| |  ____) | |_| |_| | |  | | (_| | | |  | | |  __/ |_| | |       ");
        System.out.println(" |_.__/ \\__, | |_____/ \\__|\\__,_|_|  |_|\\__,_| |_|  |_|  \\___|\\__, |_|       ");
        System.out.println("         __/ |                                                 __/ |         ");
        System.out.println("        |___/                                                 |___/          ");
    }

    private void displayProjects() {
        List<Project> projects = projectService.listProjects();
        StringBuilder projectDisplay = new StringBuilder();

        if (projects.isEmpty()) {
            projectDisplay.append("No projects available.\n");
        } else {
            int count = 0;
            for (Project project : projects) {
                int progress = calculateProjectProgress(project);
                projectDisplay.append(String.format("%-20s %d%%", project.getTitle(), progress));
                count++;

                // Add spacing or a newline based on the count
                if (count % 3 == 0) {
                    projectDisplay.append("\n"); // Add a newline after every 3 projects
                } else {
                    projectDisplay.append("   "); // Add spacing between projects
                }
            }

            // If the last row has fewer than 3 projects, add a newline
            if (projects.size() % 3 != 0) {
                projectDisplay.append("\n");
            }
        }

        // Calculate the maximum width of the text
        int maxWidth = projectDisplay.toString().lines()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Generate the dynamic bar
        String bar = "=".repeat(maxWidth);

        // Print the bar, project list, and bar again
        System.out.println(bar);
        System.out.print(projectDisplay);
        System.out.println(bar);
    }

    private int calculateProjectProgress(Project project) {
        List<WorkItem> workItems = project.getWorkItems();
        if (workItems.isEmpty()) {
            return 0; // No tasks, so progress is 0%
        }

        int completedTasks = 0;
        int totalTasks = 0;

        for (WorkItem workItem : workItems) {
            if (workItem instanceof Task task) {
                totalTasks++;
                if (task.getStatus() == TaskStatus.COMPLETED) {
                    completedTasks++;
                }
            }
        }

        return (int) ((double) completedTasks / totalTasks * 100);
    }

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}