package app;

import people.Employee;
import people.MaintenanceBill;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
      public static void main(String[] args){
            MaintenanceBill m = new MaintenanceBill(7500,1.2,LocalDate.now().minusDays(35),10);
            System.out.println(m);
      }
}