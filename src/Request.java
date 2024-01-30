public class Request {
    private Project project;
    private Freelancer freelancer;
    private int requiredTime;
    private int requiredMoney;
    private int isAcceptedOrDeclined; // 1 for accept, -1 for decline, 0 for pending

    public Request(int requiredTime, int requiredMoney, Freelancer freelancer, Project project){
        this.requiredMoney = requiredMoney;
        this.requiredTime = requiredTime;
        this.freelancer = freelancer;
        this.project = project;
    }
    public Freelancer getRequestFreelancer(){
        return freelancer;
    }
    public int getRequestRequiredTime(){
        return requiredTime;
    }
    public int getRequestRequiredMoney(){
        return requiredMoney;
    }
    public int getIsAcceptedOrDeclined(){
        return isAcceptedOrDeclined;
    }
    public void setIsAcceptedOrDeclined(int isAcceptedOrDeclined){
        this.isAcceptedOrDeclined = isAcceptedOrDeclined;
    }
}
