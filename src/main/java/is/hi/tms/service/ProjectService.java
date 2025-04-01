package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.WorkItem;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private List<Project> projects;

    public ProjectService() {
        this.projects = new ArrayList<>();
    }

    public Project createProject(String name) {
        Project project = new Project(name);
        projects.add(project);
        return project;
    }

    public void addWorkItemToProject(Project project, WorkItem workItem) {
        project.addWorkItem(workItem); // Updated to use addWorkItem
    }

    public void removeWorkItemFromProject(Project project, WorkItem workItem) {
        project.removeWorkItem(workItem); // Updated to use removeWorkItem
    }

    public List<Project> listProjects() {
        return projects;
    }
}