package is.hi.tms.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project containing a collection of work items (e.g. tasks or sub-projects).
 */
public class Project implements WorkItem {
    private String name;
    private List<WorkItem> workItems;

    /**
     * Constructs a Project with a given name.
     *
     * @param name the name of the project
     */
    public Project(String name) {
        this.name = name;
        this.workItems = new ArrayList<>();
    }

    /**
     * Returns the title (name) of the project.
     *
     * @return project name
     */
    @Override
    public String getTitle() {
        return name;
    }

    /**
     * Adds a work item to this project.
     *
     * @param workItem the work item to add
     */
    public void addWorkItem(WorkItem workItem) {
        workItems.add(workItem);
    }

    /**
     * Removes a work item from this project.
     *
     * @param workItem the work item to remove
     */
    public void removeWorkItem(WorkItem workItem) {
        workItems.remove(workItem);
    }

    /**
     * Returns the list of work items in this project.
     *
     * @return list of work items
     */
    public List<WorkItem> getWorkItems() {
        return workItems;
    }
}
