package is.hi.tms.domain;

/**
 * Represents a common interface for all items that can be part of a project,
 * such as tasks and subprojects.
 */
public interface WorkItem {
    /**
     * Returns the title of the work item.
     *
     * @return the title
     */
    String getTitle();
}
