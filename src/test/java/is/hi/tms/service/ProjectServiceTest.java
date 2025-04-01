package is.hi.tms.service;

import is.hi.tms.domain.Project;
import is.hi.tms.domain.Task;
import is.hi.tms.domain.TaskPriority;
import is.hi.tms.domain.TaskStatus;
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
    public void testAddTaskToProject() {
        ProjectService projectService = new ProjectService();
        Project project = projectService.createProject("Test Project");
        Task task = new Task("Test Task", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        projectService.addTaskToProject(project, task);
        assertEquals(1, project.getTasks().size());
        assertEquals(task, project.getTasks().get(0));
    }

    @Test
    public void testRemoveTaskFromProject() {
        ProjectService projectService = new ProjectService();
        Project project = projectService.createProject("Test Project");
        Task task = new Task("Test Task", "Description", LocalDate.of(2025, 4, 1), TaskStatus.NOT_STARTED, TaskPriority.MEDIUM);
        projectService.addTaskToProject(project, task);
        projectService.removeTaskFromProject(project, task);
        assertTrue(project.getTasks().isEmpty());
    }
}