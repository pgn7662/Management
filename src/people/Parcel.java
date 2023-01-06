package people;

import constants.ParcelStatus;

import java.time.LocalDate;

public class Parcel {
    private Tenant tenant;
    private LocalDate deliveryDate;
    private ParcelStatus parcelStatus;
    private String sender;

    public Parcel(Tenant tenant, LocalDate deliveryDate, ParcelStatus parcelStatus,String sender) {
        this.tenant = tenant;
        this.deliveryDate = deliveryDate;
        this.parcelStatus = parcelStatus;
        this.sender = sender;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public ParcelStatus getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(ParcelStatus parcelStatus,SecurityGuard securityGuard) {
        this.parcelStatus = parcelStatus;
    }

    public String getSender() {
        return sender;
    }

    public void setDeliveryDate(LocalDate deliveryDate,Tenant tenant) {
        this.deliveryDate = deliveryDate;
    }

    public void setSender(String sender,Tenant tenant) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return deliveryDate+" | "+String.format("%30s",tenant.getName())+" | "+String.format("%30s",sender)+" | "+String.format("%18s",parcelStatus)+" | ";
    }
}
