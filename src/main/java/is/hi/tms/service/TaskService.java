package is.hi.tms.service;

import is.hi.tms.domain.Task;
import is.hi.tms.domain.TaskPriority;
import is.hi.tms.domain.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks;

    public TaskService() {
        this.tasks = new ArrayList<>();
    }

    public Task createTask(String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        Task task = new Task(title, description, deadline, status, priority);
        tasks.add(task);
        return task;
    }

    public void updateTask(Task task, String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setStatus(status);
        task.setPriority(priority);
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> listTasks() {
        return tasks;
    }
}