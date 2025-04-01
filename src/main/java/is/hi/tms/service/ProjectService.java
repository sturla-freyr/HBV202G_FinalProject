package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.Task;

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

    public void addTaskToProject(Project project, Task task) {
        project.addTask(task);
    }

    public void removeTaskFromProject(Project project, Task task) {
        project.removeTask(task);
    }

    public List<Project> listProjects() {
        return projects;
    }
}