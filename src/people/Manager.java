package people;
import constants.HelperCategory;
import constants.HelperStatus;
import constants.TaskStatus;

import java.util.ArrayList;

public class Manager extends Employee{
    private ArrayList<Announcement> announcementList;

    public Manager(String name, String userName, String password,String id,int age,long phoneNumber,String location) {
        super(name, userName, password,id,age,phoneNumber,location);
        announcementList = new ArrayList<>();
    }

    public void assignHelper(Task taskToBeAssigned, Helper helperToBeAssigned) {
        helperToBeAssigned.addTask(taskToBeAssigned,this);
        taskToBeAssigned.setTaskStatus(TaskStatus.ASSIGNED);
        helperToBeAssigned.setHelperStatus(HelperStatus.TASK_ASSIGNED);
        taskToBeAssigned.setAssignedHelper(helperToBeAssigned);
    }

    public void addHelper(String name, int age, long phoneNumber, String location, HelperCategory helperCategory, ApartmentComplex apartmentComplex){
        String id,userName,password;
        int technicianCount = 1001,cleanerCount = 1001,securityCount = 1001;
        if(helperCategory.equals(HelperCategory.ELECTRICIAN)  || helperCategory.equals(HelperCategory.ELEVATOR_MECHANIC)) {
            String idNumber = technicianCount++ +"";
            id = "TECH"+idNumber.substring(1);
            userName = name.replaceAll(" ","").toLowerCase()+id;
            password = id+"@"+apartmentComplex.getName().replaceAll(" ","");
            Helper technician = new Helper(name,userName,password,id,age,phoneNumber,location, HelperStatus.READY_TO_WORK, helperCategory);
            apartmentComplex.addHelper(technician);
        }
        else if(helperCategory.equals(HelperCategory.CLEANER)) {
            String idNumber = cleanerCount++ +"";
            id = "CLNR"+idNumber.substring(1);
            userName = name.replaceAll(" ","").toLowerCase()+id;
            password = id+"@"+apartmentComplex.getName().replaceAll(" ","");
            Helper cleaner = new Helper(name,userName,password,id,age,phoneNumber,location, HelperStatus.READY_TO_WORK, HelperCategory.CLEANER);
            apartmentComplex.addHelper(cleaner);
        }
        else if(helperCategory.equals(HelperCategory.PLUMBER)) {
            String idNumber = securityCount++ +"";
            id  = "SCGD"+idNumber.substring(1);
            userName = name.replaceAll("\s+","").toLowerCase()+id;
            password = id+"@"+apartmentComplex.getName().replaceAll("\s+","");
            SecurityGuard securityGuard = new SecurityGuard(name,userName,password,id,age,phoneNumber,location);
            apartmentComplex.addSecurityGuard(securityGuard);
        }
    }

    public void removeHelper(Helper helperToBeRemoved, ApartmentComplex apartmentComplex){
        if(helperToBeRemoved.getHelperStatus().equals(HelperStatus.IDLE) || helperToBeRemoved.getHelperStatus().equals(HelperStatus.READY_TO_WORK))
            apartmentComplex.removeHelper(helperToBeRemoved);
        else
            System.out.println("Helper cannot be removed\nHelper is already in the task.\nPlease remove the user once the task is completed");
    }

    public void addAnnouncement(Announcement announcementToBeAdded,ApartmentComplex apartmentComplex){
        announcementList.add(announcementToBeAdded);
        apartmentComplex.addAnnouncement(announcementToBeAdded);
    }


}
