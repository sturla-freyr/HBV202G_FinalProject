-system/src/main/java/is/hi/tms/domain/Task.java
package is.hi.tms.domain;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private LocalDate deadline;

    public Task(String title, String description, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}