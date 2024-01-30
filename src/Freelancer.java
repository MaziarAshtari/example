import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Freelancer {

    private String Username;
    private String Password;
    private float score;
    private float acceptedRequestsNum;
    private float failedRequestNum;
    private ArrayList<Request> requests = new ArrayList<>();

    public Freelancer(String Username, String Password){
        this.Username = Username;
        this.Password = Password;
    }
    public float getAcceptedRequestsNum(){
        return acceptedRequestsNum;
    }
    public void setAcceptedRequestsNum(float acceptedRequestsNum){
        this.acceptedRequestsNum = acceptedRequestsNum;
        score = ((acceptedRequestsNum - failedRequestNum)/(acceptedRequestsNum + failedRequestNum))*100;
    }
    public float getFailedRequestNum(){
        return failedRequestNum;
    }
    public void setFailedRequestNum(float failedRequestNum){
        this.failedRequestNum = failedRequestNum;
        score = ((acceptedRequestsNum - failedRequestNum)/(acceptedRequestsNum + failedRequestNum))*100;
    }
    public float getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }


    public String getUsername(){
        return Username;
    }

    public String getPassword(){
        return Password;
    }
    public ArrayList<Request> getRequests(){
        return requests;
    }
    public void printAcceptedRequests(Freelancer freelancer){
        boolean isAcceptedRequestFound = false;
        Iterator<Request> iterator = freelancer.getRequests().iterator();
        while (iterator.hasNext()){
            Request request = iterator.next();
            if(request.getIsAcceptedOrDeclined() == 1){
                System.out.println("Freelancer: " + request.getRequestFreelancer().getUsername() + "\t\t\tProposed time: " + request.getRequestRequiredTime() +" day(s)\t\t\tProposed Money: " + request.getRequestRequiredMoney() + "$\t\t\tRequest Status: " + "\u001B[32m" +"Accepted" + "\u001B[0m");
                isAcceptedRequestFound = true;
            }
        }
        if(isAcceptedRequestFound == false){
            System.out.println("No accepted request was found!");
        }
    }
    public void printDeclinedRequests(Freelancer freelancer){
        boolean isDeclinedRequestFound = false;
        Iterator<Request> iterator = freelancer.getRequests().iterator();
        while (iterator.hasNext()){
            Request request = iterator.next();
            if(request.getIsAcceptedOrDeclined() == -1){
                System.out.println("Freelancer: " + request.getRequestFreelancer().getUsername() + "\t\t\tProposed time: " + request.getRequestRequiredTime() +" day(s)\t\t\tProposed Money: " + request.getRequestRequiredMoney() + "$\t\t\tRequest Status: " + "\u001B[31m" + "Declined" + "\u001B[0m");
                isDeclinedRequestFound = true;
            }
        }
        if(isDeclinedRequestFound == false){
            System.out.println("No declined request was found!");
        }
    }
    public void printPendingRequests(Freelancer freelancer){
        boolean isPendingRequestFound = false;
        Iterator<Request> iterator = freelancer.getRequests().iterator();
        while (iterator.hasNext()){
            Request request = iterator.next();
            if(request.getIsAcceptedOrDeclined() != 1 && request.getIsAcceptedOrDeclined() != -1){
                System.out.println("Freelancer: " + request.getRequestFreelancer().getUsername() + "\t\t\tProposed time: " + request.getRequestRequiredTime() +" day(s)\t\t\tProposed Money: " + request.getRequestRequiredMoney() + "$\t\t\tRequest Status: " + "\u001B[33m" + "Pending" + "\u001B[0m");
                isPendingRequestFound = true;
            }
        }
        if(isPendingRequestFound == false){
            System.out.println("No Pending request was found!");
        }
    }
}
