package is.hi.tms.service;

import is.hi.tms.domain.Task;
import is.hi.tms.domain.TaskPriority;
import is.hi.tms.domain.TaskStatus;
import is.hi.tms.ui.Observer;
import is.hi.tms.ui.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class that manages tasks and notifies observers of changes.
 */
public class TaskService implements Subject {
    private List<Task> tasks;
    private List<Observer> observers;

    /**
     * Constructs an empty TaskService.
     */
    public TaskService() {
        this.tasks = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Creates a new task and adds it to the task list.
     *
     * @param title       the title of the task
     * @param description the description of the task
     * @param deadline    the deadline of the task
     * @param status      the status of the task
     * @param priority    the priority of the task
     * @return the created Task
     */
    public Task createTask(String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        Task task = new Task(title, description, deadline, status, priority);
        tasks.add(task);
        notifyObservers("Task created: " + title);
        return task;
    }

    /**
     * Updates the details of an existing task.
     *
     * @param task        the task to update
     * @param title       new title
     * @param description new description
     * @param deadline    new deadline
     * @param status      new status
     * @param priority    new priority
     */
    public void updateTask(Task task, String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setStatus(status);
        task.setPriority(priority);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param task the task to delete
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
        notifyObservers("Task deleted: " + task.getTitle());
    }

    /**
     * Returns the list of all tasks.
     *
     * @return list of tasks
     */
    public List<Task> listTasks() {
        return tasks;
    }

    /**
     * Returns tasks sorted by deadline.
     *
     * @return sorted list of tasks
     */
    public List<Task> sortTasksByDeadline() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());
    }

    /**
     * Returns tasks sorted by priority.
     *
     * @return sorted list of tasks
     */
    public List<Task> sortTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }

    /**
     * Returns tasks sorted by status.
     *
     * @return sorted list of tasks
     */
    public List<Task> sortTasksByStatus() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

    /**
     * Returns tasks filtered by the given status.
     *
     * @param status the status to filter by
     * @return filtered list of tasks
     */
    public List<Task> filterTasksByStatus(TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
