package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.WorkItem;
import is.hi.tms.ui.Observer;
import is.hi.tms.ui.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the list of projects and their associated work items.
 * Implements the Subject interface for notifying observers of changes.
 */
public class ProjectService implements Subject {
    private List<Project> projects;
    private List<Observer> observers;

    /**
     * Constructs a ProjectService with empty project and observer lists.
     */
    public ProjectService() {
        this.projects = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Creates a new project and adds it to the list.
     *
     * @param name the name of the project
     * @return the created Project
     */
    public Project createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        notifyObservers("Project created: " + name);
        return project;
    }

    /**
     * Adds a work item to a project.
     *
     * @param project the project
     * @param workItem the work item to add
     */
    public void addWorkItemToProject(Project project, WorkItem workItem) {
        project.addWorkItem(workItem);
        notifyObservers("WorkItem added to project: " + project.getTitle());
    }

    /**
     * Removes a work item from a project.
     *
     * @param project the project
     * @param workItem the work item to remove
     */
    public void removeWorkItemFromProject(Project project, WorkItem workItem) {
        project.removeWorkItem(workItem);
        notifyObservers("WorkItem removed from project: " + project.getTitle());
    }

    /**
     * Returns a list of all projects.
     *
     * @return list of projects
     */
    public List<Project> listProjects() {
        return projects;
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
