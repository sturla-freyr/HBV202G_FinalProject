package is.hi.tms.ui;

import is.hi.tms.service.ProjectService;
import is.hi.tms.service.TaskService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {

    @Test
    public void testTaskServiceNotifications() {
        // Create TaskService and a mock observer
        TaskService taskService = new TaskService();
        MockObserver mockObserver = new MockObserver();
        taskService.addObserver(mockObserver);

        // Perform an action on TaskService
        taskService.createTask("Test Task", "Description", null, null, null);

        // Verify the observer received the notification
        assertEquals("Task created: Test Task", mockObserver.getLastMessage());
    }

    @Test
    public void testProjectServiceNotifications() {
        // Create ProjectService and a mock observer
        ProjectService projectService = new ProjectService();
        MockObserver mockObserver = new MockObserver();
        projectService.addObserver(mockObserver);

        // Perform an action on ProjectService
        projectService.createProject("Test Project");

        // Verify the observer received the notification
        assertEquals("Project created: Test Project", mockObserver.getLastMessage());
    }

    // Mock observer class for testing
    private static class MockObserver implements Observer {
        private String lastMessage;

        @Override
        public void update(String message) {
            this.lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}