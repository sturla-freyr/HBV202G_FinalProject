package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.WorkItem;
import is.hi.tms.ui.Observer;
import is.hi.tms.ui.Subject;

import java.util.ArrayList;
import java.util.List;

public class ProjectService implements Subject {
    private List<Project> projects;
    private List<Observer> observers;

    public ProjectService() {
        this.projects = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Project createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        notifyObservers("Project created: " + name);
        return project;
    }

    public void addWorkItemToProject(Project project, WorkItem workItem) {
        project.addWorkItem(workItem);
        notifyObservers("WorkItem added to project: " + project.getTitle());
    }

    public void removeWorkItemFromProject(Project project, WorkItem workItem) {
        project.removeWorkItem(workItem);
        notifyObservers("WorkItem removed from project: " + project.getTitle());
    }

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