package app;

import constants.HelperStatus;
import constants.TaskStatus;
import people.Helper;
import people.Requirement;
import people.Task;
import java.util.ArrayList;

public class HelperMenu {
    private Input input = new Input();
    public void displayHelperMenu(App app, Helper helper){
        loop: while(true){
            System.out.println("Enter 1 to view assigned task");
            System.out.println("Enter 2 to view task list");
            System.out.println("Enter 3 to change status");
            System.out.println("Enter any other number to log out");
            switch (input.getInteger()) {
                case 1 -> {
                    Task taskAssigned = null;
                    for (Task taskToBeChecked : helper.getTaskList()) {
                        if (taskToBeChecked.getTaskStatus().equals(TaskStatus.ASSIGNED)) {
                            taskAssigned = taskToBeChecked;
                            break;
                        }

                    }
                    if (taskAssigned != null) {
                        taskAssigned.setTaskStatus(TaskStatus.IN_PROGRESS);
                        checkForRequirements(helper, taskAssigned);
                        buyRequirements(taskAssigned, helper);
                        proceedTask(taskAssigned,helper);
                    }
                }
                case 2 -> app.getTaskFromList(helper.getTaskList()).viewMoreDetails();
                case 3 -> changeStatus(helper);
                default -> {
                    break loop;
                }
            }
        }
    }

    private void changeStatus(Helper selectedHelper) {
        if(selectedHelper.getHelperStatus().equals(HelperStatus.TASK_ASSIGNED))
            System.out.println("Task has been assigned to you\nYou will not be able to change status until the task is completed");
        else if(selectedHelper.getHelperStatus().equals(HelperStatus.AT_WORK))
            System.out.println("You are in the task.Complete the task and you can change status");
        else {
            System.out.println("Enter 1 to change status to READY_TO_WORK");
            System.out.println("Enter 2 to change status to IDLE");
            loop: while (true){
                switch (input.getInteger()) {
                    case 1 -> {
                        selectedHelper.setHelperStatus(HelperStatus.READY_TO_WORK);
                        break loop;
                    }
                    case 2 -> {
                        selectedHelper.setHelperStatus(HelperStatus.IDLE);
                        break loop;
                    }
                    default -> System.out.print("Enter a valid choice:");
                }
            }
        }
    }
    private void checkForRequirements(Helper assignedHelper, Task taskAssigned){
        ArrayList<Requirement> requirements= new ArrayList<>();
        while (true){
            System.out.println("Enter 1 to add a item");
            System.out.println("Enter any other number to return");
            if (input.getInteger() == 1) {
                System.out.print("Enter the item name:");
                String itemName = input.getString();
                System.out.print("Enter the quantity:");
                int quantity = input.getInteger();
                requirements.add(new Requirement(itemName, quantity));
            }
            else
                break;
        }
        assignedHelper.addRequirementsToTask(taskAssigned,requirements);
    }

    private void buyRequirements(Task taskAssigned, Helper assignedHelper){
        for(Requirement item:taskAssigned.getRequirements()) {
            System.out.print("Enter the cost of "+item.getProductRequired()+" : ");
            assignedHelper.addRequirementCost(item,input.getDouble());
        }
    }

    private void proceedTask(Task taskAssigned, Helper assignedHelper) {
        System.out.print("Enter the cost for the task:");
        double taskCost = input.getDouble();
        assignedHelper.completeTask(taskAssigned,taskCost);
    }
}
