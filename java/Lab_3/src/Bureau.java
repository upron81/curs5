import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Bureau implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Project> projects = new ArrayList<>();

    public Project createProject(Client client, TechnicalTask technicalTask) {
        Project project = new Project(client, technicalTask);
        projects.add(project);
        return project;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
