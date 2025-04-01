package is.hi.tms;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;
import is.hi.tms.ui.TextUI;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        TaskService taskService = new TaskService();
        ProjectService projectService = new ProjectService();

        // Initialize and start the UI
        TextUI textUI = new TextUI(taskService, projectService);
        textUI.start();
    }
}