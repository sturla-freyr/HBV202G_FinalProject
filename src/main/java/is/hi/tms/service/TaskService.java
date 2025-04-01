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

public class TaskService implements Subject {
    private List<Task> tasks;
    private List<Observer> observers;

    public TaskService() {
        this.tasks = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Task createTask(String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        Task task = new Task(title, description, deadline, status, priority);
        tasks.add(task);
        notifyObservers("Task created: " + title);
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
        notifyObservers("Task deleted: " + task.getTitle());
    }

    public List<Task> listTasks() {
        return tasks;
    }

    public List<Task> sortTasksByDeadline() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDeadline))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getPriority))
                .collect(Collectors.toList());
    }

    public List<Task> sortTasksByStatus() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

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