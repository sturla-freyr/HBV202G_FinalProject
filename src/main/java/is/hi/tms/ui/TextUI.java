package is.hi.tms.ui;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;
import is.hi.tms.domain.Project;
import is.hi.tms.domain.Task;
import is.hi.tms.domain.WorkItem;
import is.hi.tms.domain.TaskStatus;
import is.hi.tms.domain.TaskPriority; // For task priority (LOW, MEDIUM, HIGH)
import java.time.LocalDate;          // For handling dates

import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

/**
 * Text-based user interface for interacting with the Task Management System.
 * Implements Observer to receive notifications from TaskService and ProjectService.
 */
public class TextUI implements Observer {
    private TaskService taskService;
    private ProjectService projectService;

    /**
     * Constructs a TextUI with the given task and project services.
     *
     * @param taskService the task service to use
     * @param projectService the project service to use
     */
    public TextUI(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
        this.taskService.addObserver(this);
        this.projectService.addObserver(this);
    }

    /**
     * Starts the main user interaction loop for the console UI.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                clearScreen(); // Clear the terminal
                displayHeader(); // Display the ASCII art header
                displayProjects(); // Display the list of projects with progress

                System.out.println("1. Create Task");
                System.out.println("2. Create Project");
                System.out.println("3. List Tasks");
                System.out.println("4. Update Project");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> createTask(scanner);
                    case 2 -> createProject(scanner);
                    case 3 -> listTasks(scanner);
                    case 4 -> updateProject(scanner);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code to clear the screen
        System.out.flush();
    }

    private void displayHeader() {
        System.out.println("  _____           _           _     __  __                                               ");
        System.out.println(" |  __ \\         (_)         | |   |  \\/  |                                              ");
        System.out.println(" | |__) | __ ___  _  ___  ___| |_  | \\  / | __ _ _ __   __ _  __ _  ___ _ __             ");
        System.out.println(" |  ___/ '__/ _ \\| |/ _ \\/ __| __| | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|            ");
        System.out.println(" | |   | | | (_) | |  __/ (__| |_  | |  | | (_| | | | | (_| | (_| |  __/ |               ");
        System.out.println(" |_|   |_|  \\___/| |\\___|\\___|\\__| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|               ");
        System.out.println("                _/ |                                          __/ |                      ");
        System.out.println("  _            |______ _              _              ____    |___/          _           ");
        System.out.println(" | |            / ____| |            | |       ___  |  _ \\                 (_)          ");
        System.out.println(" | |__  _   _  | (___ | |_ _   _ _ __| | __ _ ( _ ) | |_) |_ __ _   _ _ __  _  __ _ _ __ ");
        System.out.println(" | '_ \\| | | |  \\___ \\| __| | | | '__| |/ _` |/ _ \\/\\  _ <| '__| | | | '_ \\| |/ _` | '__|");
        System.out.println(" | |_) | |_| |  ____) | |_| |_| | |  | | (_| | (_>  < |_) | |  | |_| | | | | | (_| | |   ");
        System.out.println(" |_.__/ \\__, | |_____/ \\__|\\__,_|_|  |_|\\__,_|\\___/\\/____/|_|   \\__, |_| |_| |\\__,_|_|   ");
        System.out.println("         __/ |                                                   __/ |    _/ |           ");
        System.out.println("        |___/                                                   |___/    |__/            ");
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

    private void createTask(Scanner scanner) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        taskService.createTask(title, description, null, null, null);
    }

    private void createProject(Scanner scanner) {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine();
        projectService.createProject(name);
    }

    private void listTasks(Scanner scanner) {
        clearScreen();
        displayHeader();
        System.out.println("Tasks without a project:");
        List<Task> tasks = taskService.listTasks().stream()
                .filter(task -> projectService.listProjects().stream()
                        .noneMatch(project -> project.getWorkItems().contains(task)))
                .toList();

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d. Title: %s | Status: %s | Priority: %s | Deadline: %s%n",
                        i + 1, task.getTitle(), task.getStatus(), task.getPriority(), task.getDeadline());
            }
        }

        System.out.println("\nOptions:");
        System.out.println("1. Update Task");
        System.out.println("2. Delete Task");
        System.out.println("3. Assign Task to Project");
        System.out.println("4. Return to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> updateTask(scanner);
            case 2 -> deleteTask(scanner);
            case 3 -> assignTaskToProject(scanner);
            case 4 -> {
                return; // Return to main menu
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    private void updateTask(Scanner scanner) {
        System.out.print("Enter task title to update: ");
        String title = scanner.nextLine();
        Task task = taskService.listTasks().stream()
                .filter(t -> t.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.println("1. Update Status");
        System.out.println("2. Update Priority");
        System.out.println("3. Update Deadline");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                System.out.print("Enter new status (NOT_STARTED, IN_PROGRESS, COMPLETED): ");
                String status = scanner.nextLine();
                task.setStatus(TaskStatus.valueOf(status.toUpperCase()));
            }
            case 2 -> {
                System.out.print("Enter new priority (LOW, MEDIUM, HIGH): ");
                String priority = scanner.nextLine();
                task.setPriority(TaskPriority.valueOf(priority.toUpperCase()));
            }
            case 3 -> {
                System.out.print("Enter new deadline (YYYY-MM-DD): ");
                String deadline = scanner.nextLine();
                task.setDeadline(LocalDate.parse(deadline));
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Enter task title to delete: ");
        String title = scanner.nextLine();
        Task task = taskService.listTasks().stream()
                .filter(t -> t.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

        if (task == null) {
            System.out.println("Task not found.");
        } else {
            taskService.deleteTask(task);
            System.out.println("Task deleted.");
        }
    }

    private void assignTaskToProject(Scanner scanner) {
        System.out.print("Enter task title to assign: ");
        String taskTitle = scanner.nextLine();
        Task task = taskService.listTasks().stream()
                .filter(t -> t.getTitle().equalsIgnoreCase(taskTitle))
                .findFirst()
                .orElse(null);

        if (task == null) {
            System.out.println("Task not found.");
            return;
        }

        System.out.print("Enter project name to assign the task to: ");
        String projectName = scanner.nextLine();
        Project project = projectService.listProjects().stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(projectName))
                .findFirst()
                .orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
        } else {
            projectService.addWorkItemToProject(project, task);
            System.out.println("Task assigned to project.");
        }
    }

    private void deleteProject(Scanner scanner) {
        System.out.print("Enter project name to delete: ");
        String name = scanner.nextLine();
        Project project = projectService.listProjects().stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
        } else {
            projectService.listProjects().remove(project);
            System.out.println("Project deleted.");
        }
    }

    private void updateProject(Scanner scanner) {
        System.out.print("Enter project name to update: ");
        String name = scanner.nextLine();
        Project project = projectService.listProjects().stream()
                .filter(p -> p.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        clearScreen();
        displayHeader();
        System.out.printf("Project: %s%n", project.getTitle());
        System.out.println("Tasks:");
        List<WorkItem> workItems = project.getWorkItems();
        if (workItems.isEmpty()) {
            System.out.println("No tasks assigned to this project.");
        } else {
            for (WorkItem workItem : workItems) {
                if (workItem instanceof Task task) {
                    System.out.printf("Title: %s | Status: %s | Priority: %s | Deadline: %s%n",
                            task.getTitle(), task.getStatus(), task.getPriority(), task.getDeadline());
                }
            }
        }

        System.out.println("\nOptions:");
        System.out.println("1. Delete Project");
        System.out.println("2. Return to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1 -> {
                projectService.listProjects().remove(project);
                System.out.println("Project deleted.");
            }
            case 2 -> {
                return; // Return to main menu
            }
            default -> System.out.println("Invalid choice. Try again.");
        }
    }

    /**
     * Receives and displays a notification from the subject.
     *
     * @param message the message received
     */
    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}