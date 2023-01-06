package people;

import java.util.ArrayList;

public class Tenant extends User {
    private Apartment apartment;
    private MaintenanceBill maintenanceBill;
    private ArrayList<MaintenanceBill> bills;
    private ArrayList<Task> myRequestList;
    private double taskCostPerMonth;
    private ArrayList<Parcel> parcelList;
    public Tenant(String name, String userName, String password) {
        super(name, userName, password);
        myRequestList = new ArrayList<>();
        parcelList = new ArrayList<>();
    }


    public MaintenanceBill getMaintenanceBill() {
        return maintenanceBill;
    }

    public ArrayList<MaintenanceBill> getBills() {
        return bills;
    }

    public Apartment getApartment() {
        return apartment;
    }
    public void addToRequestList(Task taskToBeAdded, ApartmentComplex apartmentComplex) {
        myRequestList.add(taskToBeAdded);
        apartmentComplex.addTask(taskToBeAdded);
    }

    public ArrayList<Task> getMyRequestList() {
        return myRequestList;
    }

    public void setMaintenanceBill(MaintenanceBill maintenanceBill,Manager manager) {
        this.maintenanceBill = maintenanceBill;
    }

    public ArrayList<Parcel> getParcelList() {
        return parcelList;
    }

    public void addParcel(Parcel parcelToBeAdded){
        parcelList.add(parcelToBeAdded);
    }


}
