package app;

import constants.HelperCategory;
import constants.IssueType;
import constants.PaymentStatus;
import people.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class TenantMenu {
    private Input input = new Input();
    public void displayTenantMenu(App app, Tenant tenant){
        System.out.println("Enter 1 to Ask for a Helper");
        System.out.println("Enter 2 to Pay maintenance bill");
        System.out.println("Enter 3 to raise a complaint");
        System.out.println("Enter 4 to view Notifications");
        System.out.println("Enter 5 to register for parcel pick-up");
        while(true)
        {
            switch (input.getInteger())
            {
                case 1 -> getHelper(tenant,app);
                case 2 -> payMaintenanceBill(tenant,app);
                case 3 -> raiseComplaint(tenant,app);
                case 4 -> viewNotifications(tenant);
                case 5 -> viewParcelOptions(tenant,app);
            }
        }

    }

    private void getHelper(Tenant tenant, App app)
    {
        System.out.print("Enter the problem description:");
        String problemDescription = input.getString();
        System.out.println("Enter 1 to ask for Electrician");
        System.out.println("Enter 2 to ask for Plumber");
        System.out.println("Enter 3 to ask for Cleaner");
        System.out.println("Enter 4 to ask for Carpenter");
        HelperCategory selectedHelper = null;
        loop: while(true){
            switch (input.getInteger()) {
                case 1 -> {
                    selectedHelper = HelperCategory.ELECTRICIAN;
                    break loop;
                }
                case 2-> {
                    selectedHelper = HelperCategory.PLUMBER;
                    break loop;
                }
                case 3 -> {
                    selectedHelper = HelperCategory.CLEANER;
                    break loop;
                }
                case 4 -> {
                    selectedHelper = HelperCategory.CARPENTER;
                    break loop;
                }
                default -> System.out.print("Enter a valid choice");
            }
        }
        app.addTask(tenant,problemDescription,selectedHelper);
    }

    private void raiseComplaint(Tenant tenant, App app) {

        System.out.print("Enter the complaint description:");
        String complaintDescription = input.getString();
        System.out.print("Enter the block of the apartment where complaint to be fixed:");
        String blockName = input.getString();
        System.out.print("Enter the floor number:");
        int floorNumber = input.getInteger();
        System.out.println("Enter 1 for electrical issue");
        System.out.println("Enter 2 for plumbing work");
        System.out.println("Enter 3 for cleaning work");
        System.out.println("Enter 4 for Lift problems");
        System.out.println("Enter 5 for carpentry work ");
        HelperCategory category;
        loop: while(true){
            switch (input.getInteger()) {
                case 1 ->{
                    category = HelperCategory.ELECTRICIAN;
                    break loop;
                }
                case 2 ->{
                    category = HelperCategory.PLUMBER;
                    break loop;
                }
                case 3 ->{
                    category = HelperCategory.CLEANER;
                    break loop;
                }
                case 4 ->{
                    category = HelperCategory.ELEVATOR_MECHANIC;
                    break loop;
                }
                case 5 ->{
                    category = HelperCategory.CARPENTER;
                    break loop;
                }
                default -> System.out.print("Enter a valid choice:");
            }
        }
        app.addComplaint(tenant,complaintDescription,category,blockName,floorNumber);
    }

    private void payMaintenanceBill(Tenant tenant, App app) {
        ArrayList<MaintenanceBill> billsToBePaid = new ArrayList<>();
        for (MaintenanceBill bill : tenant.getBills()) {
            if (bill.getPaymentStatus().equals(PaymentStatus.YET_TO_PAY))
                billsToBePaid.add(bill);
        }
        System.out.println(" You have " + billsToBePaid.size() + " bill/s to pay");
        System.out.println("Enter 1 to view bills");
        System.out.println("Enter any other number to go back");
        if(input.getInteger() == 1 && billsToBePaid.size()>0)
        {
            System.out.println("Sl.no | BillMonth,Year | Total Bill Amount ");
            for (int counter = 0; counter < billsToBePaid.size(); counter++) {
                System.out.println(String.format("%4d", (counter + 1)) + " |" + billsToBePaid.get(counter).getBillMonth() + ", " + billsToBePaid.get(counter).getBillYear() + " | " + billsToBePaid.get(counter).getTotalMaintenanceCost());
            }
            System.out.print("Enter the serial number to pay the bill:");
            int number = input.getInteger();
            if (number <= billsToBePaid.size() && number > 0) {
                MaintenanceBill selectedBill = billsToBePaid.get(number - 1);
                loop:
                while (true) {
                    System.out.println("Enter 1 to pay the maintenance bill");
                    System.out.println("Enter 2 to view more details of the bill");
                    System.out.println("Enter any other number to go back");
                    switch (input.getInteger()) {
                        case 1 -> {
                            System.out.println(selectedBill);
                            System.out.print("Enter the amount to pay: Rs. ");
                            if (input.getDouble() == selectedBill.getTotalMaintenanceCost()) {
                                app.payBill(tenant, selectedBill);
                                break loop;
                            } else
                                System.out.println("Payment Failed\nTry Again");
                        }
                        case 2 -> viewTaskDetails(tenant, selectedBill);
                        default -> {
                            break loop;
                        }
                    }
                }
            }
        }

    }
    private void viewNotifications(Tenant tenant){
        for(Notification notification:tenant.getNotificationList()){
            System.out.println(notification);
        }
    }

    private void viewTaskDetails(Tenant tenant, MaintenanceBill bill){
        System.out.println("Date       |  Task Cost  |  Requirement Cost | Total Cost  |");
        for(Task taskToBeViewed : tenant.getMyRequestList()){
            if(taskToBeViewed.getCompletedDate().isBefore(bill.getDueDate().minusDays(10)) && taskToBeViewed.getIssueType().equals(IssueType.INDIVIDUAL_ISSUE)) {
                System.out.println(taskToBeViewed.getCompletedDate()+" | Rs. "+String.format("%8.2f",taskToBeViewed.getTaskCost())+" | Rs. "+String.format("%12.2f",taskToBeViewed.getRequirementCost())+" | Rs. "+ String.format("%8.2f",taskToBeViewed.getTotalTaskCost())+" |");
            }
        }
    }

    private void viewParcelOptions(Tenant tenant, App app){
        loop :while(true){
            System.out.println("Enter 1 to request for a new parcel");
            System.out.println("Enter 2 to view parcel list and edit");
            System.out.println("Enter any other number to go back");
            switch (input.getInteger()) {
                case 1 -> {
                    System.out.println("Enter the delivery date in the format dd-mm-yyyy");
                    LocalDate deliveryDate = input.getDate();
                    System.out.println("Enter the sender name");
                    String senderName = input.getString();
                    app.addParcel(tenant, deliveryDate, senderName);
                }
                case 2 -> {
                    Parcel selectedParcel;
                    int counter = 1;
                    for (Parcel parcel : tenant.getParcelList()) {
                        System.out.println(counter + " | " + parcel.getDeliveryDate() + " | " + String.format("%18s", parcel.getParcelStatus()) + " | " + parcel.getSender());
                        counter++;
                    }
                    System.out.println("Enter 1 to select the parcel to edit");
                    System.out.println("Enter any other number to go back");
                    if(input.getInteger() == 1) {
                        System.out.print("Enter the serial number to select the parcel:");
                        while (true){
                            int index = input.getInteger();
                            if (index > 0 && index <= tenant.getParcelList().size()) {
                                selectedParcel = tenant.getParcelList().get(index - 1);
                                editParcelDetails(selectedParcel, tenant, app);
                                break;
                            } else
                                System.out.print("Enter a valid serial number in the above list:");
                        }
                    }
                }
                default -> {
                    break loop;
                }
            }
        }
    }

    private void editParcelDetails(Parcel parcelToBeEdited, Tenant tenant, App app){
        System.out.println("Enter 1 to edit delivery date");
        System.out.println("Enter 2 to edit sender name");
        System.out.println("Enter any other number to return");
        if(input.getInteger() == 1) {
            System.out.print("Enter the deliveryDate in the format dd-mm-yyyy :");
            LocalDate newDate;
          while (true){
                newDate = input.getDate();
                if (parcelToBeEdited.getDeliveryDate().isEqual(newDate))
                    System.out.print("The date you entered is same as your old date\nPlease enter a new date:");
                else
                    break;
            }
          app.editParcelDate(tenant,parcelToBeEdited,newDate);
        }
        else if(input.getInteger() == 2){
            System.out.print("Enter the sender name :");
            String sender = input.getString();
            app.editParcelSender(tenant,parcelToBeEdited,sender);
        }
    }
}
