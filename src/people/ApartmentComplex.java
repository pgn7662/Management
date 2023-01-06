package people;

import java.util.ArrayList;

public class ApartmentComplex {
    private String name;
    private String address;
    private int pinCode;
    private Manager manager;
    private ArrayList<Apartment> apartments;
    private ArrayList<Helper> helperList;
    private ArrayList<SecurityGuard> securityGuardList;
    private ArrayList<Task> taskList;
    private ArrayList<Announcement> announcementList;
    private ArrayList<Parcel> parcelList;
    public ApartmentComplex(String name, String address, int pinCode) {
        this.name = name;
        this.address = address;
        this.pinCode = pinCode;
        this.apartments = new ArrayList<>();
        this.helperList = new ArrayList<>();
        this.taskList = new ArrayList<>();
        this.announcementList = new ArrayList<>();
        this.parcelList = new ArrayList<>();
        manager = new Manager("manager","manager1","manager","1",32,1234567890,"abc");
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPinCode() {
        return pinCode;
    }

    public Manager getManager() {
        return manager;
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public ArrayList<Helper> getEmployeeList() {
        return helperList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    protected void addHelper(Helper helperToBeRemoved){
        helperList.add(helperToBeRemoved);
    }

    protected void removeHelper(Helper helperToBeRemoved) {
        helperList.remove(helperToBeRemoved);
    }

    protected void addTask(Task taskToBeAdded){
        taskList.add(taskToBeAdded);
    }


    protected void addAnnouncement(Announcement announcementToBeAdded){
        announcementList.add(announcementToBeAdded);
    }
    protected void removeTask(Task taskToBeRemoved){
        taskList.remove(taskToBeRemoved);
    }

    protected void removeAnnouncement(Announcement announcementToBeRemoved){
        announcementList.remove(announcementToBeRemoved);
    }

    protected void addSecurityGuard(SecurityGuard securityGuardToBeAdded){
        securityGuardList.add(securityGuardToBeAdded);
    }

    protected void removeSecurityGuard(SecurityGuard securityGuardToBeRemoved){
        securityGuardList.remove(securityGuardToBeRemoved);
    }

    protected  ArrayList<Parcel> getParcelList(){
        return parcelList;
    }

    public void addParcel(Parcel parcelToBeAdded){
        parcelList.add(parcelToBeAdded);
    }
}
