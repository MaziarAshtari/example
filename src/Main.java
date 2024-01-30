import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Project> projectList = new ArrayList<>();
    private static final List<Freelancer> freelancersList = new ArrayList<>();
    private static final List<Client> clientsList = new ArrayList<>();
    static Client currentClient = null;
    static Freelancer currentFreelancer = null;
    static Scanner scanner = new Scanner(System.in);

    public static void signUpClient(){
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if(isUsernameAvailableClient(username)){
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            Client newClient = new Client(username, password);
            clientsList.add(newClient);
            currentClient = newClient;
            currentFreelancer = null;

            System.out.println("Signup successful. Welcome, " + username + "!\n");
        } else {
            System.out.println("Username already taken. Please choose a different username.\n");
        }

    }

    public static void signInClient(){
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (isValidCredentialsClient(username, password)) {
            System.out.println("Sign-in successful. Welcome back, " + username + "!\n");
        } else {
            System.out.println("Invalid username or password.\n");
        }
    }

    private static boolean isValidCredentialsClient(String username, String password) {
        for (Client client : clientsList) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                currentClient = client;
                currentFreelancer = null;
                return true;
            }
        }
        return false;
    }

    private static boolean isUsernameAvailableClient(String username){
        for(Client client : clientsList){
            if(client.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public static void signUpFreelancer(){
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if(isUsernameAvailableFreelancer(username)){
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            Freelancer newFreelancer = new Freelancer(username, password);
            freelancersList.add(newFreelancer);
            currentFreelancer = newFreelancer;
            currentClient = null;

            System.out.println("Signup successful. Welcome, " + username + "!\n");
        } else {
            System.out.println("Username already taken. Please choose a different username.\n");
        }

    }

    public static void signInFreelancer(){
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (isValidCredentialsFreelancer(username, password)) {
            System.out.println("Sign-in successful. Welcome back, " + username + "!\n");
        } else {
            System.out.println("Invalid username or password.\n");
        }
    }

    private static boolean isValidCredentialsFreelancer(String username, String password) {
        for (Freelancer freelancer : freelancersList) {
            if (freelancer.getUsername().equals(username) && freelancer.getPassword().equals(password)) {
                currentFreelancer = freelancer;
                currentClient = null;
                return true;
            }
        }
        return false;
    }

    private static boolean isUsernameAvailableFreelancer(String username){
        for(Freelancer freelancer : freelancersList){
            if(freelancer.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    private static void addProject(Client client) {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        System.out.println("Please provide a brief description of the project you want to create: ");
        String projectDescription = scanner.nextLine();

        Project newProject = new Project(projectName,projectDescription,client);
//        user.getProjects().add(newProject);
        client.addProject(newProject);
        projectList.add(newProject);

        System.out.println("Project '" + projectName + "' added successfully!\n");
    }

    private static void displayAllProjects() {
        if (projectList.isEmpty()) {
            System.out.println("No projects available.\n");
        } else {
            for (Project project : projectList) {
                System.out.println("- " + project.getProjectName() + " by " + project.getProjectClient().getUsername());
                System.out.println("Description: " + project.getProjectDescription() +"\n");
            }
        }
    }

    private static void displayClientProjects(Client client) {
        System.out.println("\nProjects created by client: " + client.getUsername());

        if (client.getprojects().isEmpty()) {
            System.out.println("No projects available.");
        } else {
            for (Project project : client.getprojects()) {
                System.out.println("- " + project.getProjectName());
                System.out.println("Description: " + project.getProjectDescription() +"\n");
            }
        }
    }

    private static void removeClientProject(String projectName, Client currentClient) {
        Iterator<Project> iterator1 = currentClient.getprojects().iterator();
        Iterator<Project> iterator2 = projectList.iterator();
        while (iterator1.hasNext()) {
            Project project = iterator1.next();
            if (project.getProjectName().equals(projectName) && project.getProjectClient().getUsername().equals(currentClient.getUsername())) {
                iterator1.remove();
                System.out.println("Project '" + projectName + "' removed successfully.");
            }
        }
        while (iterator2.hasNext()){
            Project project = iterator2.next();
            if(project.getProjectName().equals(projectName) && project.getProjectClient().getUsername().equals(currentClient.getUsername())){
                iterator2.remove();
                return;
            }
        }
        System.out.println("Project not found. Unable to remove.");
    }

    private static void makeProjectRequest(String projectName, String clientName, Freelancer freelancer){
        for (Project project : projectList) {
            if (project.getProjectName().equals(projectName) && project.getProjectClient().getUsername().equals(clientName)) {
                for (Request iteratedRequest : project.getRequests()) {
                    if (iteratedRequest.getRequestFreelancer().equals(freelancer)) {
                        System.out.println("Unable to make the request, You've already made a request for this project");
                        return;
                    }
                }
                System.out.print("Please enter your proposed money in US dollar: ");
                int requiredMoney = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Please enter your proposed number of days it takes to complete the project: ");
                int requiredTime = scanner.nextInt();
                scanner.nextLine();
                Request freelancerRequest = new Request(requiredTime, requiredMoney, freelancer, project);
                project.getRequests().add(freelancerRequest);
                freelancer.getRequests().add(freelancerRequest);
                System.out.println("Request added successfully");
                return;
            }
        }
        System.out.println("Project not found. Unable to make a new request.");
    }

    private static void displayProjectRequest(String projectName, Client client){
        boolean isProjectFound = false;
        for (Project project : client.getprojects()) {
            if (project.getProjectName().equals(projectName) && project.getProjectClient().equals(client)) {
                for (Request request : project.getRequests()) {
                    System.out.print("Freelancer: " + request.getRequestFreelancer().getUsername() + "\t\t\tFreelancer score: " + (int) request.getRequestFreelancer().getScore() + "%\t\t\tProposed time: " + request.getRequestRequiredTime() + " day(s)\t\t\tProposed Money: " + request.getRequestRequiredMoney() + "$\t\t\tRequest Status: ");
                    if (request.getIsAcceptedOrDeclined() == 1)
                        System.out.println("\u001B[32m" + "Accepted" + "\u001B[0m");
                    else if (request.getIsAcceptedOrDeclined() == -1)
                        System.out.println("\u001B[31m" + "Declined" + "\u001B[0m");
                    else
                        System.out.println("\u001B[33m" + "Pending" + "\u001B[0m");
                    isProjectFound = true;
                }
                if (project.getRequests().isEmpty()) {
                    System.out.println("No request was found!");
                    return;
                }
            }
        }
        if (!isProjectFound){
            System.out.println("No project was found!");
        }
    }

    private static void acceptOrDeclineRequest(String projectName, String freelancerName, Client client){
        boolean isProjectFound = false;
        boolean isRequestFound = false;
        for (Project project : client.getprojects()) {
            if (project.getProjectName().equals(projectName)) {
                isProjectFound = true;
                for (Request request : project.getRequests()) {
                    if (request.getRequestFreelancer().getUsername().equals(freelancerName)) {
                        System.out.print("Freelancer: " + request.getRequestFreelancer().getUsername() + "\t\t\tFreelancer score: " + (int) request.getRequestFreelancer().getScore() + "%\t\t\tProposed time: " + request.getRequestRequiredTime() + " day(s)\t\t\tProposed Money: " + request.getRequestRequiredMoney() + "$\n");
                        System.out.print("Above request is found. Enter 'y' to Accept or 'n' to decline the request: ");
                        String acceptOrDeclineRequest = scanner.nextLine();
                        if (acceptOrDeclineRequest.equalsIgnoreCase("y")) {
                            request.setIsAcceptedOrDeclined(1);
                            request.getRequestFreelancer().setAcceptedRequestsNum(request.getRequestFreelancer().getAcceptedRequestsNum() + 1);
                            System.out.println("Request status changed successfully");
                            return;
                        } else if (acceptOrDeclineRequest.equalsIgnoreCase("n")) {
                            if (request.getIsAcceptedOrDeclined() == 1) {
                                request.getRequestFreelancer().setFailedRequestNum(request.getRequestFreelancer().getFailedRequestNum() + 1);
                            }
                            request.setIsAcceptedOrDeclined(-1);
                            System.out.println("Request status changed successfully");
                            return;
                        } else {
                            System.out.println("Invalid input. Unable to change the request status");
                            return;
                        }
                    }
                }
            }
        }
        if(!isProjectFound){
            System.out.println("Unable to find the project");
        } else if (!isRequestFound) {
            System.out.println("Unable to find the request");
        }
    }

    private static void clientInputMessage(){
        System.out.println("Please select an option from the following choices and enter the corresponding number:");
        System.out.println("1.Create a new project");
        System.out.println("2.Remove a project");
        System.out.println("3.Display my projects");
        System.out.println("4.Display the requests for a project");
        System.out.println("5.Accept/Decline a request for a project");
        System.out.println("6.Sign out");
    }

    private static void freelancerInputMessage(){
        System.out.println("Please select an option from the following choices and enter the corresponding number:");
        System.out.println("1.View Available Projects");
        System.out.println("2.Request for a project");
        System.out.println("3.Show my requests");
        System.out.println("4.Sign out");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select an option from the following choices and enter the corresponding number:");
            System.out.println("1.I want to sign up as a client");
            System.out.println("2.I want to sign up as a freelancer");
            System.out.println("3.I want to sign in as a client");
            System.out.println("4.I want to sign in as a freelancer");
            int select1 = scanner.nextInt();
            scanner.nextLine();
            switch (select1) {
                case 1 -> {
                    signUpClient();
                    System.out.println("----------------------------------------------------------------------------------------");
                }
                case 2 -> {
                    signUpFreelancer();
                    System.out.println("----------------------------------------------------------------------------------------");
                }
                case 3 -> {
                    signInClient();
                    System.out.println("----------------------------------------------------------------------------------------");
                }
                case 4 -> {
                    signInFreelancer();
                    System.out.println("----------------------------------------------------------------------------------------");
                }
                default -> {
                    System.out.println("Invalid input! Please try again\n");
                    continue;
                }
            }

            if((select1 == 2 || select1 == 4) && currentFreelancer != null){
                freelancerInputMessage();

                while (true) {
                    int select2 = scanner.nextInt();
                    scanner.nextLine();
                    if (select2 == 1) {
                        displayAllProjects();
                        System.out.println("----------------------------------------------------------------------------------------");
                        freelancerInputMessage();
                    } else if (select2 == 2) {
                        System.out.print("Please enter the project name: ");
                        String requestProjectName = scanner.nextLine();
                        System.out.print("Please enter the client name for the project: ");
                        String requestClientName = scanner.nextLine();
                        makeProjectRequest(requestProjectName,requestClientName,currentFreelancer);
                        System.out.println("----------------------------------------------------------------------------------------");
                        freelancerInputMessage();
                    } else if (select2 == 3){
                        currentFreelancer.printAcceptedRequests(currentFreelancer);
                        currentFreelancer.printPendingRequests(currentFreelancer);
                        currentFreelancer.printDeclinedRequests(currentFreelancer);
                        System.out.println("----------------------------------------------------------------------------------------");
                        freelancerInputMessage();
                    } else if (select2 == 4) {
                        currentFreelancer = null;
                        System.out.println("You are now signed out.\n");
                        System.out.println("----------------------------------------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Invalid input! Please try again\n");
                    }
                }
            }

            if((select1 == 1 || select1 == 3) && currentClient != null){
                clientInputMessage();

                while (true) {
                    int select2 = scanner.nextInt();
                    scanner.nextLine();
                    if (select2 == 1) {
                        addProject(currentClient);
                        System.out.println("----------------------------------------------------------------------------------------");
                        clientInputMessage();
                    } else if (select2 == 2) {
                        System.out.print("Please enter the project name you would like to remove: ");
                        String projectName = scanner.nextLine();
                        removeClientProject(projectName,currentClient);
                        System.out.println("----------------------------------------------------------------------------------------");
                        clientInputMessage();
                    } else if (select2 == 3) {
                        displayClientProjects(currentClient);
                        System.out.println("----------------------------------------------------------------------------------------");
                        clientInputMessage();
                    } else if (select2 == 4) {
                        System.out.print("Please enter the project name you would like to see its requests: ");
                        String seeRequestsProjectName = scanner.nextLine();
                        displayProjectRequest(seeRequestsProjectName,currentClient);
                        System.out.println("----------------------------------------------------------------------------------------");
                        clientInputMessage();
                    } else if (select2 == 5) {
                        System.out.print("Please enter the project name you would like to accept/decline its request: ");
                        String projectNameAcceptDecline = scanner.nextLine();
                        System.out.print("Please enter the freelancer name you would like to accept/decline his/her request: ");
                        String freelancerNameAcceptDecline = scanner.nextLine();
                        acceptOrDeclineRequest(projectNameAcceptDecline,freelancerNameAcceptDecline,currentClient);
                        System.out.println("----------------------------------------------------------------------------------------");
                        clientInputMessage();
                    } else if (select2 == 6) {
                        currentClient = null;
                        System.out.println("You are now signed out\n");
                        System.out.println("----------------------------------------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("Invalid input! Please try again\n");
                    }
                }
            }
        }
    }
}