import java.util.ArrayList;

public class Project {
    private String projectName;
    private String projectDescription;
    private Client projectClient;
    private ArrayList<Request> requests = new ArrayList<>();

    public Project(String projectName, String projectDescription, Client projectClient){
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectClient = projectClient;
    }

    public String getProjectName(){
        return projectName;
    }
    public String getProjectDescription(){
        return projectDescription;
    }
    public Client getProjectClient(){
        return projectClient;
    }
    public ArrayList<Request> getRequests(){
        return requests;
    }
}
