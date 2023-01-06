package people;

import constants.PaymentStatus;

import java.time.LocalDate;

public class MaintenanceBill {
    private double generalMaintenanceCost;
    private double totalTaskCostPerMonth;
    private double fineRate;
    private double fineCost;
    private LocalDate dueDate;
    private int finePeriod;
    private int billMonth;
    private int billYear;
    private PaymentStatus paymentStatus;

    public MaintenanceBill(double generalMaintenanceCost, double fineRate, LocalDate dueDate, int finePeriod) {
        this.generalMaintenanceCost = generalMaintenanceCost;
        this.fineRate = fineRate;
        this.dueDate = dueDate;
        this.finePeriod = finePeriod;
        this.fineCost = 0;
        this.billMonth = LocalDate.now().getMonthValue();
        this.billYear = LocalDate.now().getYear();
        this.paymentStatus = PaymentStatus.YET_TO_PAY;
    }

    public double getTotalMaintenanceCost() {
        return fineCost+totalTaskCostPerMonth+generalMaintenanceCost;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getFineRate() {
        return fineRate;
    }

    public int getFinePeriod() {
        return finePeriod;
    }

    public double getTotalTaskCostPerMonth() {
        return totalTaskCostPerMonth;
    }

    public double getGeneralMaintenanceCost() {
        return generalMaintenanceCost;
    }

    public double getFineCost() {
        return fineCost;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public int getBillMonth() {
        return billMonth;
    }

    public int getBillYear() {
        return billYear;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return  dueDate+
                      "\nGeneral Maintenance Cost     = Rs. "+generalMaintenanceCost+
                      "\nTotal task cost              = Rs. "+totalTaskCostPerMonth+
                      "\nFine cost                    = Rs. "+fineCost+
                      "\nTotal Maintenance Cost       = Rs. "+getTotalMaintenanceCost();
    }



}
