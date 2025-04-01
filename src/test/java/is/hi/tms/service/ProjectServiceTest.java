package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.Task;
import is.hi.tms.domain.TaskPriority;
import is.hi.tms.domain.TaskStatus;
import is.hi.tms.domain.WorkItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectServiceTest {

    @Test
    public void testCreateProject() {
        ProjectService projectService = new ProjectService();
        Project project = projectService.createProject("Test Project");
        List<Project> projects = projectService.listProjects();
        assertEquals(1, projects.size());
        assertEquals(project, projects.get(0));
    }

    @Test
    public void testAddWorkItemToProject() {
        ProjectService projectService = new ProjectService();
        Project project = projectService.createProject("Test Project");

        // Create a Task and add it as a WorkItem
        Task task = new Task("Task 1", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        projectService.addWorkItemToProject(project, task);

        // Verify the task was added
        List<WorkItem> workItems = project.getWorkItems();
        assertEquals(1, workItems.size());
        assertEquals(task, workItems.get(0));
    }

    @Test
    public void testRemoveWorkItemFromProject() {
        ProjectService projectService = new ProjectService();
        Project project = projectService.createProject("Test Project");

        // Create a Task and add it as a WorkItem
        Task task = new Task("Task 1", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        projectService.addWorkItemToProject(project, task);

        // Remove the task
        projectService.removeWorkItemFromProject(project, task);

        // Verify the task was removed
        List<WorkItem> workItems = project.getWorkItems();
        assertTrue(workItems.isEmpty());
    }

    @Test
    public void testListProjects() {
        ProjectService projectService = new ProjectService();

        // Create two projects
        Project project1 = projectService.createProject("Project 1");
        Project project2 = projectService.createProject("Project 2");

        // Verify the projects are listed
        List<Project> projects = projectService.listProjects();
        assertEquals(2, projects.size());
        assertEquals(project1, projects.get(0));
        assertEquals(project2, projects.get(1));
    }
}