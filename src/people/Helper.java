package people;

import constants.HelperCategory;
import constants.HelperStatus;
import constants.TaskStatus;
import java.util.ArrayList;

public class Helper extends Employee {
    private HelperCategory helperCategory;
    private HelperStatus helperStatus;
    private ArrayList<Task> taskList;
    public Helper(String name, String userName, String password, String id, int age, long phoneNumber, String location, HelperStatus helperStatus, HelperCategory helperCategory) {
        super(name, userName, password, id, age, phoneNumber, location);
        this.helperCategory = helperCategory;
        this.helperStatus = helperStatus;
        taskList = new ArrayList<>();
    }


    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public void addRequirementsToTask(Task assignedTask, ArrayList<Requirement> requirements){
        assignedTask.setRequirements(requirements);
    }
    public void addRequirementCost(Requirement productRequired,double cost){
        productRequired.setCost(cost,this);
    }
    public HelperStatus getHelperStatus() {
        return helperStatus;
    }

    public HelperCategory getHelperCategory() {
        return helperCategory;
    }

    public void addTask(Task taskToBeAdded,Manager manager) {
        taskList.add(taskToBeAdded);
    }

    public void setHelperStatus(HelperStatus helperStatus) {
        this.helperStatus = helperStatus;
    }
    public void completeTask(Task taskToBeCompleted,double taskCost){
        taskToBeCompleted.setTaskCost(taskCost);
        taskToBeCompleted.setTaskStatus(TaskStatus.COMPLETED);
        setHelperStatus(HelperStatus.READY_TO_WORK);
    }
}
