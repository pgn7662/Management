package app;

import constants.HelperCategory;
import constants.HelperStatus;
import constants.PaymentStatus;
import constants.TaskStatus;
import people.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManagerMenu{
    private final Input input = new Input();
    public void displayManagerMenu(ApartmentComplex apartmentComplex,Manager manager, App app){
        System.out.println("Enter 1 to add a worker");
        System.out.println("Enter 2 to remove a worker");
        System.out.println("Enter 3 to view all Tasks");
        System.out.println("Enter 4 to assign task");
        System.out.println("Enter 5 to send Important Announcement");
        System.out.println("Enter 6 to view all notifications");
        switch(input.getInteger()) {
            case 1 ->{
                System.out.print("Enter the helper name:");
                String name = input.getName();
                System.out.print("Enter the age:");
                int age = input.getAge();
                System.out.print("Enter the phone number:");
                long phoneNumber = input.getPhoneNumber();
                System.out.print("Enter the location:");
                String location = input.getString();
                System.out.print("Enter the helper category:");
                HelperCategory helperCategory = HelperCategory.ELECTRICIAN;
                manager.addHelper(name,age,phoneNumber,location, helperCategory,apartmentComplex);
            }
            case 2 ->{
                System.out.println("Enter the helper name :");
                String helperName = input.getName();
                ArrayList<Helper> helperList = new ArrayList<>();
                for(Helper helperToBeChecked: app.getEmployeeList()) {
                    if(helperToBeChecked.getName().contains(helperName))
                        helperList.add(helperToBeChecked);
                }
                Helper helperToBeRemoved = getHelperFromList(helperList);
                if(helperToBeRemoved != null)
                    manager.removeHelper(helperToBeRemoved,apartmentComplex);
            }
            case 3 -> System.out.println(app.getTaskFromList(apartmentComplex.getTaskList()));
            case 4 -> viewTaskToBeAssigned(app,manager);
            case 5 -> sendAnnouncement(app,manager);
        }

    }
    private void viewTaskToBeAssigned(App app,Manager manager) {
        ArrayList<Task> taskList = new ArrayList<>();
        for(Task taskToBeViewed:app.getTaskList()){
            if(taskToBeViewed.getTaskStatus().equals(TaskStatus.YET_TO_START)){
                taskList.add(taskToBeViewed);
            }
        }
        Task taskToBeAssigned = app.getTaskFromList(taskList);
        if(taskToBeAssigned != null) {
            ArrayList<Helper> helperList = new ArrayList<>();
            for (Helper helperToBeChecked : app.getEmployeeList()) {
                if (helperToBeChecked.getHelperStatus().equals(HelperStatus.READY_TO_WORK) && helperToBeChecked.getHelperCategory().equals(taskToBeAssigned.getCategory()))
                    helperList.add(helperToBeChecked);
            }
            Helper helperToBeAssigned = getHelperFromList(helperList);
            if (helperToBeAssigned != null)
                app.assignHelperToTask(taskToBeAssigned, helperToBeAssigned,manager);
        }
    }
    private Helper getHelperFromList(ArrayList<Helper> helperList) {
        if(helperList.size()==0){
            System.out.println("Nothing to show");
            return null;
        }
        else{
            loop: for(int counter = 0;counter < helperList.size(); counter++) {
                System.out.println(String.format("%2d",(counter+1))+"| "+helperList.get(counter));
                if((counter+1)%10 == 0 || ((helperList.size()-1)==counter)){
                    System.out.println("Page " + ((counter/10)+1));
                    while (true) {
                        System.out.println("Enter 1 to select the helper from the above list\nEnter 2 to go to next page\nEnter 3 to go to previous page");
                        switch (input.getInteger()) {
                            case 1 -> {
                                break loop;
                            }
                            case 2 -> {
                                if (counter == helperList.size() - 1) {
                                    System.out.println("This is the last page");
                                    continue;
                                }
                                continue loop;
                            }
                            case 3 -> {
                                if (counter <= 10) {
                                    System.out.println("This is the first page");
                                    continue;
                                }
                                else if(counter != helperList.size()-1)
                                    counter -= 20;
                                else
                                    counter -= (10+(counter%10));
                                continue loop;
                            }
                            default -> System.out.println ("Enter a valid choice");
                        }
                    }
                }
            }
            System.out.print("Enter the serial number from the above list to select the helper:");
            while(true){
                Helper helperToBeSelected = helperList.get(input.getInteger());
                if(helperToBeSelected == null) {
                    System.out.print("Enter a valid number:");
                }
                else
                    return helperToBeSelected;
            }
        }
    }

    private void sendAnnouncement(App app,Manager manager){
        System.out.print("Enter the Announcement heading:");
        String announcement = input.getString();
        System.out.println("Enter the description of the announcement");
        String description = input.getString();
        System.out.print("Enter the date when it happens in the format(DD-MM-YYYY)");
        String date = input.getString();
        app.addAnnouncement(announcement,description,date,manager);
    }

    private void setMaintenanceBill(ApartmentComplex apartmentComplex,App app,Manager manager){
        System.out.print("Enter the general maintenance cost:");
        double generalCost = input.getDouble();
        String dueDate = "10-"+ LocalDate.now().getDayOfMonth()+"-"+LocalDate.now().getYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        MaintenanceBill maintenanceBill = new MaintenanceBill(generalCost,1.6,LocalDate.parse(dueDate,formatter),10);
        for(int counter = 0 ; counter < apartmentComplex.getApartments().size() ; counter++){
            Tenant tenant = apartmentComplex.getApartments().get(counter).getTenant();
            tenant.setMaintenanceBill(maintenanceBill,manager);
            app.calculateTaskCostPerMonth(tenant);
        }
    }
}

