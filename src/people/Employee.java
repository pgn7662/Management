package people;

import app.Input;

public abstract class Employee extends User {
    private Input input = new Input();
    private String id;
    private int age;
    private long phoneNumber;
    private String location;

    public Employee(String name, String userName, String password, String id, int age, long phoneNumber, String location) {
        super(name, userName, password);
        this.id = id;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

}
