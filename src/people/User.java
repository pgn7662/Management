package people;

import java.util.ArrayList;

public class User {
    private String name;
    private String userName;
    private String password;
    private ArrayList<Notification> notificationList;

    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void addNotification(Notification notificationToBeAdded){
        notificationList.add(notificationToBeAdded);
    }

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }
}

