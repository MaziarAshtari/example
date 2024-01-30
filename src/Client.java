import java.util.ArrayList;

public class Client {

    private String Username;
    private String Password;
    private ArrayList<Project> projects = new ArrayList<>();

    public Client(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }

    public String getUsername(){
        return Username;
    }

    public String getPassword(){
        return Password;
    }
    public ArrayList<Project> getprojects(){
        return projects;
    }
    public void addProject(Project project){
        projects.add(project);
    }
}
