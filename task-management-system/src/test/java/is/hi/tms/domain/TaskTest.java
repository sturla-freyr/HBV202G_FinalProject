package is.hi.tms.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskCreation() {
        Task task = new Task("Test Task", "This is a test task", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        assertEquals("Test Task", task.getTitle());
        assertEquals("This is a test task", task.getDescription());
        assertEquals(LocalDate.of(2025, 4, 1), task.getDeadline());
        assertEquals(TaskStatus.NOT_STARTED, task.getStatus());
        assertEquals(TaskPriority.MEDIUM, task.getPriority());
    }

    @Test
    public void testTaskStatusUpdate() {
        Task task = new Task("Test Task", "This is a test task", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        task.setStatus(TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    public void testTaskPriorityUpdate() {
        Task task = new Task("Test Task", "This is a test task", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        task.setPriority(TaskPriority.HIGH);
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }
}