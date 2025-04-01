package is.hi.tms.service;

import is.hi.tms.domain.Task;
import is.hi.tms.domain.TaskPriority;
import is.hi.tms.domain.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskServiceTest {

    @Test
    public void testCreateTask() {
        TaskService taskService = new TaskService();
        Task task = taskService.createTask("Test Task", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        List<Task> tasks = taskService.listTasks();
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }

    @Test
    public void testUpdateTask() {
        TaskService taskService = new TaskService();
        Task task = taskService.createTask("Test Task", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        taskService.updateTask(task, "Updated Task", "Updated Description", LocalDate.of(2025, 5, 1), TaskStatus.IN_PROGRESS, TaskPriority.HIGH);
        assertEquals("Updated Task", task.getTitle());
        assertEquals("Updated Description", task.getDescription());
        assertEquals(LocalDate.of(2025, 5, 1), task.getDeadline());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(TaskPriority.HIGH, task.getPriority());
    }

    @Test
    public void testDeleteTask() {
        TaskService taskService = new TaskService();
        Task task = taskService.createTask("Test Task", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        taskService.deleteTask(task);
        assertTrue(taskService.listTasks().isEmpty());
    }

    @Test
    public void testSortTasksByDeadline() {
        TaskService taskService = new TaskService();
        Task task1 = taskService.createTask("Task 1", "Description 1", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        Task task2 = taskService.createTask("Task 2", "Description 2", LocalDate.of(2025, 3, 1), TaskStatus.IN_PROGRESS, TaskPriority.HIGH);
        List<Task> sortedTasks = taskService.sortTasksByDeadline();
        assertEquals(task2, sortedTasks.get(0));
        assertEquals(task1, sortedTasks.get(1));
    }

    @Test
    public void testFilterTasksByStatus() {
        TaskService taskService = new TaskService();
        Task task1 = taskService.createTask("Task 1", "Description 1", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        Task task2 = taskService.createTask("Task 2", "Description 2", LocalDate.of(2025, 3, 1), TaskStatus.IN_PROGRESS, TaskPriority.HIGH);
        List<Task> filteredTasks = taskService.filterTasksByStatus(TaskStatus.NOT_STARTED);
        assertEquals(1, filteredTasks.size());
        assertEquals(task1, filteredTasks.get(0));
    }
}