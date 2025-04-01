package is.hi.tms.domain;

import java.util.ArrayList;
import java.util.List;

public class Project implements WorkItem {
    private String name;
    private List<WorkItem> workItems;

    public Project(String name) {
        this.name = name;
        this.workItems = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return name;
    }

    public void addWorkItem(WorkItem workItem) {
        workItems.add(workItem);
    }

    public void removeWorkItem(WorkItem workItem) {
        workItems.remove(workItem);
    }

    public List<WorkItem> getWorkItems() {
        return workItems;
    }
}