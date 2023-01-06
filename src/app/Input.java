package app;

import exception.InvalidName;
import exception.NameChecker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {
    Scanner s = new Scanner(System.in);
    public int getInteger(){
        int number;
        while(true){
            try {
                number = Integer.parseInt(getString());
                if(number>=0)
                    return number;
                else{
                    System.out.print("Enter a non negative number: ");
                }
            } catch (Exception e) {
                System.out.print("Enter a valid number:");
            }
        }
    }

    public String getString(){
        return s.nextLine();
    }

    public long getLong() {
        while(true)
        {
            try{
                return Long.parseLong(getString());
            }
            catch (Exception e){
                System.out.print("Enter a valid number:");
            }
        }
    }

    public long getPhoneNumber(){
        while (true) {
            String phoneNumber = getString();
            if(phoneNumber.length()==10)
            {
                try {
                    return Long.parseLong(phoneNumber);
                }
                catch (Exception e){
                    System.out.print("Enter a valid phone number: ");
                }
            }
        }
    }

    public String getName(){
        while (true){
            try {
                String name = getString();
                NameChecker.checkName(name);
                return name;
            }
            catch (InvalidName invalidName) {
                System.out.println(invalidName.getMessage());
                System.out.print("Enter a valid name:");
            }
        }
    }

    public int getAge(){
        while(true){
            int age = getInteger();
            if(age > 18 && age <= 45)
                return age;
            else
                System.out.print("Enter a valid age");
        }
    }

    public double getDouble(){
        while(true){
            try{
                return Double.parseDouble(getString());
            }
            catch (Exception e){
                System.out.print("Enter a valid number:");
            }
        }
    }

    public LocalDate getDate(){
        while (true){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(getString(),formatter);
                if(LocalDate.now().isAfter(date) || LocalDate.now().isEqual(date)){
                    return date;
                }
                else
                    System.out.print("The date you entered is already over.\nPlease enter a valid date");
            }
            catch (Exception e){
                System.out.print("Enter the date in the correct format dd-mm-yyyy Example :01-01-2000:");
            }
        }
    }
}
