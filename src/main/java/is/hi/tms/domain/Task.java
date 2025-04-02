package is.hi.tms.domain;

import java.time.LocalDate;

/**
 * Represents a task with title, description, deadline, status and priority.
 */
public class Task implements WorkItem {
    private String title;
    private String description;
    private LocalDate deadline;
    private TaskStatus status;
    private TaskPriority priority;

    /**
     * Constructs a new Task.
     *
     * @param title the task title
     * @param description the task description
     * @param deadline the task deadline
     * @param status the task status
     * @param priority the task priority
     */
    public Task(String title, String description, LocalDate deadline, TaskStatus status, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
    }

    /**
     * Returns the task title.
     *
     * @return task title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Sets the task title.
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the task description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task description.
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the task deadline.
     *
     * @return deadline
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Sets the task deadline.
     *
     * @param deadline new deadline
     */
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    /**
     * Returns the task status.
     *
     * @return task status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the task status.
     *
     * @param status new status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Returns the task priority.
     *
     * @return task priority
     */
    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Sets the task priority.
     *
     * @param priority new priority
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
