package people;

import constants.HelperCategory;
import constants.TaskStatus;
import constants.IssueType;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private Tenant tenant;
    private String flatNumber;
    private String description;
    private HelperCategory category;
    private int taskNumber;
    private TaskStatus taskStatus;
    private ArrayList<Requirement> requirements;
    private final LocalDate createdDate;
    private final String createdTime;
    private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
    private Date date = new Date();
    private Employee assignedHelper;
    private double taskCost;
    private String completedTime;
    private LocalDate completedDate;
    private String location;
    private IssueType issueType;

    public Task(Tenant tenant, String description, HelperCategory category, int taskNumber, TaskStatus taskStatus,IssueType issueType) {
        this.tenant = tenant;
        this.flatNumber = tenant.getApartment().getFlatNumber();
        this.description = description;
        this.category = category;
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
        createdDate = LocalDate.now();
        createdTime = formatter.format(date);
        this.issueType = issueType;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public HelperCategory getCategory() {
        return category;
    }


    public double getTaskCost() {
        return taskCost;
    }

    public void setRequirements(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public void setTaskCost(double taskCost) {
        this.taskCost = taskCost;
    }

    public double getRequirementCost(){
        double cost = 0;
        for(Requirement requirement:requirements)
            cost += (requirement.getCost()*requirement.getQuantity());
        return cost;
    }

    public void setCompletedTime() {
        this.completedTime = formatter.format(new Date());
    }

    public void setCompletedDate() {
        this.completedDate = LocalDate.now();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setAssignedHelper(Employee assignedHelper) {
        this.assignedHelper = assignedHelper;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public double getTotalTaskCost(){
        return taskCost+getRequirementCost();
    }

    @Override
    public String toString() {
        return String.format("%2d",taskNumber)+"| "+flatNumber+"| "+String.format("%19s", category)+"| "+String.format("%12s",taskStatus)+"| "+description;
    }

    public void viewMoreDetails(){
        System.out.println("Task Number:"+String.format("%3d",taskNumber)+"\t\tCreated on: "+createdDate+", "+createdTime);
        System.out.println("Requester Name: "+tenant.getName());
        System.out.println("Task Description:\n\t"+description);
        System.out.println("Flat Number: "+flatNumber);
        System.out.println("Task Status: "+taskStatus);
        if(!taskStatus.equals(TaskStatus.YET_TO_START)){
            System.out.println("Assigned Helper: "+assignedHelper.getName());
            System.out.println("Specialization: "+category);
            if(requirements.size()!=0)
            {
                System.out.println("Sl.No |         Product Name          | Quantity |   Rate   |    Cost   |");
                for(int counter = 0;counter < requirements.size();counter++)
                    System.out.println(String.format("%5d",counter+1)+" | "+String.format("%29s",requirements.get(counter).getProductRequired())+" | "+String.format("%8d",requirements.get(counter).getQuantity())+" | "+String.format("%8.2f",requirements.get(counter).getCost())+" | "+String.format("%8.2f",(requirements.get(counter).getCost()*requirements.get(counter).getQuantity()))+"  |");
                System.out.println("\t\t\t\t\t\t\t\t\t       Requirement cost |"+String.format("%9.2f",getRequirementCost())+"  |");
                System.out.println("\t\t\t\t\t\t\t\t\t              Task cost |"+String.format("%9.2f",taskCost)+"  |");
            }
            if(taskCost!=0)
                System.out.println("\t\t\t\t\t\t\t\t\t             Total cost |"+String.format("%9.2f",getRequirementCost()+taskCost)+"  |");
            if(taskStatus.equals(TaskStatus.COMPLETED))
                System.out.println("Completed on: "+completedDate+", "+completedTime);
        }
    }
}
