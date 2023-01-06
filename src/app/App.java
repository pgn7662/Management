package app;

import constants.*;
import people.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class App {
    private Input input;
    private ApartmentComplex apartmentComplex;
    private int taskCount;

    public App() {
        this.input = new Input();
        this.apartmentComplex = new ApartmentComplex("Geethayala Apartments", "Selaiyur,Kanchipuram", 600803);
        taskCount = 1;
    }

    public ArrayList<Task> getTaskList() {
        return apartmentComplex.getTaskList();
    }

    public ArrayList<Helper> getEmployeeList() {
        return apartmentComplex.getEmployeeList();
    }

    public void addTask(Tenant tenant, String description, HelperCategory helperCategory) {
        Task taskToBeAdded = new Task(tenant, description, helperCategory, taskCount++, TaskStatus.YET_TO_START,IssueType.INDIVIDUAL_ISSUE);
        tenant.addToRequestList(taskToBeAdded, apartmentComplex);
    }

    public void addComplaint(Tenant tenant, String complaintDescription, HelperCategory helperCategory, String blockName, int floorNumber) {
        Task complaintToBeAdded = new Task(tenant, complaintDescription, helperCategory, taskCount++, TaskStatus.YET_TO_START, IssueType.COMMON_ISSUE);
        complaintToBeAdded.setLocation("Block - " + blockName + " " + "Floor - " + floorNumber);
        tenant.addToRequestList(complaintToBeAdded, apartmentComplex);
    }

    public void addAnnouncement(String heading, String description, String date, Manager manager) {
        Announcement announcementToBeAdded = new Announcement(heading, description, date);
        manager.addAnnouncement(announcementToBeAdded, apartmentComplex);
    }

    public void assignHelperToTask(Task taskToBeAssigned, Helper employeeToBeAssigned, Manager manager) {
        manager.assignHelper(taskToBeAssigned, employeeToBeAssigned);
    }

    protected Task getTaskFromList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("Nothing to show");
            return null;
        } else {
            loop:
            for (int counter = 0; counter < taskList.size(); counter++) {
                System.out.println(String.format("%2d", (counter + 1)) + "| " + taskList.get(counter));
                if ((counter + 1) % 10 == 0 || ((taskList.size() - 1) == counter)) {
                    System.out.println("Page " + ((counter / 10) + 1));
                    while (true) {
                        System.out.println("Enter 1 to select the task from the above list\nEnter 2 to go to next page\nEnter 3 to go to previous page");
                        switch (input.getInteger()) {
                            case 1 -> {
                                break loop;
                            }
                            case 2 -> {
                                if (counter == taskList.size() - 1) {
                                    System.out.println("This is the last page");
                                    continue;
                                }
                                continue loop;
                            }
                            case 3 -> {
                                if (counter <= 10) {
                                    System.out.println("This is the first page");
                                    continue;
                                } else if (counter != taskList.size() - 1)
                                    counter -= 20;
                                else
                                    counter -= (10 + (counter % 10));
                                continue loop;
                            }
                            default -> System.out.println("Enter a valid choice");
                        }
                    }
                }
            }
            System.out.print("Enter the serial number from the above list to select the employee:");
            while (true) {
                Task taskToBeSelected = taskList.get(input.getInteger());
                if (taskToBeSelected == null) {
                    System.out.print("Enter a valid number:");
                } else
                    return taskToBeSelected;
            }
        }
    }
    private void addParcelNotification(Parcel parcel,User receiver,User sender){
        Notification notificationToBeSent = new Notification("Security Guard","Parcel has been"+parcel.getParcelStatus(), NotificationType.PARCEL);
        receiver.addNotification(notificationToBeSent);
    }
    private void addTaskAddedNotification(Task task,User sender,User receiver){
        Notification notificationToBeAdded = new Notification("Tenant","Task has been assigned with task type "+task.getCategory(), NotificationType.TASK);
        receiver.addNotification(notificationToBeAdded);
    }

    private void addAssignTaskNotification(Task task,User sender,User receiver){
        Notification notificationToBeSent = new Notification("Manager","Task has been assigned at "+task.getFlatNumber(), NotificationType.TASK);
        receiver.addNotification(notificationToBeSent);
    }

    private void addMaintenanceBillNotification(Tenant tenant){
        if(LocalDate.now().getDayOfMonth()<=10){
            String date = "10-" + LocalDate.now().getMonthValue() + "-" + LocalDate.now().getYear();//"01-01-2023"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dueDate = LocalDate.parse(date, formatter);
            Notification notificationToBeSent;
            String heading = "";
            if (LocalDate.now().isEqual(dueDate) && tenant.getMaintenanceBill().getTotalMaintenanceCost() != 0)
                heading = "Today is the last date to pay maintenance, pay sooner to avoid fine";
            else if (dueDate.minusDays(1).isEqual(LocalDate.now()) && tenant.getMaintenanceBill().getTotalMaintenanceCost() != 0)
                heading = "Tomorrow is the last date to pay maintenance bill";
            else if (dueDate.minusDays(10).isEqual(LocalDate.now())) {
                calculateTaskCostPerMonth(tenant);
                heading = "Total maintenance bill for the last month is Rs. " + tenant.getMaintenanceBill().getTotalMaintenanceCost() + "\nPay before " + dueDate + "to avoid fine";
            }
            notificationToBeSent = new Notification("Manager",heading,NotificationType.PAYMENT);
            tenant.addNotification(notificationToBeSent);
        }

    }

    private void addTaskCompletionNotification(Task task,User receiver){
        Notification notificationToBeSent = new Notification("App","Task has been completed and task cost of Rs. "+task.getTaskCost()+task.getRequirementCost()+"has been added to your maintenance bill",NotificationType.PAYMENT);
        receiver.addNotification(notificationToBeSent);
    }

    protected void changeParcelStatus(Parcel parcel, SecurityGuard securityGuard, ParcelStatus parcelStatus){
        securityGuard.updateParcelStatus(parcel,parcelStatus);
        addParcelNotification(parcel,parcel.getTenant(),securityGuard);
    }

    protected double calculateTaskCostPerMonth(Tenant tenant){
        double totalCost = 0;
        if(LocalDate.now().getDayOfMonth() == 1){
            for (Task task : tenant.getMyRequestList()) {
                if (task.getIssueType().equals(IssueType.INDIVIDUAL_ISSUE) && task.getCompletedDate().isBefore(LocalDate.now()))
                    totalCost += (task.getRequirementCost() + task.getTaskCost());
            }
        }
        return totalCost;
    }

    protected double calculateFine(Tenant tenant){
        double fineCost = 0;
        if(tenant.getMaintenanceBill().getDueDate().isAfter((LocalDate.now()))){
            int days = (int)tenant.getMaintenanceBill().getDueDate().datesUntil(LocalDate.now()).count();
            fineCost = ((days+1)/tenant.getMaintenanceBill().getFinePeriod()) * tenant.getMaintenanceBill().getFineRate() * (tenant.getMaintenanceBill().getTotalTaskCostPerMonth()+tenant.getMaintenanceBill().getGeneralMaintenanceCost()/100);
        }
        return fineCost;
    }

    protected void payBill(Tenant tenant,MaintenanceBill maintenanceBill) {
        maintenanceBill.setPaymentStatus(PaymentStatus.PAID);
    }

    protected void addParcel(Tenant tenant,LocalDate deliveryDate,String senderName){
        Parcel parcel = new Parcel(tenant,deliveryDate,ParcelStatus.YET_TO_RECEIVE,senderName);
        apartmentComplex.addParcel(parcel);
    }

    protected void editParcelDate(Tenant tenant,Parcel parcel,LocalDate deliveryDate){
        parcel.setDeliveryDate(deliveryDate,tenant);
    }

    protected void editParcelSender(Tenant tenant,Parcel parcel,String sender){
        parcel.setSender(sender,tenant);
    }
}
