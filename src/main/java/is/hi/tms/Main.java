package is.hi.tms;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;
import is.hi.tms.ui.TextUI;

/**
 * Entry point of the Task Management System application.
 */
public class Main {

    /**
     * The main method that launches the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize services
        TaskService taskService = new TaskService();
        ProjectService projectService = new ProjectService();

        // Initialize and start the UI
        TextUI textUI = new TextUI(taskService, projectService);
        textUI.start();
    }
}
